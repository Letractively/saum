/**
 * Project: ASAP - Sistema de Reconciliacao Ativa (SRA)
 * 
 * Tipo : TelnetConnection
 */
package br.com.meganet.telnet.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.net.telnet.TelnetClient;

import br.com.meganet.telnet.constantes.IConnectionConstants;
import br.com.meganet.telnet.constantes.IConstantes;
import br.com.meganet.telnet.exception.TelnetConnectionException;


/**
 * Classe responsavel por realizar uma conexao Socket(Telnet), enviar comandos e
 * esperar por um determinado retorno.
 * 
 * @author eric Silva Souza
 * @since 07/08/2006
 */
public class TelnetConnection implements ITelnetConnection {
	private TelnetClient telnet;
	private InputStream in;
	private PrintStream out;
	private StringBuffer virtualScreen;
	private Date beginDateConnection;
	private Date endDateConnection;
	private int readTimeout;
	private int waitTimeout;
	private final int TIMEOUT_RESET_TELNET = 5000;

	public static final String LOOPBACK_NAO_ATIVO = "0";
	public static final String LOOPBACK = "LOOPBACK";

	/**
	 * Metodo Construtor default da classe. Ele e responsavel por criar os
	 * objetos dos atributos telnet e virtualScreen da classe.
	 */
	public TelnetConnection() {
		telnet = new TelnetClient();
		virtualScreen = new StringBuffer();
		this.readTimeout = 0;
		this.waitTimeout = 0;
	}

	/**
	 * Metodo responsavel por enviar um comando para o telnet
	 * 
	 * @param send
	 *            String contendo o comando
	 * @throws TelnetConnectionException
	 */

	public void send(String send) throws TelnetConnectionException {
		try {
			out.println(send);
			out.flush();
		} catch (Exception e) {
			throw new TelnetConnectionException("Erro ao enviar o comando".concat(send).concat("."));

		}
	}

	/**
	 * Metodo responsavel por enviar um comando para o telnet, aguardando a
	 * resposta deste comando
	 * 
	 * @param send
	 *            String contendo o comando
	 * @return response String contendo a resposta do console a este comando
	 */
	public String sendCommand(String send) throws TelnetConnectionException {

		String response = "";
		try {
			telnet.setSoTimeout(this.waitTimeout);
			send(send);
			response = readStream();
		} catch (SocketException e) {
			throw new TelnetConnectionException(e);
		}
		return response;

	}

	/**
	 * Metodo responsavel por enviar um comando para o telnet, aguardando a
	 * resposta deste comando
	 * 
	 * @param send String contendo o comando time tempo de espera
	 * @return response String contendo a resposta do console a este comando
	 */
	public String sendCommand2(String send, long time) throws TelnetConnectionException {
		
		String response = "";
		try {
			telnet.setSoTimeout(this.waitTimeout);
			send(send);
			response = readStream(time);
		} catch (SocketException e) {
			throw new TelnetConnectionException(e);
		}
		return response;
		
	}
	
