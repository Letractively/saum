package br.com.meganet.facebookAPI.facebook.bean.oauth;

import java.io.Serializable;

public class SignedRequest implements Serializable {

	private static final long serialVersionUID = 3247279431958421027L;
	private String algorithm;
	private long expires;
	private long issued_at;
	private String oauth_token;
	private String user_id;
	private User user;

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	public long getIssuedAt() {
		return issued_at;
	}

	public void setIssuedAt(long issuedAt) {
		this.issued_at = issuedAt;
	}

	public long getIssued_at() {
		return issued_at;
	}

	public void setIssued_at(long issued_at) {
		this.issued_at = issued_at;
	}

	public String getOauthToken() {
		return oauth_token;
	}

	public void setOauthToken(String oauthToken) {
		this.oauth_token = oauthToken;
	}

	public String getOauth_token() {
		return oauth_token;
	}

	public void setOauth_token(String oauth_token) {
		this.oauth_token = oauth_token;
	}

	public boolean isAlgorithm(String algorithm) {
		return this.algorithm.toUpperCase().equals(algorithm.toUpperCase());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUserId() {
		return user_id;
	}

	public void setUserId(String userId) {
		this.user_id = userId;
	}

}
