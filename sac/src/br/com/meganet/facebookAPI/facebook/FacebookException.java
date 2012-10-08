package br.com.meganet.facebookAPI.facebook;

public class FacebookException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FacebookException() {
		super();
	}

	public FacebookException(String message, Throwable cause) {
		super(message, cause);
	}

	public FacebookException(String message) {
		super(message);
	}

	public FacebookException(Throwable cause) {
		super(cause);
	}
	
}