	/**
	 * Metodo que consome o Stream de bytes e o coloca em uma string, para
	 * exibicao na tela;
	 * 
	 * @return Stream com comando e resposta o comando Telnet
	 * @throws TelnetConnectionException
	 */
	public String readStream() throws TelnetConnectionException {
		try {
			Thread.sleep(5500);
		} catch (InterruptedException e1) {
		}

		StringBuffer sb = new StringBuffer();
		try {
			int tamanho;
			char ch;

			tamanho = this.in.available();
			while (tamanho > 0) {
				ch = (char) this.in.read();
				sb.append(ch);
				this.virtualScreen.append(ch);
				if (tamanho == 1) {
					return sb.toString();
				}
				tamanho--;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();

	}

	/**
	 * Metodo que consome o Stream de bytes e o coloca em uma string, para
	 * exibicao na tela;
	 * 
	 * @return Stream com comando e resposta o comando Telnet
	 * @throws TelnetConnectionException
	 */
	public String readStream(long time) throws TelnetConnectionException {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e1) {
		}
		
		StringBuffer sb = new StringBuffer();
		try {
			int tamanho;
			char ch;
			
			tamanho = this.in.available();
			while (tamanho > 0) {
				ch = (char) this.in.read();
				sb.append(ch);
				this.virtualScreen.append(ch);
				if (tamanho == 1) {
					return sb.toString();
				}
				tamanho--;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
		
	}
	
	/**
	 * Metodo que inicializa os valores dos parametros.
	 * 
	 * @param propertiesCommParams
	 *            Properties com as informacoes do NE.
	 * @throws IOException
	 *             Se o Socket não poduser ser aberto.
	 * @throws NumberFormatException
	 *             Se ocorrer um erro ao transformar os timeouts de String para
	 *             int.
	 */
	private void initializeParams(Properties propertiesCommParams) throws NumberFormatException, IOException {
		// Seta o timeout em millisegundos antes de estabelecer a
		// conexao
		if ((propertiesCommParams.getProperty(IConnectionConstants.OPEN_TIMEOUT) != null)
				&& (!"".equals(propertiesCommParams.getProperty(IConnectionConstants.OPEN_TIMEOUT).trim()))) {
			telnet.setDefaultTimeout(1000 * Integer.parseInt(propertiesCommParams
					.getProperty(IConnectionConstants.OPEN_TIMEOUT)));
		}

		if ((propertiesCommParams.getProperty(IConnectionConstants.HOST_PORT) != null)
				&& (!"".equals(propertiesCommParams.getProperty(IConnectionConstants.HOST_PORT).trim()))) {
			// Abre a conexao Telnet passando o host e a porta
			this.telnet.connect(propertiesCommParams.getProperty(IConnectionConstants.HOST_IPADDR), Integer
					.parseInt(propertiesCommParams.getProperty(IConnectionConstants.HOST_PORT)));
		} else {
			// Abre a conexao Telnet passando o host e a porta
			this.telnet.connect(propertiesCommParams.getProperty(IConnectionConstants.HOST_IPADDR));

		}

		// Seta o timeout em millisegundos depois que a conexao foi
		// estabelecida
		if ((propertiesCommParams.getProperty(IConnectionConstants.READ_TIMEOUT) != null)
				&& (!"".equals(propertiesCommParams.getProperty(IConnectionConstants.READ_TIMEOUT).trim()))) {
			telnet.setSoTimeout(1000 * Integer.parseInt(propertiesCommParams
					.getProperty(IConnectionConstants.READ_TIMEOUT)));
			this.readTimeout = telnet.getSoTimeout();
		}

		if ((propertiesCommParams.getProperty(IConnectionConstants.WAIT_TIMEOUT) != null)
				&& (!"".equals(propertiesCommParams.getProperty(IConnectionConstants.WAIT_TIMEOUT).trim()))) {
			this.waitTimeout = 1000 * Integer.parseInt(propertiesCommParams
					.getProperty(IConnectionConstants.WAIT_TIMEOUT));
		}

	}

	public void connect(Properties propertiesCommParams) throws TelnetConnectionException {
		String prompt = propertiesCommParams.getProperty(IConnectionConstants.PROMPT);
		try {
			String loopBack = propertiesCommParams.getProperty(LOOPBACK);
			if ((loopBack == null) || (loopBack.equals(LOOPBACK_NAO_ATIVO))) {
				this.beginDateConnection = new Date();
				if (!this.telnet.isConnected()) {
					initializeParams(propertiesCommParams);
					// Pega o input e o output stream da conexao Telnet Aberta
					this.in = this.telnet.getInputStream();
					this.out = new PrintStream(this.telnet.getOutputStream());

					this.telnet.setTcpNoDelay(false);
				}
				this.endDateConnection = new Date();
			}
		} catch (Exception e) {
			String scream = getVirtualScreen();
			if (scream.trim().length() > 0) {
				int lastIndex = scream.trim().length() - 1;
				String lastCaracter = String.valueOf(scream.trim().charAt(lastIndex));
				if (!prompt.equals(lastCaracter) && !":".equals(lastCaracter)) {
					throw new TelnetConnectionException(IConstantes.MSG_PROMPT_DIVERGENTE_GERENCIA);

				}
				throw new TelnetConnectionException("Erro ao conectar na maquina Host. login: "
						+ propertiesCommParams.getProperty(IConnectionConstants.HOST_PORT));

			} else {
				throw new TelnetConnectionException("Erro ao conectar na maquina Host. login: "
						+ propertiesCommParams.getProperty(IConnectionConstants.HOST_PORT));
			}
		}

	}

	public String connectStream(Properties propertiesCommParams, TelnetConnection tc) throws TelnetConnectionException {
		tc.connect(propertiesCommParams);
		return readStream();
	}

	public void disconnect() throws TelnetConnectionException {
		try {
			if (this.telnet.isConnected()) {
				this.telnet.disconnect();
			}
			this.in = null;
			this.out = null;

		} catch (Exception e) {
			throw new TelnetConnectionException("Erro ao desconectar na maquina de gerencia");
		}
	}

	public String getVirtualScreen() {
		return this.virtualScreen.toString();
	}

	public Date getBeginDateConnection() {
		return this.beginDateConnection;
	}

	public Date getEndDateConnection() {
		return this.endDateConnection;
	}

	public void resetConnection() {
		try {
			telnet.setSoTimeout(this.TIMEOUT_RESET_TELNET);
			send("echo");
		} catch (Exception e) {
		} finally {
			try {
				telnet.setSoTimeout(this.readTimeout);
			} catch (Exception e) {
			}
		}
		this.virtualScreen.setLength(0);
	}

	public TelnetClient getTelnet() {
		return telnet;
	}
}