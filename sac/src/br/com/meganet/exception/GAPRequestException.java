package br.com.meganet.exception;

import java.util.ArrayList;
import java.util.List;


/**
 * PVCRequestException - Excecoes de Request do Usuario 
 * @version 1.0
 *
 */
public class GAPRequestException extends InfraBOException {
	
	/**
	 * serial
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * key
	 */
	private String messageKey;

	/**
	 * args
	 */
	private Object[] messageArgs;

	/**
	 * causa raiz
	 */
	private Throwable rootCause;

	/**
	 * excecoes
	 */
	private List<Exception> exceptions = new ArrayList<Exception>();
	
	/**
	 * Construtor
	 * @param messageKey
	 */
	public GAPRequestException(final String messageKey){
		super(messageKey);
		this.messageKey = messageKey;
	}
	
	/**
	 * Construtor
	 * @param messageKey
	 * @param args
	 */
	public GAPRequestException(String messageKey, Object[] args) {
		this(messageKey);
		this.messageArgs = args;
	}

	//GETS E SETS
	public List<Exception> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<Exception> exceptions) {
		this.exceptions = exceptions;
	}

	public Object[] getMessageArgs() {
		return messageArgs;
	}

	public void setMessageArgs(Object[] messageArgs) {
		this.messageArgs = messageArgs;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public Throwable getRootCause() {
		return rootCause;
	}

	public void setRootCause(Throwable rootCause) {
		this.rootCause = rootCause;
	}

	/**
	 * Construtor
	 * 
	 */
	public GAPRequestException() {
		super();
	}

	/**
	 * Construtor
	 * 
	 * @param message
	 * @param cause
	 */
	public GAPRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor
	 * 
	 * @param cause
	 */
	public GAPRequestException(Throwable cause) {
		super(cause);
	}
	
	
}
