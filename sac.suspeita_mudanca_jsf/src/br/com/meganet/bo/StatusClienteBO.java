package br.com.meganet.bo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.meganet.auditoria.BarraProgresso;
import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.StatusClienteDAO;
import br.com.meganet.hbm.DAO.UsuarioDAO;
import br.com.meganet.hbm.vo.StatusCliente;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.DateUtils;
import br.com.meganet.util.Logger;
import br.com.meganet.util.UtilNumero;

public class StatusClienteBO {
	private static Logger log = new Logger(StatusClienteBO.class);

	BarraProgresso bp = new BarraProgresso();
	private StatusClienteDAO statusClienteDAO;
	private UsuarioDAO usuarioDAO;
	
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	public void setStatusClienteDAO(StatusClienteDAO statusClienteDAO) {
		this.statusClienteDAO = statusClienteDAO;
	}


	public StatusCliente verificaMediaPerfomanceAP(long idCLiente) {
		Usuario usr = new Usuario();
		usr.setUsuarioId(idCLiente);
		List<StatusCliente> listaStatus = statusClienteDAO.findByProperty(StatusClienteDAO.USUARIO, usr);
		
		Double txRate = new Double(0);
		Double rxRate = new Double(0);
		Double ccq = new Double(0);
		Double signal = new Double(0);
		Double througput = new Double(0);
		Timestamp ultimaVerificacao = new Timestamp(0);

		int cont = 0;
		for (Iterator<StatusCliente> iterator = listaStatus.iterator(); iterator.hasNext();) {
			try {
				StatusCliente statusCliente = (StatusCliente) iterator.next();
				cont++;
				String tx = statusCliente.getStatusclienteTxrate().replace("Mbps-SP", "").replace("Mbps", "").replace("\"", "").replace("/", "");
				double tmpTxRate = Double.parseDouble(tx);
				String rx = statusCliente.getStatusclienteRxrate().replace("Mbps-SP", "").replace("Mbps", "").replace("\"", "").replace("/", "");
				double tmpRxRate = Double.parseDouble(rx);
				double tmpCcq = Double.parseDouble(statusCliente.getStatusclienteTxccq().replace("\"", "").replace("%", "").replace("/", ""));
				double tmpThrougput = Double.parseDouble(statusCliente.getStatusclienteThroughput());
				double tmpSignal = 0D;
				try{
					tmpSignal = Double.parseDouble(statusCliente.getStatusclienteSignalstrength().substring(0, statusCliente.getStatusclienteSignalstrength().indexOf("dBm")));
				}catch (Exception e) {
					tmpSignal = Double.parseDouble(statusCliente.getStatusclienteSignalstrength());
				}

				txRate += tmpTxRate;
				rxRate += tmpRxRate;
				ccq += tmpCcq;
				througput += tmpThrougput;
				signal += tmpSignal;

				if (DateUtils.aPrimeiraEhMaiorQueSegunda(new Date(statusCliente.getStatusclienteDatamedicao().getTime()), new Date(ultimaVerificacao.getTime()))) {
					ultimaVerificacao = statusCliente.getStatusclienteDatamedicao();
				}
			} catch (Exception e) {
				e.printStackTrace();
				log.erroSemEmail("Erro ao calcular perfomance cliente, mas vai continuar...\n" + e.getMessage());
			}

		}
		txRate = txRate / cont;
		rxRate = rxRate / cont;
		ccq = ccq / cont;
		througput = througput / cont;
		signal = signal / cont;
		
		String strTxRate = txRate.intValue() + "";
		String strRxRate = rxRate.intValue() + "";
		String strCcq = ccq.intValue() + "";
		String strThrougput = UtilNumero.formataNumeroCasaDecimal(througput / new Double("1000")) + "Mbps";
		String strSignal = signal.intValue() + "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
		
		StatusCliente ret = new StatusCliente();
		ret.setStatusclienteTxrate(strTxRate + "Mbps-SP");
		ret.setStatusclienteRxrate(strRxRate + "Mbps-SP");
		ret.setStatusclienteTxccq(strCcq);
		ret.setStatusclienteThroughput(strThrougput);
		ret.setStatusclienteDatamedicao(ultimaVerificacao);
		ret.setStatusclienteSignalstrength(strSignal);
		ret.setData(sdf.format(ret.getStatusclienteDatamedicao()));
		return ret;
	}


