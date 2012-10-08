/**
 * Projeto: ASAP - Sistema de Reconciliacao Ativa (SRA)
 * 
 * Tipo : IPool
 */
package br.com.meganet.telnet.pool;

import java.util.Properties;

import br.com.meganet.telnet.connection.IConnection;
import br.com.meganet.telnet.exception.TelnetConnectionException;



/**
 * Interface do Pool.
 * 
 * @author Adson Costa
 * @since 08/09/2006
 */
public interface IPool {

	/**
	 * Metodo que retorna uma conexao.
	 * 
	 * @return Um IConnection implementado por um objeto de conexao.
	 * @throws ConnectionException
	 *             Excecao lancada quando ocorre erro no envio/retorno dos
	 *             comandos.
	 */
	public abstract IConnection getConnection() throws TelnetConnectionException;

	/**
	 * Metodo que recebe uma conexao.
	 * 
	 * @param Um
	 *            IConnection devolvido para o pool de conexoes.
	 * @throws DisconnectException
	 *             Excecao lancada quando ocorre erro no envio/retorno dos
	 *             comandos.
	 */
	public abstract void disconnection(IConnection con) throws TelnetConnectionException;

	/**
	 * Metodo que carrega a lista de conexao.
	 * 
	 * @param Um
	 *            Properties Passa a configuracao para a conexao.
	 * @throws ReconciliationException
	 *             Excecao lancada quando ocorre erro no envio/retorno dos
	 *             comandos.
	 */
	public void loadListPool(Properties properties) throws TelnetConnectionException;

}