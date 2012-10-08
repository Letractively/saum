package br.com.meganet.facebookAPI.facebook;

import java.security.GeneralSecurityException;
import java.util.Arrays;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import br.com.meganet.facebookAPI.facebook.bean.oauth.SignedRequest;

public class OAuth {

	private Facebook facebook;
	
	public OAuth(Facebook facebook) {
		this.facebook = facebook;
	}

	private byte[] base64Decode(String encoded) {
		return Base64.decodeBase64(encoded.replace("-_", "+/"));
	}

	private byte[] hmac(String key, String input) throws OAuthException {
		try {
			SecretKeySpec sk = new SecretKeySpec(key.getBytes(), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(sk);
			return mac.doFinal(input.getBytes());
		} catch (GeneralSecurityException e) {
			throw new OAuthException("Nao foi possivel gerar o hash HmacSHA256", e);
		}
	}

	public SignedRequest parseSignedRequest(String signed_request) throws OAuthException {
		String[] values = StringUtils.split(signed_request, '.');
		String signed = values[0];
		String payload = values[1];
		
		byte[] hash = base64Decode(signed);
		String json = new String(base64Decode(payload));

		SignedRequest signedRequest = facebook.toBean(json, SignedRequest.class);
		
		if (!signedRequest.isAlgorithm("HMAC-SHA256")) {
			throw new OAuthException("Unknown algorithm. Expected HMAC-SHA256");
		}

		if (!Arrays.equals(hash, hmac(facebook.getAppSecret(), payload))) {
			throw new OAuthException("Bad Signed JSON signature!");
		}
		
		return signedRequest;
	}
	
	// authentication, app authorization
	public String getLoginUrl() {
		return "https://www.facebook.com/dialog/oauth" +
			"?client_id=" + facebook.getAppId() +
			"&redirect_uri=" + facebook.getAppCanvas() + 
			"&scope=" + StringUtils.join(facebook.getPermissions(), ",");
	}

	//app authentication
	public String getAccessToken(String authCode) throws FacebookException {
		String url = "https://graph.facebook.com/oauth/access_token" +
			"?client_id=" + facebook.getAppId() +
			"&redirect_uri=" + facebook.getAppCanvas() +
			"&client_secret=" + facebook.getAppSecret() +
			"&code=" + authCode;
		return facebook.request(url);		
	}

}
