package br.com.meganet.threads;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimerTask;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.meganet.bo.EnviaEmailBO;
import br.com.meganet.hbm.DAO.StatusClienteDAO;
import br.com.meganet.hbm.DAO.UsuarioDAO;
import br.com.meganet.hbm.vo.StatusCliente;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.socketParsers.ComandosMonitoramentoSocket;
import br.com.meganet.util.BufferLog;
import br.com.meganet.util.Logger;

/**
 * @author efren
 * 
 * Verifica a perfomance de cada cliente que esteja navegando
 * no momento da verificacao.
 */
public class VerificaVelocidadeMedia extends TimerTask {

	private ComandosMonitoramentoSocket comandosMonitoramentoSocket;
	private StatusClienteDAO statusClienteDAO;
	private UsuarioDAO usuarioDAO;
	public void setStatusClienteDAO(StatusClienteDAO statusClienteDAO) {
		this.statusClienteDAO = statusClienteDAO;
	}
	public void setComandosMonitoramentoSocket(ComandosMonitoramentoSocket comandosMonitoramentoSocket) {
		this.comandosMonitoramentoSocket = comandosMonitoramentoSocket;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	private static Logger logger = new Logger(VerificaVelocidadeMedia.class);

	
	@Override
	public void run() {
		try{
			BufferLog bl = new BufferLog();
			bl.append("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			bl.append("----I N I C I O   T R A F E G O   C L I E N T E----");
			List<StatusCliente> status = comandosMonitoramentoSocket.buscarNivelClientes();
			Calendar calCria = GregorianCalendar.getInstance(new Locale("pt","br"));
			calCria.setTimeInMillis(System.currentTimeMillis());
			bl.append("===========================================");
			bl.append(calCria.getTime());
			bl.append("===========================================");

			for (Iterator<StatusCliente> iterator = status.iterator(); iterator.hasNext();) {
				StatusCliente statusCliente = (StatusCliente) iterator.next();
				bl.append("-------------------------------------------");
				bl.append("Usuario:         " + statusCliente.getUsuario().getUsuarioNome());
				bl.append("Signal Strength: " + statusCliente.getStatusclienteSignalstrength());
				bl.append("Throughput:      " + statusCliente.getStatusclienteThroughput());
				bl.append("TX CCQ:          " + statusCliente.getStatusclienteTxccq());
				bl.append("RX Rate:         " + statusCliente.getStatusclienteRxrate());
				bl.append("TX Rate:         " + statusCliente.getStatusclienteTxrate());
				bl.append("-------------------------------------------");
				
				if(statusCliente != null && statusCliente.getUsuario() != null && statusCliente.getUsuario().getUsuarioId() == 578L){
					List<StatusCliente> ultimoStatus = statusClienteDAO.findByCriteria(1,
							Order.desc(StatusClienteDAO.STATUSCLIENTE_DATAMEDICAO),
							Expression.eq(StatusClienteDAO.USUARIO, statusCliente.getUsuario()));
					if(ultimoStatus != null && ultimoStatus.size() > 0){
						int ultimoNivel = new Integer(ultimoStatus.get(0).getStatusclienteSignalstrength()).intValue();
						int atualNivel = new Integer(statusCliente.getStatusclienteSignalstrength()).intValue();
						
						int diferenca = ultimoNivel - atualNivel;
						if(diferenca > 5){
							Usuario usr = ultimoStatus.get(0).getUsuario();
							List<Usuario> usrAdm = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, new Boolean(true)));
							statusCliente.setUsuario(usr);
							EnviaEmailBO.enviaEMailQuedaBruscaDesinal(statusCliente, ultimoStatus.get(0), usrAdm);
						}
					}
				}
				try{
					statusClienteDAO.attachDirty(statusCliente);
				}catch (DataIntegrityViolationException e) {
					logger.erroSemEmail(e);
				}
			}
			Calendar cal = GregorianCalendar.getInstance(new Locale("pt","br"));
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 3);
			Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
			statusClienteDAO.deletePorData(timestamp);
			
			bl.append("===========================================");
			bl.append(new Date(System.currentTimeMillis()));
			bl.append("===========================================");
			bl.append("-------F I M   T R A F E G O   C L I E N T E-------\n");
			bl.append("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			bl.append("----I N I C I O   M O N I T O R A M E N T O  I N T E R F A C E----");
			comandosMonitoramentoSocket.monitoraInterfaces(bl);
			bl.append("----F I M   M O N I T O R A M E N T O  I N T E R F A C E----\n");
			logger.info(bl.toString());
		}catch (Exception e) {
			logger.erro("Erro geral na thread de monitoramento de cliente", e);
		}
	}
}
