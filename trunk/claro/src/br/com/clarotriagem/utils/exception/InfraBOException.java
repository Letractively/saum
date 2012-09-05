package br.com.clarotriagem.utils.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InfraBOException extends Exception {

	private static final long serialVersionUID = -5906366751918867034L;
	private String messageKey;
	private Object[] messageArgs;
	private Throwable rootCause;
	private List<Exception> exceptions = new ArrayList<Exception>();

	public InfraBOException() {
		super();
	}

	public InfraBOException(String message, Throwable cause) {
		super(message, cause);
	}

	public InfraBOException(Throwable cause) {
		super(cause);
	}
	
	public InfraBOException(String messageKey, Object[] args) {
		this(messageKey);
		this.messageArgs = args;
	}

	public InfraBOException(final String messageKey) {
		this.messageKey = messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public Object[] getMessageArgs() {
		return messageArgs;
	}

	public void setMessageArgs(Object[] messageArgs) {
		this.messageArgs = messageArgs;
	}

	public Throwable getRootCause() {
		return rootCause;
	}

	public List<Exception> getExceptions() {
		return Collections.unmodifiableList(this.exceptions);
	}
	

}
