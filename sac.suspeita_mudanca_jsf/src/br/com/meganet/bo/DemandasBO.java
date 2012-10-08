package br.com.meganet.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.DemandasDAO;
import br.com.meganet.hbm.DAO.DominiosDAO;
import br.com.meganet.hbm.DAO.StatusClienteDAO;
import br.com.meganet.hbm.DAO.UsuarioDAO;
import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.ComboVO;
import br.com.meganet.hbm.vo.Demandas;
import br.com.meganet.hbm.vo.Dominios;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;


public class DemandasBO {
	
	private DemandasDAO demandasDAO;
	private UsuarioDAO usuarioDAO;
	private DominiosDAO dominiosDAO;
	private BoletoBO boletoBO;

	public void setBoletoBO(BoletoBO boletoBO) {
		this.boletoBO = boletoBO;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	public void setDemandasDAO(DemandasDAO demandasDAO) {
		this.demandasDAO = demandasDAO;
	}
	public void setDominiosDAO(DominiosDAO dominiosDAO) {
		this.dominiosDAO = dominiosDAO;
	}
	public int gravaDemanda(Demandas dem) {
		demandasDAO.attachDirty(dem);
		return dem.getDemandasId().intValue();
	}
	
	public List<Demandas> adiquirirDemandas() throws GAPBDException{
		List<Demandas> ret = new ArrayList<Demandas>();
		ret = demandasDAO.findByCriteria(
				Expression.isNull(DemandasDAO.DATA_ENCERRAMENTO));
		return ret;
	}

	public Demandas adiquirirDemandaPeloID(Long id) throws GAPBDException{
		Demandas ret = demandasDAO.findById(id);
		return ret;
	}
	
	public List<Demandas> adiquirirTodasDemandas(Usuario usuario) throws GAPBDException{
		List<Demandas> ret = new ArrayList<Demandas>();
		ret = demandasDAO.findByCriteria(
				Expression.eq(DemandasDAO.USUARIO_SOLICITANTE, usuario));
		return ret;
	}
	
	public int finalizarDemanda(long idDemanda, String desc, String tpErro, Usuario usuario, int dia, int mes, int ano, int hora, int minuto) throws Exception {
		Demandas demandas = demandasDAO.findById(idDemanda);
		demandas.setUsuarioFechou(usuario);
		demandas.setDemandasDescricaoEncerramento(desc);
		demandas.setDemandasTipoErro(tpErro);
		List<Dominios> dons = dominiosDAO.findByCriteria(Expression.eq(DominiosDAO.CHAVE, tpErro), Expression.eq(DominiosDAO.TIPO, Constantes.TIPOS_ERRO));
		if(dons != null && dons.size() > 0){
			if(dons.get(0).getDominiosTratamentoEspecial()){
				List<BoletosGerados> boletos = boletoBO.buscaProximosBoletosParaInserirCredito(new Usuario(demandas.getUsuarioSolicitante().getUsuarioId()));
				List<Dominios> dominios = dominiosDAO.findByCriteria(Expression.eq(DominiosDAO.TIPO, Constantes.VALOR_VISITA_IMPRODUTIVA));
				if(boletos != null && boletos.size() > 0 && dominios != null && dominios.size() > 0){
					String valorVisita = dominios.get(0).getDominiosChave();
					if(valorVisita != null && !"".equalsIgnoreCase(valorVisita) && !"0.00".equalsIgnoreCase(valorVisita) && ConfigUtil.getInstance().getBooleanProperty("adiciona_resquicio", false)){
						BoletosGerados bg = boletos.get(0);
						BoletosGerados bolASerAlterado = new BoletosGerados();
						
						bolASerAlterado.setBoletosgeradosValorCreditoDebito(valorVisita);
						bolASerAlterado.setBoletosgeradosId(bg.getBoletosgeradosId());
						bolASerAlterado.setBoletosgeradosValorCreditoDebito(bg.getBoletosgeradosValor());
						bolASerAlterado.setBoletosgeradosMotivoCreditoDebito(demandas.getDemandasDescricaoEncerramento());
						
						List<BoletosGerados> envio = new ArrayList<BoletosGerados>();
						envio.add(bolASerAlterado);
						boletoBO.salvaCredito(envio);
					}
				}else{
					return 2;
				}
			}
		}
		
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.DAY_OF_MONTH, dia);
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, ano);
		cal.set(Calendar.HOUR_OF_DAY, hora);
		cal.set(Calendar.MINUTE, minuto);
		cal.set(Calendar.SECOND, 0);
		
