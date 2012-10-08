package br.com.meganet.dwr;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.jboleto.JBoletoBean;

import br.com.meganet.facade.AdministracaoFacade;
import br.com.meganet.facade.BoletoFacade;
import br.com.meganet.hbm.vo.Avisos;
import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.ComboVO;
import br.com.meganet.hbm.vo.Contato;
import br.com.meganet.hbm.vo.Demandas;
import br.com.meganet.hbm.vo.EnderecoIp;
import br.com.meganet.hbm.vo.LogMeganet;
import br.com.meganet.hbm.vo.Mapa;
import br.com.meganet.hbm.vo.Menu;
import br.com.meganet.hbm.vo.PlanosPacotes;
import br.com.meganet.hbm.vo.Resposta;
import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.hbm.vo.StatusCliente;
import br.com.meganet.hbm.vo.StatusEquipamento;
import br.com.meganet.hbm.vo.TipoEquipamento;
import br.com.meganet.hbm.vo.Torre;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.DateUtils;
import br.com.meganet.util.Util;
import br.com.meganet.util.UtilNumero;

public class AdministracaoJS {
	
	private AdministracaoFacade administracaoFacade;
	private BoletoFacade boletoFacade;
	public void setBoletoFacade(BoletoFacade boletoFacade) {
		this.boletoFacade = boletoFacade;
	}
	public void setAdministracaoFacade(AdministracaoFacade administracaoFacade) {
		this.administracaoFacade = administracaoFacade;
	}
	
	public Collection<Menu> buscaMenu(int coluna){
		Collection<Menu> dadosMenu = administracaoFacade.buscaMenu(coluna);
		return dadosMenu;
	}
	
	public int alteraMenu(Menu menu){
		int retorno = administracaoFacade.alteraMenu(menu);
		return retorno;
	}
	
	public Resposta insereUsuario(Usuario usuario){
		usuario.setUsuarioDtAtivacao(DateUtils.traduzDataStringToTimeStamp(usuario.getDataTemp()));
		Resposta retorno = administracaoFacade.insereUsuario(usuario);
		return retorno;
	}
	
	public Resposta desbloqueiaUsr(Long id, String dataLimite, String motivo){
		Resposta retorno = administracaoFacade.desbloqueiaUsr(id, dataLimite, motivo);
		return retorno;
	}
	
	
	public Resposta alteraUsuario(Usuario usuario){
		usuario.setUsuarioDtAtivacao(DateUtils.traduzDataStringToTimeStamp(usuario.getDataTemp()));
		usuario.setUsuarioDataLimiteDesbloqueio(DateUtils.traduzDataStringToTimeStamp(usuario.getDataTemp2()));
		
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Usuario usuarioSessao = (Usuario) req.getSession().getAttribute("usuario");
		usuario.setUsuarioRealizouAlteracao(usuarioSessao);
		
		if(usuario.getUsuarioDataLimiteDesbloqueio() != null){

			Demandas dem = new Demandas();
			dem.setDemandasDataAbertura(new Timestamp(System.currentTimeMillis()));
			dem.setDemandasDescricao(usuario.getMotivoDesbloqueio());
			dem.setDemandasDataPrevistaAtendimento(usuario.getUsuarioDataLimiteDesbloqueio());
			dem.setUsuarioAbriu(usuarioSessao);
			dem.setDemandasDescricaoEncerramento(Constantes.MENSAGEM_DESBLOQUEIO_TEMPORARIO);
			dem.setUsuarioFechou(usuarioSessao);
			dem.setUsuarioSolicitante(usuario);
			dem.setDemandasDataEncerramento(usuario.getUsuarioDataLimiteDesbloqueio());
			administracaoFacade.gravaDemanda(dem, false);
		}
		if(usuario.getUsuarioDataLimiteDesbloqueio() != null &&
				DateUtils.aPrimeiraEhMaiorQueSegunda(new Date(System.currentTimeMillis()), new Date(usuario.getUsuarioDataLimiteDesbloqueio().getTime()))){
			return new Resposta(Resposta.ALTERA_CLIENTE, "Data limite menor que a data atual.");
		}else{
			if(usuarioSessao.getUsuarioAdministrativo() &&
					usuarioSessao.getUsuarioAlteraProprioPerfil() && 
					usuarioSessao.getUsuarioId().compareTo(usuario.getUsuarioId()) == 0){
				
				Resposta retorno = administracaoFacade.alteraUsuario(usuario);
				if(usuarioSessao.getUsuarioId().compareTo(usuario.getUsuarioId()) == 0){
					req.getSession().setAttribute("usuario", usuario);
				}
				return retorno;
			
			}else if(usuarioSessao.getUsuarioAdministrativo() && 
					usuarioSessao.getUsuarioId().compareTo(usuario.getUsuarioId()) != 0){
				
				return administracaoFacade.alteraUsuario(usuario);
			
			}else{
				
				return new Resposta(Resposta.ALTERA_CLIENTE, "Alteração não autorizada.");
			}
		}
	}
	
	public Resposta alteraUsuarioCliente(Usuario usuario){
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();

		Resposta resp = administracaoFacade.alteraUsuarioCliente(usuario, req);
		return resp;
	}
	
	
	public Resposta insereDadosUsuario(Usuario usuario){
		String cpfCnpj = usuario.getUsuarioCpf();
		if(cpfCnpj != null){
			cpfCnpj = cpfCnpj.replace(".", "").replace("-", "").replace("/", "");
			if(UtilNumero.validaCPF(cpfCnpj) || UtilNumero.validaCNPJ(cpfCnpj)){
				usuario.setUsuarioDtAtivacao(DateUtils.traduzDataStringToTimeStamp(usuario.getDataTemp()));
				Resposta retorno = administracaoFacade.insereDadosUsuario(usuario);
				return retorno;
			}else{
				return new Resposta(Resposta.ADICIONA_CLIENTE, "CPF/CNPJ inválido. Verifique os dados antes de gravar.");
			}
		}else{
			return new Resposta(Resposta.ADICIONA_CLIENTE, "CPF/CNPJ inválido. Verifique os dados antes de gravar.");
		}
	}
	
