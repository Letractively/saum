package br.com.meganet.hbm.vo;

import java.io.Serializable;

public class VelocidadeMedia implements Serializable{

	private static final long serialVersionUID = 686052566140538955L;
	private Long velocidademediaId;
	private Usuario usuario;
	private Long velocidademediaVelocidade;

	public Long getVelocidademediaId() {
		return this.velocidademediaId;
	}

	public void setVelocidademediaId(Long velocidademediaId) {
		this.velocidademediaId = velocidademediaId;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getVelocidademediaVelocidade() {
		return this.velocidademediaVelocidade;
	}

	public void setVelocidademediaVelocidade(Long velocidademediaVelocidade) {
		this.velocidademediaVelocidade = velocidademediaVelocidade;
	}

}