		demandas.setDemandasDataEncerramento(new Timestamp(cal.getTimeInMillis()));
		demandasDAO.attachDirty(demandas);
		return 0;
	}

	public List<Demandas> buscaRelatorioDemandas(String cliente, int tpCliente, Timestamp dataInicial, Timestamp dataFinal, int aberto) throws GAPBDException {
		DetachedCriteria findCriteria = DetachedCriteria.forClass(demandasDAO.getPojoClass());
		List<Demandas> ret = new ArrayList<Demandas>();
		Usuario usuario = new Usuario();
		
		switch (tpCliente) {
		case 1:
			usuario = usuarioDAO.findByProperty(UsuarioDAO.USUARIO_CPF, cliente).get(0);
			findCriteria.add(Expression.eq(StatusClienteDAO.USUARIO, usuario));
			break;
		case 2:
			usuario = usuarioDAO.findByProperty(UsuarioDAO.USUARIO_NOME, cliente).get(0);
			findCriteria.add(Expression.eq(DemandasDAO.USUARIO_SOLICITANTE, usuario));
			break;
		}
		
		switch (aberto) {
		case 1:
			findCriteria.add(Expression.isNull(DemandasDAO.DATA_ENCERRAMENTO));
			break;
		case 2:
			findCriteria.add(Expression.isNotNull(DemandasDAO.DATA_ENCERRAMENTO));
			break;
		}
		
		findCriteria.add(Expression.and(Expression.ge(DemandasDAO.DATA_ABERTURA, dataInicial), 
				Expression.le(DemandasDAO.DATA_ABERTURA, dataFinal)));
		ret = demandasDAO.findByDetachedCriteria(findCriteria);
		return ret;
	}
	
	public List<Demandas> buscaRelatorioDemandasSemCliente(Timestamp dataInicial, Timestamp dataFinal, int aberto) throws GAPBDException {
		DetachedCriteria findCriteria = DetachedCriteria.forClass(demandasDAO.getPojoClass());
		List<Demandas> ret = new ArrayList<Demandas>();
		
		switch (aberto) {
		case 1:
			findCriteria.add(Expression.isNull(DemandasDAO.DATA_ENCERRAMENTO));
			break;
		case 2:
			findCriteria.add(Expression.isNotNull(DemandasDAO.DATA_ENCERRAMENTO));
			break;
		}
		findCriteria.add(Expression.and(Expression.ge(DemandasDAO.DATA_ABERTURA, dataInicial),
				Expression.le(DemandasDAO.DATA_ABERTURA, dataFinal)));
		ret = demandasDAO.findByDetachedCriteria(findCriteria);
		return ret;
	}
	public List<ComboVO> carregaComboTipoErro() throws GAPBDException {
		List<Dominios> valores = dominiosDAO.findByCriteria(Order.asc(DominiosDAO.CHAVE), Expression.eq(DominiosDAO.TIPO, Constantes.TIPOS_ERRO));
		List<ComboVO> ret = new ArrayList<ComboVO>();
		for (Iterator<Dominios> iterator = valores.iterator(); iterator.hasNext();) {
			Dominios dominios = (Dominios) iterator.next();
			ComboVO vo = new ComboVO();
			vo.setId(dominios.getDominiosValor());
			vo.setValor(dominios.getDominiosValor());
			ret.add(vo);
		}
		return ret;
	}
	public Demandas localizaDemanda(Long id) throws GAPBDException{
		Demandas d = demandasDAO.findById(id);
		return d;
	}

}