	public Resposta excluirUsuario(Long usuario){
		Resposta retorno = administracaoFacade.excluirUsuario(usuario);
		return retorno;
	}
	
	public Collection<Usuario> buscaUsuarios(){
		Collection<Usuario> usuarios = administracaoFacade.buscaUsuarios();
		return usuarios;
	}
	
	public Collection<Usuario> buscaUsuariosAdministrativos(){
		List<Usuario> usuarios = administracaoFacade.buscaUsuariosAdministrativos();
		Collection<Usuario> usr2 = new ArrayList<Usuario>();
		for (Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext();) {
			Usuario usuario = (Usuario) iterator.next();
			Usuario us = new Usuario();
			us.setUsuarioNome(usuario.getUsuarioNome());
			us.setUsuarioId(usuario.getUsuarioId());
			usr2.add(us);
		}
		return usr2;
	}
	
	public Collection<Usuario> buscarUsuarios(String cliente){
		Collection<Usuario> usuarios = administracaoFacade.buscaUsuarios(cliente);
		Collection<Usuario> usr2 = new ArrayList<Usuario>();
		for (Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext();) {
			Usuario usuario = (Usuario) iterator.next();
			Usuario us = new Usuario();
			us.setUsuarioCpf(usuario.getUsuarioCpf());
			us.setUsuarioNome(usuario.getUsuarioNome());
			us.setUsuarioId(usuario.getUsuarioId());
			usr2.add(us);
		}
		return usr2;
	}
	
	public Collection<Mapa> buscarMapaPeloServidor(Long idServ){
		Collection<Mapa> res = administracaoFacade.buscarMapaPeloServidor(idServ);
		Collection<Mapa> ret = new ArrayList<Mapa>();
		for (Iterator<Mapa> iterator = res.iterator(); iterator.hasNext();) {
			Mapa mapa = (Mapa) iterator.next();
			Mapa m = new Mapa();
			Usuario u = new Usuario();
			Servidor s = new Servidor();
			
			m.setMapaLeft(mapa.getMapaLeft());
			m.setMapaTop(mapa.getMapaTop());
			
			s.setServidorNome(mapa.getUsuario().getServidor().getServidorNome());
			u.setServidor(s);
			
			u.setUsuarioNome(mapa.getUsuario().getUsuarioNome());
			u.setUsuarioEndereco(mapa.getUsuario().getUsuarioEndereco());
			u.setUsuarioBairro(mapa.getUsuario().getUsuarioBairro());
			u.setUsuarioCidade(mapa.getUsuario().getUsuarioCidade());
			u.setUsuarioCpf(mapa.getUsuario().getUsuarioCpf());
			u.setUsuarioId(mapa.getUsuario().getUsuarioId());
			
			m.setUsuario(u);
			ret.add(m);
		}
		return ret;
	}
	
	public Collection<Usuario> buscaClientes(){
		Collection<Usuario> usuarios = administracaoFacade.buscaClientes();
		return usuarios;
	}
	
	public List<Usuario> buscaComboClientes(){
		Collection<Usuario> usuarios = administracaoFacade.buscaClientes();
		List<Usuario> ret = new ArrayList<Usuario>();
		for (Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext();) {
			Usuario usuario = (Usuario) iterator.next();
			Usuario u = new Usuario();
			u.setUsuarioId(usuario.getUsuarioId());
			u.setUsuarioNome(usuario.getUsuarioNome());
			ret.add(u);
		}
		return ret;
	}
	
	public Collection<Usuario> buscaClientesSemLocalizacao(){
		Collection<Usuario> usuarios = administracaoFacade.buscaClientesSemLocalizacao();
		Collection<Usuario> lUs = new ArrayList<Usuario>();
		for (Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext();) {
			Usuario usuario = (Usuario) iterator.next();
			Usuario us = new Usuario();
			us.setUsuarioId(usuario.getUsuarioId());
			us.setUsuarioNome(usuario.getUsuarioNome());
			lUs.add(us);
		}
		return lUs;
	}
	
	public Collection<Usuario> buscaClientesComboLocalizacao(){
		Collection<Usuario> usuarios = administracaoFacade.buscaClientesComboLocalizacao();
		Collection<Usuario> lUs = new ArrayList<Usuario>();
		for (Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext();) {
			Usuario usuario = (Usuario) iterator.next();
			Usuario us = new Usuario();
			us.setUsuarioId(usuario.getUsuarioId());
			us.setUsuarioNome(usuario.getUsuarioNome());
			lUs.add(us);
		}
		return lUs;
	}
	
	public Collection<Usuario> buscaClientesComboLocalizacaoGoogle(){
		Collection<Usuario> usuarios = administracaoFacade.buscaClientesComboLocalizacaoGoogle();
		Collection<Usuario> lUs = new ArrayList<Usuario>();
		for (Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext();) {
			Usuario usuario = (Usuario) iterator.next();
			Usuario us = new Usuario();
			us.setUsuarioId(usuario.getUsuarioId());
			us.setUsuarioNome(usuario.getUsuarioNome());
			lUs.add(us);
		}
		return lUs;
	}
	
