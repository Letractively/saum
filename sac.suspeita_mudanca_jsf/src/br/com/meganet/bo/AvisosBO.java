package br.com.meganet.bo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.AvisosDAO;
import br.com.meganet.hbm.DAO.BoletosGeradosDAO;
import br.com.meganet.hbm.DAO.DemandasDAO;
import br.com.meganet.hbm.DAO.UsuarioDAO;
import br.com.meganet.hbm.vo.Avisos;
import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.Contato;
import br.com.meganet.hbm.vo.Demandas;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.Constantes;

public class AvisosBO{
	

	private AvisosDAO avisosDAO;
	private BoletosGeradosDAO boletosGeradosDAO;
	private DemandasDAO demandasDAO;
	private UsuarioDAO usuarioDAO;
	private ContatoBO contatoBO;
	private BoletoBO boletoBO;

	public void setBoletoBO(BoletoBO boletoBO) {
		this.boletoBO = boletoBO;
	}
	public void setDemandasDAO(DemandasDAO demandasDAO) {
		this.demandasDAO = demandasDAO;
	}
	public void setContatoBO(ContatoBO contatoBO) {
		this.contatoBO = contatoBO;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	public void setBoletosGeradosDAO(BoletosGeradosDAO boletosGeradosDAO) {
		this.boletosGeradosDAO = boletosGeradosDAO;
	}
	public void setAvisosDAO(AvisosDAO avisosDAO) {
		this.avisosDAO = avisosDAO;
	}

	public List<Avisos> carregaAvisos() throws GAPBDException {
		return avisosDAO.findByCriteria(
			Order.asc(AvisosDAO.AVISOS_DATA_EXPIRACAO),
			Expression.eq(AvisosDAO.AVISOS_ATIVO, true),
			Expression.gt(AvisosDAO.AVISOS_DATA_EXPIRACAO, new Timestamp(System.currentTimeMillis())));
	}

	public Avisos carregaAviso(Long id) throws GAPBDException {
		return avisosDAO.findById(id);
	}
	
	public List<Avisos> carregaTodosAvisos() throws GAPBDException {
		return avisosDAO.findAll();
	}

	public List<Avisos> carregaAvisosAdministrativo(Usuario usuario, Boolean fb) throws Exception {
		Calendar calTmp = GregorianCalendar.getInstance(new Locale("pt","br"));
		calTmp.setTimeInMillis(System.currentTimeMillis());
		calTmp.set(Calendar.DATE, calTmp.get(Calendar.DATE) - 4);
		
		Timestamp hoje = new Timestamp(calTmp.getTimeInMillis());
		List<Avisos> avisos = this.carregaAvisos();
		List<BoletosGerados> boletos = boletosGeradosDAO.findByCriteria(
			Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO),
			Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, hoje),
			Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));
		
		List<BoletosGerados> bolsSemDesativado = new ArrayList<BoletosGerados>();
		for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados = (BoletosGerados) iterator.next();
			if(boletosGerados.getUsuario().getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) == 0){
				bolsSemDesativado.add(boletosGerados);
			}
		}
		
		if(boletos != null && boletos.size() > 0){
			Avisos avisosTmp = new Avisos();
			avisosTmp.setAvisosAtivo(true);
			if(!fb.booleanValue()){
				avisosTmp.setAvisosTitulo("<div style=\"cursor:pointer; text-decoration: underline\" onclick=\"buscaDetalheAviso(1)\">" + (boletos.size() - bolsSemDesativado.size()) + " boletos vencidos</div>");
			}else{
				avisosTmp.setAvisosTitulo((boletos.size() - bolsSemDesativado.size()) + " boletos vencidos");
			}
			avisos.add(avisosTmp);
		}
		
		
		Calendar cal = GregorianCalendar.getInstance(new Locale("pt","br"));
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 2);
		Timestamp anteOntem = new Timestamp(cal.getTimeInMillis());
		
		List<Demandas> demandas = demandasDAO.findByCriteria(
			Expression.le(DemandasDAO.DATA_ABERTURA, anteOntem),
			Expression.isNull(DemandasDAO.DATA_ENCERRAMENTO));
		
		if(demandas != null && demandas.size() > 0){
			Avisos avisoTmp = new Avisos();
			avisoTmp.setAvisosAtivo(true);
			if(!fb.booleanValue()){
				avisoTmp.setAvisosTitulo("<div style=\"cursor:pointer; text-decoration: underline\" onclick=\"buscaDetalheAviso(2)\">" + demandas.size() + " demandas vencidas</div>");
			}else{
				avisoTmp.setAvisosTitulo(demandas.size() + " demandas vencidas");
			}
			avisos.add(avisoTmp);
		}
		
		List<Usuario> usuarioMensagem = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_BLOQUEADO, Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM));
		if(usuarioMensagem != null && usuarioMensagem.size() > 0){
			Avisos avisoTmp = new Avisos();
			avisoTmp.setAvisosAtivo(true);
			if(!fb.booleanValue()){
				avisoTmp.setAvisosTitulo("<div style=\"cursor:pointer; text-decoration: underline\" onclick=\"buscaDetalheAviso(3)\">" + usuarioMensagem.size() + " usuario(s) com mensagem de advertência.</div>");
			}else{
				avisoTmp.setAvisosTitulo(usuarioMensagem.size() + " usuario(s) com mensagem de advertência.");
			}
			avisos.add(avisoTmp);
		}

		List<Usuario> usuarioBloqueado = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_BLOQUEADO, Constantes.USR_ATIVO_BLOQUEADO));
		if(usuarioBloqueado != null && usuarioBloqueado.size() > 0){
			Avisos avisoTmp = new Avisos();
			avisoTmp.setAvisosAtivo(true);
			if(!fb.booleanValue()){
				avisoTmp.setAvisosTitulo("<div style=\"cursor:pointer; text-decoration: underline\" onclick=\"buscaDetalheAviso(4)\">" + usuarioBloqueado.size() + " usuario(s) bloqueado(s).</div>");
			}else{
				avisoTmp.setAvisosTitulo(usuarioBloqueado.size() + " usuario(s) bloqueado(s).");
			}
			avisos.add(avisoTmp);
		}
		
		List<Contato> contatos = contatoBO.adiquirirContatosAbertos();
		if(contatos != null && contatos.size() > 0){
			Avisos avisoTmp = new Avisos();
			avisoTmp.setAvisosAtivo(true);
			if(!fb.booleanValue()){
				avisoTmp.setAvisosTitulo("<div style=\"cursor:pointer; text-decoration: underline\" onclick=\"fnResponderContato();\">" + contatos.size() + " Contatos sem resposta.</div>");
			}else{
				avisoTmp.setAvisosTitulo(contatos.size() + " Contatos sem resposta.");
			}
			avisos.add(avisoTmp);
		}
		
		return avisos;
	}
	
	public List<Avisos> carregaAvisosCliente(Usuario usuario) throws Exception {
		Calendar calTmp = GregorianCalendar.getInstance(new Locale("pt","br"));
		calTmp.setTimeInMillis(System.currentTimeMillis());
		calTmp.set(Calendar.DATE, calTmp.get(Calendar.DATE) - 4);
		
		List<Avisos> avisos = this.carregaAvisos();
		Collection<BoletosGerados> boletos = boletoBO.adiquirirBoletosEmAberto(usuario.getUsuarioCpf());
		
		if(boletos != null && boletos.size() > 0){
			String conteudoHTML = "<table width=\"100%\" align=\"fight\">" +
					"<tr bgcolor=\"#DFE8F6\" align=\"center\">" +
					"<td width=\"33%\">Data Venc.</td><td width=\"33%\">Data pag.</td><td>Gerar</td></tr>";
			for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {
				BoletosGerados bg = (BoletosGerados) iterator.next();
				
				if(!bg.getBoletosgeradosPago()){
					String link = " onClick=\"geraBoleto(\'"+ bg.getBoletosgeradosId() +"\');\" ";
					
					conteudoHTML += "<tr align=\"center\"><td>"+ bg.getDataTempVencimento() +"</td>";
					if(bg.getVencido()){
						conteudoHTML += "<td>Vencido</td>";
					}else{
						conteudoHTML += "<td>Aberto</td>";
					}
					conteudoHTML += "<td><button style=\"width: 30px; height=18px;font-size: 10px\""+ link +">OK</button></td>";
				conteudoHTML += "</tr>";
				}
			}
			conteudoHTML += "</table>";
			
			Avisos avBol = new Avisos();
			avBol.setAvisosAtivo(true);
			avBol.setAvisosTitulo(conteudoHTML);
			avisos.add(avBol);

		}
		return avisos;
	}
	
	public List<Avisos> carregaClientesInadimplentes() throws GAPBDException {
		Calendar calTmp = GregorianCalendar.getInstance(new Locale("pt","br"));
		calTmp.setTimeInMillis(System.currentTimeMillis());
		calTmp.set(Calendar.DATE, calTmp.get(Calendar.DATE) - 4);
		
		Timestamp hoje = new Timestamp(calTmp.getTimeInMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
		Usuario u = new Usuario();
		u.setUsuarioBloqueado(Constantes.USR_DESATIVADO);
		List<Avisos> avisos = new ArrayList<Avisos>();
		List<BoletosGerados> boletos = boletosGeradosDAO.findByCriteria(
				Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO),
				Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, hoje),
				Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));
		
		for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados = (BoletosGerados) iterator.next();
			if(boletosGerados.getUsuario().getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) != 0){
				Avisos avisosTmp = new Avisos();
				avisosTmp.setAvisosAtivo(true);
				String estado;
				switch (boletosGerados.getUsuario().getUsuarioBloqueado().intValue()) {
				case 0:
					estado = "Normal";
					break;
				case 1:
					estado = "Mensagem";
					break;
				case 2:
					estado = "Bloqueado";
					break;
				case 3:
					estado = "Desativado";
					break;
				default:
					estado = "N/A";
					break;
				}
				String dataVenc = sdf.format(boletosGerados.getBoletosgeradosDataVencimento());
				avisosTmp.setAvisosTitulo(boletosGerados.getUsuario().getUsuarioNome() + " - ID: " + boletosGerados.getUsuario().getUsuarioId() + " - Venc. " + dataVenc + " - " + estado);
				avisos.add(avisosTmp);
			}
		}
		
		return avisos;
	}
	
	public List<Avisos> carregaDemandasAtrasadas() throws GAPBDException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
		List<Avisos> avisos = new ArrayList<Avisos>();

		Calendar cal = GregorianCalendar.getInstance(new Locale("pt","br"));
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 2);
		Timestamp anteOntem = new Timestamp(cal.getTimeInMillis());
		
		List<Demandas> demandas = demandasDAO.findByCriteria(
				Expression.le(DemandasDAO.DATA_ABERTURA, anteOntem),
				Expression.isNull(DemandasDAO.DATA_ENCERRAMENTO));
		
		for (Iterator<Demandas> iterator = demandas.iterator(); iterator.hasNext();) {
			Demandas demandas2 = (Demandas) iterator.next();
			Avisos avisoTmp = new Avisos();
			avisoTmp.setAvisosAtivo(true);
			avisoTmp.setAvisosTitulo("Demanda " + demandas2.getDemandasId() + " aberta desde " + sdf.format(demandas2.getDemandasDataAbertura()));
			avisos.add(avisoTmp);
		}
		
		return avisos;
	}
	
	public Avisos gravaAviso(Avisos aviso) {
		avisosDAO.save(aviso);
		return aviso;
	}
	
	public List<Avisos> carregaAvisosAdministrativoWAP(Usuario usuario) throws GAPBDException {
		Calendar calTmp = GregorianCalendar.getInstance(new Locale("pt","br"));
		calTmp.setTimeInMillis(System.currentTimeMillis());
		calTmp.set(Calendar.DATE, calTmp.get(Calendar.DATE) - 4);
		
		Timestamp hoje = new Timestamp(calTmp.getTimeInMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
		List<Avisos> avisos = new ArrayList<Avisos>();
		List<BoletosGerados> boletos = boletosGeradosDAO.findByCriteria(
			Order.asc(BoletosGeradosDAO.BOLETOSGERADOS_USUARIO),
			Expression.le(BoletosGeradosDAO.BOLETOSGERADOS_DATA_VENCIMENTO, hoje),
			Expression.eq(BoletosGeradosDAO.BOLETOSGERADOS_PAGO, false));
		for (Iterator<BoletosGerados> iterator = boletos.iterator(); iterator.hasNext();) {
			BoletosGerados boletosGerados = (BoletosGerados) iterator.next();
			if(boletosGerados.getUsuario().getUsuarioBloqueado().compareTo(Constantes.USR_DESATIVADO) != 0){
				Avisos avisosTmp = new Avisos();
				avisosTmp.setAvisosAtivo(true);
				String dataVenc = sdf.format(boletosGerados.getBoletosgeradosDataVencimento());
				avisosTmp.setAvisosAviso("<a href=\"atendimentoUsuario.do?id=" + boletosGerados.getUsuario().getUsuarioId() 
						+ "\">" + boletosGerados.getUsuario().getUsuarioNome() + "</a>" +  
						" código 1 - " + dataVenc);
				avisos.add(avisosTmp);
			}
		}
		
		Calendar cal = GregorianCalendar.getInstance(new Locale("pt","br"));
		cal.setTime(new Date(System.currentTimeMillis()));
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 2);
		Timestamp anteOntem = new Timestamp(cal.getTimeInMillis());
		
		List<Demandas> demandas = demandasDAO.findByCriteria(
			Expression.le(DemandasDAO.DATA_ABERTURA, anteOntem),
			Expression.isNull(DemandasDAO.DATA_ENCERRAMENTO));
		
		for (Iterator<Demandas> iterator = demandas.iterator(); iterator.hasNext();) {
			Demandas demandas2 = (Demandas) iterator.next();
			Avisos avisoTmp = new Avisos();
			avisoTmp.setAvisosAtivo(true);
			avisoTmp.setAvisosAviso("Demanda <a href=\"finalizarDemanda.do\"> " + demandas2.getDemandasId() + "</a>" +
					" aberta desde " + sdf.format(demandas2.getDemandasDataAbertura()));
			avisos.add(avisoTmp);
		}
		return avisos;
	}
}
