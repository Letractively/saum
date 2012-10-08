package br.com.meganet.facebookAPI.facebook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.math.NumberUtils;

import br.com.meganet.facebookAPI.facebook.bean.error.ContentError;
import br.com.meganet.facebookAPI.infra.json.SimplesPropertySetStrategy;
import br.com.meganet.util.ConfigUtil;

public class Facebook {
	
	private OAuth oauth;
	private FacebookService service;

	private Proxy proxy;
	
	public Facebook() {
		oauth = new OAuth(this);
		service = new FacebookService(this);
		initProxy();
	}
	
	private void initProxy() {
		String hostname = System.getProperty("http.proxyHost");
		int port = NumberUtils.toInt(System.getProperty("http.proxyPort"), 0);
		if (hostname == null || port == 0) {
			proxy = Proxy.NO_PROXY;
		} else {
			proxy = new Proxy(Type.HTTP, new InetSocketAddress(hostname, port));
		}
	}

	public String getApiKey() {
		return ConfigUtil.getInstance().getProperty("facebook_chave_api_aplicativo", "0");
	}

	public String getAppSecret() {
		return ConfigUtil.getInstance().getProperty("facebook_secret_aplicativo", "0");
	}

	public String getAppId() {
		return ConfigUtil.getInstance().getProperty("facebook_id_aplicativo", "0");
	}

	public List<String> getPermissions() {
		List<String> perm = new ArrayList<String>();
		perm.add(ConfigUtil.getInstance().getProperty("facebook_permission", "0"));
		return perm;
	}

	public String getAppCanvas() {
		return ConfigUtil.getInstance().getProperty("facebook_url_aplicativo", "0");
	}

	private String getContent(InputStream iStream) throws IOException {
		if (iStream == null) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int r;
		while ((r = iStream.read()) != -1) {
			baos.write(r);
		}
		return new String(baos.toByteArray());
	}
	
	public String request(String value) throws FacebookException {
		System.out.println("DEBUG " + this.getClass() + " - [REQUEST]  " + value);
		try {
			URL url = new URL(value);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection(proxy);
			conn.setConnectTimeout(5000);
	
			if (conn.getResponseCode() != 200) {
				String content = getContent(conn.getErrorStream());
				System.out.println("DEBUG " + this.getClass() + " - [RESPONSE] " + content);
				ContentError contentError = toBean(content, ContentError.class);
				throw contentError.createException();
			    //throw new RequestException(conn.getResponseMessage(), conn.getResponseCode(), getContent(conn.getErrorStream()));
			}
			
			String content = getContent(conn.getInputStream()); 
			System.out.println("DEBUG " + this.getClass() + " - [RESPONSE] " + content);
			
			return content;
		} catch (MalformedURLException e) {
			throw new FacebookException("URL invalida (" + value + ")", e);
		} catch (IOException e) {
			throw new FacebookException("Erro na comunicacao com o servico (" + value + ")", e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T toBean(String json, Class<T> beanClass) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setRootClass(beanClass);
		jsonConfig.setPropertySetStrategy(new SimplesPropertySetStrategy());
		JSONObject jsonObject = JSONObject.fromObject(json);
		return (T) JSONObject.toBean(jsonObject, jsonConfig);
	}

	public <T> T jsonRequest(String url, Class<T> beanClass) throws FacebookException {
		return toBean(request(url), beanClass);
	}

	public OAuth getOauth() {
		return oauth;
	}

	public FacebookService getService() {
		return service;
	}

}