package br.com.meganet.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AutenticadorEmail extends Authenticator {
	private String username;
	private String password;
	private final String mailSMTPServer = "smtp.gmail.com";
	private final String mailSMTPServerPort = "465";

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

	public AutenticadorEmail(String user, String pwd) {
		username = user;
		password = pwd;
	}

	public AutenticadorEmail() {
		username = "plataformabr@gmail.com";
		password = "plataforma2009";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public final String getMailSMTPServerPort() {
		return mailSMTPServerPort;
	}

	public final String getMailSMTPServer() {
		return mailSMTPServer;
	}

}