	public List<Mapa> buscaClientesComLocalizacao(){
		List<Mapa> mapas = administracaoFacade.buscaClientesComLocalizacao();
		List<Mapa> lMap = new ArrayList<Mapa>();
		for (Iterator<Mapa> iterator = mapas.iterator(); iterator.hasNext();) {
			Mapa mapa = (Mapa) iterator.next();
			Mapa m = new Mapa();
			m.setMapaId(mapa.getMapaId());
			m.setMapaLeft(mapa.getMapaLeft());
			m.setMapaTop(mapa.getMapaTop());
			Usuario usr = new Usuario();
			usr.setUsuarioBloqueado(mapa.getUsuario().getUsuarioBloqueado());
			m.setUsuario(usr);
			lMap.add(m);
		}
		
		return lMap;
	}
	
	public Long localizarMapa(Long idUsuario){
		return administracaoFacade.localizarMapa(idUsuario);
	}

	public Mapa localizarMapaGoogle(Long idUsuario){
		Mapa mapa = administracaoFacade.localizarMapaGoogle(idUsuario);
		Mapa m = new Mapa();
		Usuario u = new Usuario();
		Servidor s = new Servidor();
		
		m.setMapaLeft(mapa.getMapaLeft());
		m.setMapaTop(mapa.getMapaTop());
		
		s.setServidorNome(mapa.getUsuario().getServidor().getServidorNome());
		u.setServidor(s);
		
		u.setUsuarioNome(mapa.getUsuario().getUsuarioNome());
		u.setUsuarioEndereco(mapa.getUsuario().getUsuarioEndereco());
		u.setUsuarioBairro(mapa.getUsuario().getUsuarioBairro());
		u.setUsuarioCidade(mapa.getUsuario().getUsuarioCidade());
		u.setUsuarioCpf(mapa.getUsuario().getUsuarioCpf());
		u.setUsuarioId(mapa.getUsuario().getUsuarioId());
		
		m.setUsuario(u);

		return m;
	}
	
	public int salvaCredito(List<BoletosGerados> boletos){
		return administracaoFacade.salvaCredito(boletos);
	}
	
	public Collection<BoletosGerados> buscaUsuarioCredito(String cliente, String tipo, boolean buscaSemAdiamento){
		Collection<BoletosGerados> boletosGerados = administracaoFacade.buscaUsuarioCredito(cliente, tipo, buscaSemAdiamento);
		Collection<BoletosGerados> ret = new ArrayList<BoletosGerados>();
		int i = 0;
		for (Iterator<BoletosGerados> iterator = boletosGerados.iterator(); iterator.hasNext();) {
			if(i < 3){
				BoletosGerados boletosGerados2 = (BoletosGerados) iterator.next();
				BoletosGerados bg = new BoletosGerados();
				Usuario usr = new Usuario();
				usr.setUsuarioCpf(boletosGerados2.getUsuario().getUsuarioCpf());
				usr.setUsuarioNome(boletosGerados2.getUsuario().getUsuarioNome());
				usr.setUsuarioEndereco(boletosGerados2.getUsuario().getUsuarioEndereco());
				usr.setUsuarioComplementoEndereco(boletosGerados2.getUsuario().getUsuarioComplementoEndereco());
				bg.setUsuario(usr);
				
				bg.setDataTempVencimento(boletosGerados2.getDataTempVencimento());
				bg.setBoletosgeradosValorCreditoDebito(boletosGerados2.getBoletosgeradosValorCreditoDebito());
				bg.setBoletosgeradosId(boletosGerados2.getBoletosgeradosId());
				bg.setBoletosgeradosValor(boletosGerados2.getBoletosgeradosValor());
				bg.setBoletosgeradosMotivoCreditoDebito(boletosGerados2.getBoletosgeradosMotivoCreditoDebito());
				bg.setBoletosgeradosIdF2B(boletosGerados2.getBoletosgeradosIdF2B());
				ret.add(bg);
				i++;
			}else{
				break;
			}
		}
		return ret;
	}
	
	public Usuario buscaUsuario(Long id){
		WebContext context = WebContextFactory.get();
		Util.acoesGrava("buscaUsuario", "id-@-" + id, context.getHttpServletRequest());
		Usuario usuario = administracaoFacade.buscaUsuario(id);
		Usuario ret = Util.copiaPropriedades(usuario);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
		ret.setDataTemp(sdf.format(usuario.getUsuarioDtAtivacao()));
		return ret;
	}
	
	public String buscaMensagemCliente(Long id){
		return administracaoFacade.buscaMensagemCliente(id);
	}
	
	public Usuario buscaUsuarioPeloNome(String cliente){
		WebContext context = WebContextFactory.get();
		Util.acoesGrava("buscaUsuario", "nome-@-" + cliente, context.getHttpServletRequest());
		Usuario usuario = administracaoFacade.buscaUsuarioPeloNome(cliente);
		Usuario ret = Util.copiaPropriedades(usuario);
		if(ret != null && !ret.getUsuarioAdministrativo() && ret.getUsuarioDtAtivacao() != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			ret.setDataTemp(sdf.format(usuario.getUsuarioDtAtivacao()));
			return ret;
		}else if(ret != null){
			return ret;
		}else{
			return null;
		}
	}
	
	public Usuario buscaUsuarioPeloId(String cliente){
		try{
		WebContext context = WebContextFactory.get();
		Usuario usuario = administracaoFacade.buscaUsuarioPeloId(new Long(cliente));
		Util.acoesGrava("buscaUsuario", "id-@-" + cliente, context.getHttpServletRequest());
		Usuario ret = Util.copiaPropriedades(usuario);
		if(usuario != null && !usuario.getUsuarioAdministrativo()){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			ret.setDataTemp(sdf.format(usuario.getUsuarioDtAtivacao()));
		}
		return ret;
		}catch (Exception e) {
			return null;
		}
	}
	
