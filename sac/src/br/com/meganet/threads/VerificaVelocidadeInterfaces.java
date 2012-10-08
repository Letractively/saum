package br.com.meganet.threads;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimerTask;

import br.com.meganet.hbm.DAO.InfServidorDAO;
import br.com.meganet.socketParsers.ComandosMonitoramentoSocket;
import br.com.meganet.util.BufferLog;
import br.com.meganet.util.Logger;

/**
 * @author efren
 * 
 * Verifica a velocidade media de cada interface
 */
public class VerificaVelocidadeInterfaces extends TimerTask {

	private static Logger logger = new Logger(VerificaVelocidadeInterfaces.class);

	private ComandosMonitoramentoSocket comandosMonitoramentoSocket;
	private InfServidorDAO infServidorDAO;
	public void setInfServidorDAO(InfServidorDAO infServidorDAO) {
		this.infServidorDAO = infServidorDAO;
	}
	public void setComandosMonitoramentoSocket(ComandosMonitoramentoSocket comandosMonitoramentoSocket) {
		this.comandosMonitoramentoSocket = comandosMonitoramentoSocket;
	}
	
	@Override
	public void run() {
		try{
			BufferLog bl = new BufferLog();
			bl.append("\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			bl.append("----I N I C I O   T R A F E G O   I N T E R F A C E----");
			Calendar calDel = GregorianCalendar.getInstance(new Locale("pt","br"));
			Calendar calCria = GregorianCalendar.getInstance(new Locale("pt","br"));
			calDel.setTimeInMillis(System.currentTimeMillis());
			calCria.setTimeInMillis(System.currentTimeMillis());
			bl.append("===========================================");
			bl.append(calCria.getTime());
			bl.append("===========================================");
			
			comandosMonitoramentoSocket.buscarTrafegoInterfaces(calCria, bl);
			calDel.set(Calendar.MONTH, calDel.get(Calendar.MONTH) - 2);
			Timestamp timestamp = new Timestamp(calDel.getTimeInMillis());
			infServidorDAO.deletePorData(timestamp);
			
			bl.append("===========================================");
			bl.append(new Date(System.currentTimeMillis()));
			bl.append("===========================================");
			bl.append("-------F I M   T R A F E G O   I N T E R F A C E-------\n");
			logger.info(bl.toString());
		}catch (Exception e) {
			logger.erro("Erro geral na thread de monitoramento de interfaces", e);
		}
	}
	
}
