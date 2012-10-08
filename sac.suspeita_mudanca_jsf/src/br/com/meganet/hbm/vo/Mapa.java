package br.com.meganet.hbm.vo;

import java.io.Serializable;

public class Mapa implements Serializable{


	private static final long serialVersionUID = 490595546152203459L;
	private Long mapaId;
	private Usuario usuario;
	private Double mapaTop;
	private Double mapaLeft;

	public Usuario getUsuario() {
		return this.usuario;
	}

	public Double getMapaTop() {
		return mapaTop;
	}

	public void setMapaTop(Double mapaTop) {
		this.mapaTop = mapaTop;
	}

	public Double getMapaLeft() {
		return mapaLeft;
	}

	public void setMapaLeft(Double mapaLeft) {
		this.mapaLeft = mapaLeft;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getMapaId() {
		return mapaId;
	}

	public void setMapaId(Long mapaId) {
		this.mapaId = mapaId;
	}

}