	public Usuario buscaUsuarioPeloCPF(String cliente){
		WebContext context = WebContextFactory.get();
		Util.acoesGrava("buscaUsuario", "cpf-@-" + cliente, context.getHttpServletRequest());
		Usuario usuario = administracaoFacade.buscaUsuarioPeloCPF(cliente);
		Usuario ret = Util.copiaPropriedades(usuario);
		if(ret != null && !ret.getUsuarioAdministrativo() && ret.getUsuarioDtAtivacao() != null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
			ret.setDataTemp(sdf.format(usuario.getUsuarioDtAtivacao()));
			return ret;
		}else if(ret != null){
			return ret;
		}else{
			return null;
		}
	}
	
	public BoletosGerados buscaUsuarioPeloBoleto(String boleto){
		WebContext context = WebContextFactory.get();
		Util.acoesGrava("buscaUsuario", "boleto-@-" + boleto, context.getHttpServletRequest());
		BoletosGerados boletosGerados = administracaoFacade.buscaUsuarioPeloBoleto(boleto);
		
		context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		if(!usuario.getUsuarioAdministrativo()){
			if(usuario.getUsuarioId().compareTo(boletosGerados.getUsuario().getUsuarioId()) == 0){
				return boletosGerados;
			}else{
				return null;
			}
		}
		
		return boletosGerados;
	}
	
	public Usuario buscaUsuarioMAC(String mac){
		WebContext context = WebContextFactory.get();
		Util.acoesGrava("buscaUsuario", "mac-@-" + mac, context.getHttpServletRequest());
		Usuario usuario = administracaoFacade.buscaUsuario(mac);
		Usuario ret = Util.copiaPropriedades(usuario);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
		if(usuario.getUsuarioDtAtivacao() != null){
			ret.setDataTemp(sdf.format(usuario.getUsuarioDtAtivacao()));
		}
		return ret;
	}
	
	public Collection<Menu> carregaFormularioMenu(){
		Collection<Menu> menu =  administracaoFacade.getMenus();
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		boolean primeiroAcesso = false;
		if(req.getSession().getAttribute("PRIMEIRO_ACESSO") != null){
			primeiroAcesso = (Boolean) req.getSession().getAttribute("PRIMEIRO_ACESSO");
			req.getSession().removeAttribute("PRIMEIRO_ACESSO");
		}
		
		if(primeiroAcesso){
			for (Iterator<Menu> iterator = menu.iterator(); iterator.hasNext();) {
				Menu m = (Menu) iterator.next();
				m.setMenuChecked(true);
			}
		}
		return menu;
	}
	
	public List<TipoEquipamento> carregaFormularioEqp(){
		List<TipoEquipamento> menu =  administracaoFacade.carregaFormularioEqp();
		return menu;
	}
	
	public StatusEquipamento verificaTorre(long idCLiente){
		return administracaoFacade.verificaTorre(idCLiente);
	}
	
	public StatusCliente verificaMediaPerfomanceAP(long idCLiente){
		return administracaoFacade.verificaMediaPerfomanceAP(idCLiente);
	}
	
	public StatusCliente verificaAtualPerfomanceAP(long idCLiente){
		return administracaoFacade.verificaAtualPerfomanceAP(idCLiente);
	}
	
	public PlanosPacotes verificarPlanoCliente(long idCLiente){
		return administracaoFacade.verificarPlanoCliente(idCLiente);
	}
	
	public int gravaResposta(Long id, String descricao){
		return administracaoFacade.gravaResposta(id, descricao);
	}
	public int gravaDemandaPadrao(Long idCliente, String descricao, Long usuarioResponsavel){
		Usuario usr = new Usuario();
		usr.setUsuarioId(idCliente);

		Usuario usrResp = new Usuario();
		usrResp.setUsuarioId(usuarioResponsavel);
		
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Usuario usuarioAbriu = (Usuario) req.getSession().getAttribute("usuario");
		if(usuarioAbriu != null){
			Calendar calDataPrevista = GregorianCalendar.getInstance(new Locale("pt","br"));
			calDataPrevista.setTimeInMillis(System.currentTimeMillis());
			calDataPrevista.set(Calendar.DAY_OF_MONTH, calDataPrevista.get(Calendar.DAY_OF_MONTH) + 3);
			calDataPrevista.set(Calendar.HOUR_OF_DAY, 0);
			calDataPrevista.set(Calendar.MINUTE, 0);
			calDataPrevista.set(Calendar.SECOND, 0);
			calDataPrevista.set(Calendar.MILLISECOND, 0);
			
			Timestamp dataPrevista = new Timestamp(calDataPrevista.getTimeInMillis());
			Demandas dem = new Demandas();
			dem.setDemandasDataAbertura(new Timestamp(System.currentTimeMillis()));
			dem.setDemandasDescricao(descricao);
			dem.setDemandasDataPrevistaAtendimento(dataPrevista);
			dem.setUsuarioAbriu(usuarioAbriu);
			dem.setUsuarioSolicitante(usr);
			dem.setUsuarioResponsavel(usrResp);
			return administracaoFacade.gravaDemanda(dem);
		}else{
			return 0;
		}
	}
	
