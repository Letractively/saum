/**
 * Projeto: ASAP - Sistema de Reconciliacao Ativa (SRA)
 * 
 * Tipo : Pool10_142_184_34ProvisionConnection
 */

package br.com.meganet.telnet.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import br.com.meganet.telnet.connection.IConnection;
import br.com.meganet.telnet.connection.ITelnetConnection;
import br.com.meganet.telnet.connection.TelnetConnection;
import br.com.meganet.telnet.exception.TelnetConnectionException;



/**
 * Classe que controla a conexao de telnet.
 * 
 * @author Adson Costa
 * @since 08/09/2006
 */
public class PoolConexaoTelnet implements IPool {

	private static List<IConnection> poolList = null;
	private static PoolConexaoTelnet pool;
	private final int RECUPERA = 1;
	private final int ADICIONA = 2;
	private int cont;
	private Properties properties;
	private int maxLength;
	private int minLength;
	private int waitTime;

	/**
	 * Construtor.
	 * 
	 * @throws ReconciliationException.
	 */
	private PoolConexaoTelnet() throws TelnetConnectionException {
		loadParameter();
	}

	/**
	 * Carrega os parametros de configuracao do Pool.
	 * 
	 * @throws ReconciliationException.
	 */
	private void loadParameter() throws TelnetConnectionException {
		maxLength = 10;
		minLength = 1;
		waitTime = 100;
	}

	public void loadListPool(Properties properties) throws TelnetConnectionException {
		if (poolList == null) {
			poolList = new ArrayList<IConnection>();
			for (int i = 0; i < minLength; i++) {
				try {
					IConnection con = (IConnection) getObjectConnect(properties);
					if (con.getTelnet().isConnected()) {
						poolList.add(con);
					}
				} catch (Exception e) {
					throw new TelnetConnectionException(e.getMessage());
				}
			}
			setProperties(properties);
		}
	}

	/**
	 * Metodo que disponibiliza a instancia do telnet.
	 * 
	 * @param properties
	 *            do tipo Properties com os valores que serao utilizados na
	 *            conexao Telnet.
	 * @return IConnection Retorna a conexao.
	 * @throws ConnectionException
	 *             Excecao levantada quando não e possivel fazer a conexao.
	 */
	private IConnection getObjectConnect(Properties properties) throws TelnetConnectionException {
		TelnetConnection connection = new TelnetConnection();
		try {
			connection.connect(properties);
		} catch (Exception e) {
			throw new TelnetConnectionException(e.getMessage());
		}
		return connection;
	}

	/**
	 * Metodo que retorna o properties com os parametros da conexao.
	 * 
	 * @return Properties
	 */
	private Properties getProperties() {
		return properties;
	}

	/**
	 * Metodo que disponibiliza a instancia do objeto.
	 * 
	 * @throws ProvisioningException
	 *             Excecao levantada quando não e possivel instanciar a classe.
	 */
	public static PoolConexaoTelnet getInstancia() throws TelnetConnectionException {
		if (pool == null) {
			pool = new PoolConexaoTelnet();
		}
		return pool;
	}

	/**
	 * Metodo que faz o tratamento de disponibilizar e receber a conexao.
	 * 
	 * @return IConnection Retorna a conexao
	 * @param type
	 *            Um int que define se devolve ou recupera uma conexao;
	 * @param con
	 *            Um IConnection conexao devolvida;
	 * @throws ProvisioningException
	 *             Excecao levantada quando não e possivel instanciar a classe.
	 */
	private synchronized IConnection treatConnection(IConnection con, int type) throws TelnetConnectionException {

		if (type == RECUPERA) {

			if (poolList.size() == 0 && cont <= maxLength) {
				cont++;
				return getObjectConnect(getProperties());

			} else if (poolList.size() > 0) {

				ITelnetConnection connection = (ITelnetConnection) poolList.remove(0);
				if (!connection.getTelnet().isConnected()) {
					return treatConnection(con, RECUPERA);

				}
				return (IConnection) connection;
			}

		} else {
			if (poolList.size() < minLength) {
				poolList.add(con);
				cont--;
			} else {
				try {
					con.disconnect();
				} catch (Exception e) {
					throw new TelnetConnectionException(e.getMessage());
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see conexao.IPoolTelnetConnection#recuperaConexao()
	 */
	public IConnection getConnection() throws TelnetConnectionException {
		IConnection conection = null;

		try {
			while ((conection = treatConnection(null, RECUPERA)) == null) {
				Thread.sleep(waitTime);
			}
		} catch (Exception e) {
			throw new TelnetConnectionException(e.getMessage());
		}

		return conection;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see conexao.IPoolTelnetConnection#devolveConexao(java.sql.Connection)
	 */
	public void disconnection(IConnection con) throws TelnetConnectionException {
		try {
			treatConnection(con, ADICIONA);
		} catch (Exception e) {
			throw new TelnetConnectionException(e.getMessage());
		}
	}

	/**
	 * Carrega o properties
	 * 
	 * @param properties
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}