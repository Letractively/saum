package br.com.meganet.exception;

import java.util.ArrayList;
import java.util.List;


/**
 * PVCBDException - Excecoes de BD 
 * @version 1.0
 *
 */
public class GAPPermissionException extends InfraBOException {
	
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
	 * 
	 * @param messageKey
	 */
	public GAPPermissionException(final String messageKey){
		super(messageKey);
		this.messageKey = messageKey;
	}
	
	/**
	 * 
	 * @param messageKey
	 * @param args
	 */
	public GAPPermissionException(String messageKey, Object[] args) {
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
	public GAPPermissionException() {
		super();
	}

	/**
	 * Construtor
	 * 
	 * @param message
	 * @param cause
	 */
	public GAPPermissionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor
	 * 
	 * @param cause
	 */
	public GAPPermissionException(Throwable cause) {
		super(cause);
	}
	
	
}