	public int gravaDemanda(long idCLiente, String descricao, int dia, int mes, int ano, int hora, int minuto, Long responsavel){
		Usuario usr = new Usuario();
		usr.setUsuarioId(idCLiente);

		Usuario usrResp = new Usuario();
		usrResp.setUsuarioId(responsavel);
		
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Usuario usuarioAbriu = (Usuario) req.getSession().getAttribute("usuario");
		
		Calendar calDataPrevista = GregorianCalendar.getInstance(new Locale("pt","br"));
		calDataPrevista.set(Calendar.DAY_OF_MONTH, dia);
		calDataPrevista.set(Calendar.MONTH, mes - 1);
		calDataPrevista.set(Calendar.YEAR, ano);
		calDataPrevista.set(Calendar.HOUR_OF_DAY, hora);
		calDataPrevista.set(Calendar.MINUTE, minuto);
		calDataPrevista.set(Calendar.SECOND, 0);
		calDataPrevista.set(Calendar.MILLISECOND, 0);
		if(DateUtils.aPrimeiraEhMaiorQueSegunda(calDataPrevista.getTime(), new Date(System.currentTimeMillis()))){
			Timestamp dataPrevista = new Timestamp(calDataPrevista.getTimeInMillis());
			Demandas dem = new Demandas();
			dem.setDemandasDataAbertura(new Timestamp(System.currentTimeMillis()));
			dem.setDemandasDescricao(descricao);
			dem.setDemandasDataPrevistaAtendimento(dataPrevista);
			dem.setUsuarioAbriu(usuarioAbriu);
			dem.setUsuarioSolicitante(usr);
			dem.setUsuarioResponsavel(usrResp);
			return administracaoFacade.gravaDemanda(dem);
		}else{
			return -1;
		}
	}
	
	public int finalizarDemanda(long idDemanda, String desc, String tpErro, int dia, int mes, int ano, int hora, int minuto){
		Usuario usuario;
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		usuario = (Usuario) req.getSession().getAttribute("usuario");
		return administracaoFacade.finalizarDemanda(idDemanda, desc, tpErro, usuario, dia, mes, ano, hora, minuto);
	}
	
	public int informaPagamentoEmMaoPeloIdBoleto(Long idBoleto, String valor){
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Usuario usuarioAbriu = (Usuario) req.getSession().getAttribute("usuario");
		
		return administracaoFacade.informaPagamentoEmMaoPeloIdBoleto(idBoleto, valor, usuarioAbriu);
	}
	
	public Resposta criaBoletoExtraUsuario(String cliente, String valor, String data, String tipoPesquisa, double multa, double desc, double juro, long dias){
		return administracaoFacade.criaBoletoExtraUsuario(cliente, valor, data, tipoPesquisa, multa, desc, juro, dias);
	}
	
	public int buscaRelatorioRendimento(int comboEstado, int comboPesquisa, String dataInicial, String dataFinal){
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		req.getSession().removeAttribute("boletos");
		req.getSession().removeAttribute("boletosPagos");

		req.getSession().setAttribute("comboEstado", comboEstado);
		req.getSession().setAttribute("comboPesquisa", comboPesquisa);
		req.getSession().setAttribute("dataInicial", dataInicial);
		req.getSession().setAttribute("dataFinal", dataFinal);
		
		List<BoletosGerados> boletos = administracaoFacade.buscaRelatorioRendimento(comboEstado, comboPesquisa, dataInicial, dataFinal);
		if(comboEstado == 1){
			req.getSession().setAttribute("boletosPagos", boletos);
		}else{
			req.getSession().setAttribute("boletos", boletos);
		}
		return 0;
	}
	
	public int buscaRelatorioTrafego(String dataInicial, String dataFinal, String cliente, int tpCliente){
		List<StatusCliente> status = new ArrayList<StatusCliente>();
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		req.getSession().removeAttribute("statusCliente");
		req.getSession().removeAttribute("statusSemCliente");
		req.getSession().removeAttribute("msgErro");
		
		req.getSession().setAttribute("dataInicial", dataInicial);
		req.getSession().setAttribute("dataFinal", dataFinal);
		req.getSession().setAttribute("cliente", cliente);
		req.getSession().setAttribute("tpCliente", tpCliente);

		if(dataInicial != null && !"".equalsIgnoreCase(dataInicial) && dataFinal != null && !"".equalsIgnoreCase(dataFinal)){
			if(cliente != null && !"".equalsIgnoreCase(cliente)){
				status = administracaoFacade.buscaRelatorioTrafego(dataInicial, dataFinal, cliente, tpCliente);
				for (Iterator<StatusCliente> iterator = status.iterator(); iterator.hasNext();) {
					StatusCliente statusCliente = (StatusCliente) iterator.next();
					try{
						Double ccq = new Double(statusCliente.getStatusclienteTxccq().replace("%", ""));
						statusCliente.setStatusclienteTxccq(UtilNumero.formataNumeroCasaDecimal(ccq) + "%");
		
						Double troug = new Double(statusCliente.getStatusclienteThroughput());
						statusCliente.setStatusclienteThroughput((UtilNumero.formataNumeroCasaDecimal(troug / 1024)) + "Mbps");
					}catch (Exception e) {
						System.out.println(e);
					}
				}
				req.getSession().setAttribute("statusCliente", status);
			}else{
				status = administracaoFacade.buscaRelatorioTrafegoSemCliente(dataInicial, dataFinal);
				for (Iterator<StatusCliente> iterator = status.iterator(); iterator.hasNext();) {
					StatusCliente statusCliente = (StatusCliente) iterator.next();
					try{
						Double ccq = new Double(statusCliente.getStatusclienteTxccq().replace("%", ""));
						statusCliente.setStatusclienteTxccq(UtilNumero.formataNumeroCasaDecimal(ccq) + "%");
		
						Double troug = new Double(statusCliente.getStatusclienteThroughput());
						statusCliente.setStatusclienteThroughput((UtilNumero.formataNumeroCasaDecimal(troug / 1024)) + "Mbps");
					}catch (Exception e) {
						System.out.println(e);
					}
				}
				req.getSession().setAttribute("statusSemCliente", status);
			}
		}else{
			req.getSession().setAttribute("msgErro", "Data obrigatória");
		}
		return 0;
	}
	
