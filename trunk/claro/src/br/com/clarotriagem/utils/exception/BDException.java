package br.com.clarotriagem.utils.exception;

import java.util.ArrayList;
import java.util.List;


public class BDException extends InfraBOException {
	
	private static final long serialVersionUID = 1L;

	private String messageKey;

	private Object[] messageArgs;

	private Throwable rootCause;

	private List<Exception> exceptions = new ArrayList<Exception>();

	public BDException() {
		super();
	}

	public BDException(final String messageKey){
		super(messageKey);
		this.messageKey = messageKey;
	}
	
	public BDException(String messageKey, Object[] args) {
		this(messageKey);
		this.messageArgs = args;
	}

	public BDException(String message, Throwable cause) {
		super(message, cause);
	}

	public BDException(Throwable cause) {
		super(cause);
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

}
