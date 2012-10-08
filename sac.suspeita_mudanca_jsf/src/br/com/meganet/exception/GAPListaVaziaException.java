package br.com.meganet.exception;


/**
 * PVCListaVaziaException - Excecoes de Listagem Vazia 
 * @version 1.0
 *
 */
public class GAPListaVaziaException extends InfraBOException {

	/**
	 * serial
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor
	 * 
	 */
	public GAPListaVaziaException() {
		super();
	}

	/**
	 * Construtor
	 * 
	 * @param message
	 * @param cause
	 */
	public GAPListaVaziaException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor
	 * 
	 * @param message
	 */
	public GAPListaVaziaException(String message) {
		super(message);
	}

	/**
	 * Construtor
	 * 
	 * @param cause
	 */
	public GAPListaVaziaException(Throwable cause) {
		super(cause);
	}

}