	public Demandas localizaDemanda(Long id){
		Demandas d = administracaoFacade.localizaDemanda(id);
		if(d != null){
			Demandas ret = new Demandas();
			ret.setUsuarioSolicitante(Util.copiaPropriedades(d.getUsuarioSolicitante()));
			
			ret.setDataAbertura(new SimpleDateFormat("dd/MM/yyyy").format(d.getDemandasDataAbertura()));
			ret.setDataPrevistaAtendimento(new SimpleDateFormat("dd/MM/yyyy").format(d.getDemandasDataPrevistaAtendimento()));
			if(d.getUsuarioResponsavel() != null){
				ret.setUsuarioResponsavel(new Usuario(d.getUsuarioResponsavel().getUsuarioNome()));
			}
			if(d.getDemandasDataEncerramento() != null){
				ret.setDataEncerramento(new SimpleDateFormat("dd/MM/yyyy").format(d.getDemandasDataEncerramento()));
			}
			ret.setUsuarioAbriu(new Usuario(d.getUsuarioAbriu().getUsuarioNome()));
			ret.setDemandasDescricaoEncerramento(d.getDemandasDescricaoEncerramento());
			ret.setDemandasDescricao(d.getDemandasDescricao());
			return ret;
		}else{
			return null;
		}
		
		
		
	}
	
	public int buscaRelatorioDemandas(String dataInicial, String dataFinal, String cliente, int tpCliente, int aberto){
		List<Demandas> dem = new ArrayList<Demandas>();
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		req.getSession().removeAttribute("demandaCliente");
		req.getSession().removeAttribute("demandaSemCliente");

		req.getSession().setAttribute("dataInicial", dataInicial);
		req.getSession().setAttribute("dataFinal", dataFinal);
		req.getSession().setAttribute("cliente", cliente);
		req.getSession().setAttribute("tpCliente", tpCliente);
		req.getSession().setAttribute("aberto", aberto);
		
		if(cliente == null || !"".equalsIgnoreCase(cliente)){
			dem = administracaoFacade.buscaRelatorioDemandas(dataInicial, dataFinal, cliente, tpCliente, aberto);
			req.getSession().setAttribute("demandaCliente", dem);
		}else{
			dem = administracaoFacade.buscaRelatorioDemandasSemCliente(dataInicial, dataFinal, aberto);
			for (Iterator<Demandas> iterator = dem.iterator(); iterator.hasNext();) {
				Demandas demandas = (Demandas) iterator.next();
				try{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
					if(demandas.getDemandasDataPrevistaAtendimento() != null){
						Timestamp time = demandas.getDemandasDataPrevistaAtendimento();
						demandas.setDataPrevistaAtendimento(sdf.format(new Date(time.getTime())));
					}
					
					if(demandas.getDemandasDataEncerramento() != null){
						Timestamp time = demandas.getDemandasDataEncerramento();
						demandas.setDataEncerramento(sdf.format(new Date(time.getTime())));
					}
				}catch (Exception e) {
					System.out.println(e);
				}
				
			}
			req.getSession().setAttribute("demandaSemCliente", dem);
		}
		return 0;
	}
	
	public int buscaRelatorioContatos(String dataInicial, String dataFinal, int aberto){
		List<Contato> c = new ArrayList<Contato>();
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		req.getSession().removeAttribute("relatorioContato");
		
		req.getSession().setAttribute("dataInicial", dataInicial);
		req.getSession().setAttribute("dataFinal", dataFinal);
		req.getSession().setAttribute("aberto", aberto);
		
		c = administracaoFacade.buscaRelatorioContato(dataInicial, dataFinal, aberto);
		for (Iterator<Contato> iterator = c.iterator(); iterator.hasNext();) {
			Contato contato = (Contato) iterator.next();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:MM:SS");
			Timestamp time = contato.getContatoDataEnvio();
			contato.setDataEnvio(sdf.format(new Date(time.getTime())) + " às " + sdf2.format(new Date(time.getTime())));
			contato.setFechar("Aberta");
		}

		req.getSession().setAttribute("relatorioContato", c);
		return 0;
	}
	
	public int gravaMapa(Mapa mapa){
		int ret = administracaoFacade.gravaMapa(mapa);
		return ret;
	}
	
	public int deletaMapa(Long id){
		return administracaoFacade.deletaMapa(id);
	}
	
	public Usuario buscaInfCliente(Long idMapa){
		Usuario u = administracaoFacade.buscaInfCliente(idMapa);
		Usuario ret = new Usuario();
		ret.setUsuarioNome(u.getUsuarioNome());
		ret.setUsuarioEndereco(u.getUsuarioEndereco());
		ret.setUsuarioCep(u.getUsuarioCep());
		ret.setUsuarioCidade(u.getUsuarioCidade());
		ret.setUsuarioTelefone1(u.getUsuarioTelefone1());
		ret.setUsuarioTelefone2(u.getUsuarioTelefone2());
		ret.setUsuarioBairro(u.getUsuarioBairro());
		ret.setUsuarioCpf(u.getUsuarioCpf());
		return ret;
	}
	
	public boolean verificaDataLimiteClientePagamentoAtrasado(Long idCliente){
		return administracaoFacade.verificaDataLimiteClientePagamentoAtrasado(idCliente);
	}
	
	public Usuario carregaCliente(){
		Usuario usuario;
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		usuario = (Usuario) req.getSession().getAttribute("usuario");
		usuario.setServidor(new Servidor());
		usuario.setDemandases(new HashSet<Demandas>());
		usuario.setPlanosPacotes(new PlanosPacotes());
		usuario.setEnderecoIp(new EnderecoIp());
		usuario.setBoletosGeradoses(new HashSet<BoletosGerados>());
		usuario.setContrato(administracaoFacade.buscaContratoParaUsuario(usuario));
		Usuario usr = Util.copiaPropriedades(usuario);
		return usr;
	}

