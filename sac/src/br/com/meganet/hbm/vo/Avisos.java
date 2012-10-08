package br.com.meganet.hbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Avisos implements Serializable{

	private static final long serialVersionUID = 2990799233516536396L;
	private Long avisosId;
	private String avisosAviso;
	private Boolean avisosAtivo;
	private Timestamp avisosDataExpiracao;
	private String tmp;
	private String avisosTitulo;
	
	public Long getAvisosId() {
		return this.avisosId;
	}

	public void setAvisosId(Long avisosId) {
		this.avisosId = avisosId;
	}

	public String getAvisosAviso() {
		return this.avisosAviso;
	}

	public void setAvisosAviso(String avisosAviso) {
		this.avisosAviso = avisosAviso;
	}

	public Boolean getAvisosAtivo() {
		return this.avisosAtivo;
	}

	public void setAvisosAtivo(Boolean avisosAtivo) {
		this.avisosAtivo = avisosAtivo;
	}

	public Timestamp getAvisosDataExpiracao() {
		return avisosDataExpiracao;
	}

	public void setAvisosDataExpiracao(Timestamp avisosDataExpiracao) {
		this.avisosDataExpiracao = avisosDataExpiracao;
	}

	public String getAvisosTitulo() {
		return avisosTitulo;
	}

	public void setAvisosTitulo(String avisosTitulo) {
		this.avisosTitulo = avisosTitulo;
	}

	public String getTmp() {
		return tmp;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp;
	}

}