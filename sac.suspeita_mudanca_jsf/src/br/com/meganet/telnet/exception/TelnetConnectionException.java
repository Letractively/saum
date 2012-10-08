package br.com.meganet.telnet.exception;

public class TelnetConnectionException extends Exception {

	private static final long serialVersionUID = 1L;

	public TelnetConnectionException(String msg){
		super(msg);
	}
	
	public TelnetConnectionException(String msg, Throwable t){
		super(msg,t);
	}
	
	public TelnetConnectionException(Exception e){
		super(e);
	}
}