	public List<ComboVO> carregaComboAno(){
		List<ComboVO> ret = new ArrayList<ComboVO>();
		Calendar cal = GregorianCalendar.getInstance(new Locale("pt","br"));
		cal.setTimeInMillis(System.currentTimeMillis());
		cal.set(Calendar.YEAR, (cal.get(Calendar.YEAR) - 2));
		for (int i = 0; i < 5; i++) {
			ComboVO anoMesVO = new ComboVO();
			anoMesVO.setValor("" + cal.get(Calendar.YEAR));
			anoMesVO.setId("" + cal.get(Calendar.YEAR));
			ret.add(anoMesVO);
			cal.set(Calendar.YEAR, (cal.get(Calendar.YEAR) + 1));
		}
		return ret;
	}

	public List<ComboVO> carregaComboMes(){
		List<ComboVO> ret = new ArrayList<ComboVO>();
		Calendar cal = GregorianCalendar.getInstance(new Locale("pt","br"));
		
		//o mes que será selecionado por default na combo
		ComboVO id = new ComboVO();
		id.setId("" + (cal.get(Calendar.MONTH) + 1));
		ret.add(id);
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM", new Locale("pt","br"));
		
		for (int i = 0; i < 12; i++) {
			ComboVO mesVO = new ComboVO();
			
			cal.set(Calendar.MONTH, i);
			mesVO.setId("" + i);
			mesVO.setValor(sdf.format(cal.getTime()));
			
			ret.add(mesVO);
		}
		
		
		return ret;
	}
	
	public List<ComboVO> carregaComboTipoLog(){
		List<ComboVO> ret = new ArrayList<ComboVO>();
		String val[] = Constantes.LOG_TIPO_RETORNO;
		for (int i = 0; i < val.length; i++) {
			ComboVO mesVO = new ComboVO();
			mesVO.setId(val[i]);
			mesVO.setValor(val[i]);
			ret.add(mesVO);
		}
		return ret;
	}
	
	public List<ComboVO> carregaComboTipoErro(){
		List<ComboVO> ret = administracaoFacade.carregaComboTipoErro();
		return ret;
	}
	
	public List<ComboVO> carregaComboDatasPagamento(){
		List<ComboVO> ret = new ArrayList<ComboVO>();
		String val[] = ConfigUtil.getInstance().getProperty("dias_vencimento_boleto", "8,20,28").split(",");
		if(val != null && val.length <= 1){
			val = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28".split(",");
		}
		for (int i = 0; i < val.length; i++) {
			ComboVO mesVO = new ComboVO();
			mesVO.setId(val[i]);
			mesVO.setValor(val[i] + " de cada mês");
			ret.add(mesVO);
		}
		return ret;
	}
	
	public List<ComboVO> carregaComboEstado(){
		List<ComboVO> ret = new ArrayList<ComboVO>();
		String val[] = ConfigUtil.getInstance().getProperty("unidades_federacao", "Distrito Federal-DF,Goiás-GO").split(",");
		for (int i = 0; i < val.length; i++) {
			ComboVO mesVO = new ComboVO();
			mesVO.setId(val[i].split("-")[1]);
			mesVO.setValor(val[i].split("-")[0]);
			ret.add(mesVO);
		}
		return ret;
	}
	
	public List<ComboVO> carregaComboTipoCusto(){
		List<ComboVO> ret = new ArrayList<ComboVO>();
		String val[] = Constantes.TIPO_GASTO;
		for (int i = 0; i < val.length; i++) {
			ComboVO mesVO = new ComboVO();
			mesVO.setId(val[i]);
			mesVO.setValor(val[i]);
			ret.add(mesVO);
		}
		return ret;
	}
	
	public int buscaLog(String tipo, String dataInicial, String dataFinal){
		List<LogMeganet> ret = new ArrayList<LogMeganet>();
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		req.getSession().removeAttribute("logmeganet");
		req.getSession().setAttribute("tipo-rel", tipo);
		req.getSession().setAttribute("dtInicial", dataInicial);
		req.getSession().setAttribute("dtFinal", dataFinal);
		
		if(dataInicial == null || !"".equalsIgnoreCase(dataInicial) &&
				dataFinal == null || !"".equalsIgnoreCase(dataFinal)){
			ret = administracaoFacade.buscaLog(dataInicial, dataFinal, tipo);
			if(ret != null && ret.size() > 0){
				for (Iterator<LogMeganet> iterator = ret.iterator(); iterator.hasNext();) {
					LogMeganet logMeganet = (LogMeganet) iterator.next();
					logMeganet.setLogDescricao(logMeganet.getLogDescricao().replaceAll(",", ", "));
				}
				req.getSession().setAttribute("logmeganet", ret);
				return 0;
			}else{
				return 1;
			}
		}else{
			return 1;
		}
	}
	
	public int buscaSituacaoCliente(boolean asm, boolean acm, boolean blo, boolean des){
		List<Usuario> tmp = new ArrayList<Usuario>();
		List<Usuario> ret = new ArrayList<Usuario>();
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		tmp = administracaoFacade.buscaSituacaoCliente(asm, acm, blo, des);
		if(tmp != null && tmp.size() > 0){
			for (Iterator<Usuario> iterator = tmp.iterator(); iterator.hasNext();) {
				Usuario usuario = (Usuario) iterator.next();
				Usuario usr = new Usuario();
				
				PlanosPacotes pp = new PlanosPacotes();
				if(usuario.getPlanosPacotes() != null){
					pp.setPlanospacotesNome(usuario.getPlanosPacotes().getPlanospacotesNome());
				}else{
					pp.setPlanospacotesNome(" ");
				}
				
				Servidor s = new Servidor();
				if(usuario.getServidor() != null){
					s.setServidorNome(usuario.getServidor().getServidorNome());
				}else{
					s.setServidorNome(" ");
				}
				
				usr.setUsuarioNome(usuario.getUsuarioNome());
				usr.setUsuarioTelefone2(usuario.getUsuarioTelefone2());
				usr.setPlanosPacotes(pp);
				usr.setServidor(s);
				usr.setUsuarioMac(usuario.getUsuarioMac());
				ret.add(usr);
			}
			req.getSession().setAttribute("listaestadocliente", ret);
			return 0;
		}else{
			req.getSession().setAttribute("listaestadocliente", new ArrayList<Usuario>());
			return 1;
		}
	}
	
