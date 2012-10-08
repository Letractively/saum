package br.com.meganet.facebookAPI.facebook;

import br.com.meganet.facebookAPI.facebook.bean.profile.Profile;

public class FacebookService {

	private Facebook facebook;
	
	public FacebookService(Facebook facebook) {
		this.facebook = facebook;
	}
	
	public Profile getProfile(String accessToken) throws FacebookException {
		String param = accessToken == null ? "" : "?" + accessToken;
		return facebook.jsonRequest("https://graph.facebook.com/me" + param, Profile.class);
	}
	
	public Profile getProfileByUserId(String userId) throws FacebookException {
		return facebook.jsonRequest("https://graph.facebook.com/" + userId, Profile.class);
	}
	
}
