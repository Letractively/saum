package br.com.clarotriagem.utils;

import javax.faces.application.FacesMessage.Severity;

public class BeanMensagem {
	private boolean temMensagem;
	private String titulo;
	private String mensagem;
	private Severity facesMessage;
	
	public BeanMensagem(boolean temMensagem, String titulo, String mensagem, Severity severityError) {
		super();
		this.temMensagem = temMensagem;
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.facesMessage = severityError;
	}

	public boolean isTemMensagem() {
		return temMensagem;
	}

	public void setTemMensagem(boolean temMensagem) {
		this.temMensagem = temMensagem;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Severity getFacesMessage() {
		return facesMessage;
	}

	public void setFacesMessage(Severity facesMessage) {
		this.facesMessage = facesMessage;
	}
	
}