	public List<StatusCliente> buscaRelatorioTrafego(String cliente, int tpCliente, Timestamp dataInicial, Timestamp dataFinal) throws GAPBDException {
		DetachedCriteria findCriteria = DetachedCriteria.forClass(statusClienteDAO.getPojoClass());
		Usuario usuario = new Usuario();
		List<StatusCliente> statusRet = new ArrayList<StatusCliente>();
		
		switch (tpCliente) {
		case 1:
			usuario = usuarioDAO.findByProperty(UsuarioDAO.USUARIO_CPF, cliente).get(0);
			findCriteria.add(Expression.eq(StatusClienteDAO.USUARIO, usuario));
			break;
		case 2:
			usuario = usuarioDAO.findByProperty(UsuarioDAO.USUARIO_NOME, cliente).get(0);
			findCriteria.add(Expression.eq(StatusClienteDAO.USUARIO, usuario));
			break;
		}
		if(dataInicial != null && dataFinal != null){
			findCriteria.add(Expression.and(Expression.ge(StatusClienteDAO.STATUSCLIENTE_DATAMEDICAO, dataInicial),
					Expression.le(StatusClienteDAO.STATUSCLIENTE_DATAMEDICAO, dataFinal)));
		}
		statusRet = statusClienteDAO.findByDetachedCriteria(findCriteria);
			
		return statusRet;
			
	}
	
	public List<StatusCliente> buscaRelatorioTrafegoSemCliente(Timestamp dataInicial, Timestamp dataFinal) throws GAPBDException {
		List<StatusCliente> statusRet = new ArrayList<StatusCliente>();
		
		List<Usuario> lista = usuarioDAO.findByCriteria(
				Expression.or(
						Expression.eq(UsuarioDAO.USUARIO_BLOQUEADO, Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM),
						Expression.eq(UsuarioDAO.USUARIO_BLOQUEADO, Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM)));
		
		for (Iterator<Usuario> iterator = lista.iterator(); iterator.hasNext();) {
			Usuario usuario2 = (Usuario) iterator.next();
			Usuario usuario = new Usuario();
			usuario.setUsuarioId(usuario2.getUsuarioId());
			List<StatusCliente> status = statusClienteDAO.findByCriteria(Order.asc(StatusClienteDAO.STATUSCLIENTE_DATAMEDICAO)
					, Expression.and(Expression.ge(StatusClienteDAO.STATUSCLIENTE_DATAMEDICAO, dataInicial),
							Expression.le(StatusClienteDAO.STATUSCLIENTE_DATAMEDICAO, dataFinal))
					, Expression.eq(StatusClienteDAO.USUARIO, usuario));
			
			if(status != null && status.size() > 0){
				statusRet.add(calculaMediaTrafegoUsuario(status));
			}
		}

		return statusRet;
	}
	
	private StatusCliente calculaMediaTrafegoUsuario(List<StatusCliente> status) {
		StatusCliente ret = new StatusCliente();
		Double throughput = new Double(0);
		Double ccq = new Double(0);
		String txRate = "";
		String rxRate = "";
		String sinal = "";
		Timestamp medicao = new Timestamp(0);
		Usuario usuario = new Usuario();
		
		int cont = 1;
		
		for (Iterator<StatusCliente> iterator = status.iterator(); iterator.hasNext();) {
			StatusCliente statusCliente = (StatusCliente) iterator.next();
			throughput += new Double(statusCliente.getStatusclienteThroughput());
			if(statusCliente.getStatusclienteTxccq() != null && !"".equalsIgnoreCase(statusCliente.getStatusclienteTxccq())){
				ccq += new Double(statusCliente.getStatusclienteTxccq().replace("%", ""));
			}else{
				ccq+= new Double(0);
			}
			txRate = statusCliente.getStatusclienteTxrate();
			rxRate = statusCliente.getStatusclienteRxrate();
			sinal = statusCliente.getStatusclienteSignalstrength();
			medicao = statusCliente.getStatusclienteDatamedicao();
			usuario = statusCliente.getUsuario();
			cont++;
		}
		ret.setStatusclienteThroughput((throughput / cont) + "");
		ret.setStatusclienteTxccq((ccq / cont) + "");
		ret.setStatusclienteTxrate(txRate);
		ret.setStatusclienteRxrate(rxRate);
		ret.setStatusclienteSignalstrength(sinal);
		ret.setStatusclienteDatamedicao(medicao);
		ret.setUsuario(usuario);
		
		return ret;
	}

}
