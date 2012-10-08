package br.com.meganet.facebookAPI.facebook.bean.error;

import br.com.meganet.facebookAPI.facebook.FacebookException;
import br.com.meganet.facebookAPI.facebook.OAuthException;

public class ContentError {

	private Error error;

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public FacebookException createException() {
		String type = getError().getType();
		String message = getError().getMessage();
		if ("OAuthException".equals(type)) {
			return new OAuthException(type, message);
		}
		return new FacebookException(message);
	}
	
}
