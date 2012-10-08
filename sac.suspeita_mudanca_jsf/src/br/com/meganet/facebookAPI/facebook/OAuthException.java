package br.com.meganet.facebookAPI.facebook;

public class OAuthException extends FacebookException {
	
	private static final long serialVersionUID = 1L;
	
	private String type;

	public OAuthException(String message) {
		super(message);
	}

	public OAuthException(String message, Throwable cause) {
		super(message, cause);
	}

	public OAuthException(String type, String message, Throwable cause) {
		super(message, cause);
		this.type = type;
	}

	public OAuthException(String type, String message) {
		super(message);
		this.type = type;
	}

	public String getType() {
		return type;
	}

}
