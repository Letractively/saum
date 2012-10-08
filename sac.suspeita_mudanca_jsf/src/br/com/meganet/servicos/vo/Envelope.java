package br.com.meganet.servicos.vo;


public class Envelope{
	public Body body;
	private String xmlSoapEnv;

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public String getXmlSoapEnv() {
		return xmlSoapEnv;
	}

	public void setXmlSoapEnv(String xmlSoapEnv) {
		this.xmlSoapEnv = xmlSoapEnv;
	}

}
