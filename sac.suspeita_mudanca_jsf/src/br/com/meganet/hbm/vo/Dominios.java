package br.com.meganet.hbm.vo;

import java.io.Serializable;


public class Dominios implements Serializable{

	private static final long serialVersionUID = -1016398703650386862L;
	private Long dominiosId;
	private String dominiosChave;
	private String dominiosValor;
	private String dominiosDescricao;
	private Boolean dominiosTratamentoEspecial;
	private Long dominiosTipo;
	
	public String getDominiosChave() {
		return dominiosChave;
	}
	public void setDominiosChave(String dominiosChave) {
		this.dominiosChave = dominiosChave;
	}
	public String getDominiosValor() {
		return dominiosValor;
	}
	public void setDominiosValor(String dominiosValor) {
		this.dominiosValor = dominiosValor;
	}
	public Long getDominiosId() {
		return dominiosId;
	}
	public void setDominiosId(Long dominiosId) {
		this.dominiosId = dominiosId;
	}
	public Long getDominiosTipo() {
		return dominiosTipo;
	}
	public void setDominiosTipo(Long dominiosTipo) {
		this.dominiosTipo = dominiosTipo;
	}
	public Boolean getDominiosTratamentoEspecial() {
		return dominiosTratamentoEspecial;
	}
	public void setDominiosTratamentoEspecial(Boolean dominiosTratamentoEspecial) {
		this.dominiosTratamentoEspecial = dominiosTratamentoEspecial;
	}
	public String getDominiosDescricao() {
		return dominiosDescricao;
	}
	public void setDominiosDescricao(String dominiosDescricao) {
		this.dominiosDescricao = dominiosDescricao;
	}
}