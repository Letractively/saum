package br.com.meganet.exception;

import java.util.ArrayList;
import java.util.List;


public class SACSocketException extends InfraBOException {
	
	private static final long serialVersionUID = 1L;

	private String messageKey;
	private int tipoErro;
	private Object[] messageArgs;
	private Throwable rootCause;
	private List<Exception> exceptions = new ArrayList<Exception>();
	
	public static final int INSERCAO = 0;
	public static final int ALTERACAO = 1;
	public static final int EXCLUSAO = 2;
	public static final int MUDANCA_TORRE = 3;

	public SACSocketException() {
		super();
	}
	public SACSocketException(String message, Throwable cause) {
		super(message, cause);
	}
	public SACSocketException(Throwable cause) {
		super(cause);
	}
	public SACSocketException(final String messageKey){
		super(messageKey);
		this.messageKey = messageKey;
	}
	public SACSocketException(String messageKey, Object[] args) {
		this(messageKey);
		this.messageArgs = args;
	}

	public SACSocketException(int tipoErro, final String messageKey) {
		super(messageKey);
		this.tipoErro = tipoErro;
		this.messageKey = messageKey;
	}
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
	public int getTipoErro() {
		return tipoErro;
	}
	public void setTipoErro(int tipoErro) {
		this.tipoErro = tipoErro;
	}

}
