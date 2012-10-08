package br.com.meganet.facebookAPI.infra.jee;

import java.io.Serializable;

import br.com.meganet.facebookAPI.facebook.bean.oauth.SignedRequest;
import br.com.meganet.facebookAPI.facebook.bean.profile.Profile;

public class UserLogged implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SignedRequest signedRequest;
	private Profile profile;
	private String accessToken;

	public SignedRequest getSignedRequest() {
		return signedRequest;
	}

	public void setSignedRequest(SignedRequest signedRequest) {
		this.signedRequest = signedRequest;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
