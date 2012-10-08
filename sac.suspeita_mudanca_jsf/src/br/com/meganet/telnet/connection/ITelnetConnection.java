/**
 * Projeto: ASAP - Sistema de Reconciliacao Ativa (SRA)
 * 
 * Tipo : ITelnetConnection
 */
package br.com.meganet.telnet.connection;

import br.com.meganet.telnet.exception.TelnetConnectionException;


/**
 * Interface de conexao via Telnet.
 * 
 * @author Jailson
 */
public interface ITelnetConnection extends IConnection {



	public String sendCommand(String send) throws TelnetConnectionException;

	// public String send(String send) throws TelnetConnectionException;

}
