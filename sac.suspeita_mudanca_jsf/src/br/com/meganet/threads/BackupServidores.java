package br.com.meganet.threads;

import java.util.TimerTask;

import br.com.meganet.socketParsers.ComandosMonitoramentoSocket;

/**
 * @author efren
 * 
 * Faz o backup dos servidor de internet e banco de dados
 * 
 */
public class BackupServidores extends TimerTask {

	private ComandosMonitoramentoSocket comandosMonitoramentoSocket;

	public void setComandosMonitoramentoSocket(ComandosMonitoramentoSocket comandosMonitoramentoSocket) {
		this.comandosMonitoramentoSocket = comandosMonitoramentoSocket;
	}

	@Override
	public void run() {
		comandosMonitoramentoSocket.realizaBackupServidores();
	}

}
