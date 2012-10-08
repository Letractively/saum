package br.com.meganet.hbm.vo;

import java.io.Serializable;

public class TipoEquipamento implements Serializable{

	private static final long serialVersionUID = -2794023785663309636L;
	private Long tipoEquipamentoId;
	private String tipoEquipamentoDescricao;

	// Property accessors

	public Long getTipoEquipamentoId() {
		return this.tipoEquipamentoId;
	}

	public void setTipoEquipamentoId(Long tipoEquipamentoId) {
		this.tipoEquipamentoId = tipoEquipamentoId;
	}

	public String getTipoEquipamentoDescricao() {
		return this.tipoEquipamentoDescricao;
	}

	public void setTipoEquipamentoDescricao(String tipoEquipamentoDescricao) {
		this.tipoEquipamentoDescricao = tipoEquipamentoDescricao;
	}

}