	public int gravaCusto(String tp, String motivo, String valor, String data){
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Usuario usr = (Usuario) req.getSession().getAttribute("usuario");
		return administracaoFacade.gravaCusto(tp, motivo, valor, data, usr);
	}
	
	public String buscaRelatorioTrafegoInterface(String dataInicial, Long torre, int diario){
		return administracaoFacade.buscaRelatorioTrafegoInterface(dataInicial, torre, diario);
	}

	public List<EnderecoIp> buscaGrupos(Long combo){
		return administracaoFacade.buscaGrupos(combo);
	}
	
	public List<BoletosGerados> localizarBoletos(Long idCliente){
		return administracaoFacade.localizarBoletos(idCliente);
	}

	public boolean reenviarBoleto(Long idBol){
		return administracaoFacade.reenviarBoleto(idBol);
	}
	
	public boolean cancelarBoleto(Long idBol){
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Usuario usuarioAbriu = (Usuario) req.getSession().getAttribute("usuario");

		return administracaoFacade.cancelarBoleto(idBol, usuarioAbriu);
	}
	
	public List<Demandas> localizarDemandas(Long idCliente){
		return administracaoFacade.localizarDemandas(idCliente);
	}
	
	public int existeLogParaEsseArquivo(String arquivo){
		if(arquivo != null && !"".equalsIgnoreCase(arquivo)){
			String nome  = "";
			if(arquivo.contains("\\")){
				nome = arquivo.substring(arquivo.lastIndexOf("\\"), arquivo.length());
			}else{
				nome = arquivo;
			}
			boolean existe = boletoFacade.existeLogParaEsseArquivo(nome);
			if(existe){
				return 1;
			}else{
				return 0;
			}
		}else{
			return 2;
		}
	}
	
	public Usuario buscaCEP(String cep){
		return administracaoFacade.buscaCEP(cep.replace("-", ""));
	}
	public int salvaTorre(Torre t){
		return administracaoFacade.salvaTorre(t);
	}
	public int salvaServidor(Servidor t){
		return administracaoFacade.salvaServidor(t);
	}
	public List<Avisos> carregaAvisos(){
		return administracaoFacade.carregaAvisos();
	}
	public Avisos carregaAviso(Long id){
		return administracaoFacade.carregaAviso(id);
	}
	public boolean salvaAviso(Avisos aviso, List<String> opcoes, int opcEscolhida){
		return administracaoFacade.salvaAviso(aviso, opcoes, opcEscolhida);
	}
	public boolean testarConexao(String ip, int porta){
		return administracaoFacade.testarConexao(ip, porta);
	}
	public String buscaString(int i){
		return Constantes.arrayAjuda(i);
	}
	public Resposta adiaBoletoCliente(String tpPesquisa, String cliente, String data, String idBol){
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Usuario usrLogado = (Usuario) req.getSession().getAttribute("usuario");
		
		List<JBoletoBean> jbb = boletoFacade.getBoletoByUsuarioMes(cliente, tpPesquisa, 0, 0, data, usrLogado, idBol);
		if(jbb != null && jbb.size() > 0){
			return new Resposta(false, "Adiar boleto", "Boleto adiado com sucesso.");
		}else{
			return new Resposta(true, "Adiar boleto", "Ocorreu um erro ao adiar o boleto.\nVerifique a data.\nSe o problema persistir contate o administrador.");
		}
	}
	public Resposta adiaBoletoPorDataCliente(String ini, String fim, String dataAdia){
		try{
			WebContext context = WebContextFactory.get();
			HttpServletRequest req = context.getHttpServletRequest();
			Usuario usrLogado = (Usuario) req.getSession().getAttribute("usuario");
			
			SimpleDateFormat sdff = new SimpleDateFormat("dd/MM/yyyy");
			
			Calendar dtIni = new GregorianCalendar();
			Calendar dtFim = new GregorianCalendar();
			dtIni.setTime(sdff.parse(ini));
			dtFim.setTime(sdff.parse(fim));
			
			if(DateUtils.aPrimeiraEhMaiorQueSegunda(dtIni.getTime(), dtFim.getTime())){
				return new Resposta(true, "Adiar boleto", "Data inicial maior que a data final.");
			}
			
			Date dataNova = sdff.parse(dataAdia);
			Date dataFIM = sdff.parse(fim);
			
			if(DateUtils.obterIntervaloDiasComSinal(dataNova, dataFIM) <= 0){
				return new Resposta(true, "Adiar boleto", "A funcionalidade é para ADIAR, não para ANTECIPAR.");
			}
			
			List<JBoletoBean> jbb = boletoFacade.getBoletoByDataPagamento(dtIni, dtFim, dataAdia, usrLogado);
			if(jbb != null && jbb.size() > 0){
				return new Resposta(false, "Adiar boleto", "Boletos adiados com sucesso.");
			}else{
				return new Resposta(true, "Adiar boleto", "Ocorreu um erro ao adiar os boletos.\nVerifique a data.\nSe o problema persistir contate o administrador.");
			}

		}catch (Exception e) {
			return new Resposta(true, "Adiar boleto", "Não foi possível reconhecer a data.");
		}
	}
}
