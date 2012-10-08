package br.com.meganet.hbm.vo;

import java.io.Serializable;

public class Resposta implements Serializable{
	
	private static final long serialVersionUID = 1778795235812289237L;
	private String acao;
	private String resposta;
	private boolean ehErro;
	
	public static final String ADICIONA_CLIENTE = "Adicionar cliente";
	public static final String ALTERA_CLIENTE = "Alterar cliente";
	public static final String EXCLUIR_CLIENTE = "Excluir cliente";
	
	public Resposta(boolean ehErro, String acao, String resposta) {
		this.ehErro = ehErro;
		this.acao = acao;
		this.resposta = resposta;
	}
	public Resposta(String acao, String resposta) {
		this.acao = acao;
		this.resposta = resposta;
	}
	public Resposta() {
	}
	public final String getAcao() {
		return acao;
	}
	public final void setAcao(String acao) {
		this.acao = acao;
	}
	public final String getResposta() {
		return resposta;
	}
	public final void setResposta(String resposta) {
		this.resposta = resposta;
	}
	public boolean isEhErro() {
		return ehErro;
	}
	public void setEhErro(boolean ehErro) {
		this.ehErro = ehErro;
	}	

}