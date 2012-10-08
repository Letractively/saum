package br.com.meganet.hbm.vo;

import java.io.Serializable;


public class PlanosPacotes implements Serializable{

	private static final long serialVersionUID = 8366103039544266232L;
	private Long planospacotesId;
	private Double planospacotesValor;
	private String valor;
	private String planospacotesNome;
	private String planospacotesDescricao;
	private Boolean planospacotesAtivado;
	private Boolean planospacotesBloqueiaPgAtrasado;
	private Integer planospacotesVelocidade;
	
	private Double planospacotesDesconto;
	private Long planospacotesLimiteDesconto;
	private Double planospacotesMulta;
	private Double planospacotesJuroMes;

	// Property accessors
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Boolean getPlanospacotesAtivado() {
		return planospacotesAtivado;
	}

	public void setPlanospacotesAtivado(Boolean planospacotesAtivado) {
		this.planospacotesAtivado = planospacotesAtivado;
	}

	public Long getPlanospacotesId() {
		return this.planospacotesId;
	}

	public void setPlanospacotesId(Long planospacotesId) {
		this.planospacotesId = planospacotesId;
	}

	public Double getPlanospacotesValor() {
		return this.planospacotesValor;
	}

	public void setPlanospacotesValor(Double planospacotesValor) {
		this.planospacotesValor = planospacotesValor;
	}

	public String getPlanospacotesNome() {
		return this.planospacotesNome;
	}

	public void setPlanospacotesNome(String planospacotesNome) {
		this.planospacotesNome = planospacotesNome;
	}

	public String getPlanospacotesDescricao() {
		return this.planospacotesDescricao;
	}

	public void setPlanospacotesDescricao(String planospacotesDescricao) {
		this.planospacotesDescricao = planospacotesDescricao;
	}

	public Integer getPlanospacotesVelocidade() {
		return planospacotesVelocidade;
	}

	public void setPlanospacotesVelocidade(Integer planospacotesVelocidade) {
		this.planospacotesVelocidade = planospacotesVelocidade;
	}

	public Boolean getPlanospacotesBloqueiaPgAtrasado() {
		return planospacotesBloqueiaPgAtrasado;
	}

	public void setPlanospacotesBloqueiaPgAtrasado(Boolean planospacotesBloqueiaPgAtrasado) {
		this.planospacotesBloqueiaPgAtrasado = planospacotesBloqueiaPgAtrasado;
	}

	public Long getPlanospacotesLimiteDesconto() {
		return planospacotesLimiteDesconto;
	}

	public void setPlanospacotesLimiteDesconto(Long planospacotesLimiteDesconto) {
		this.planospacotesLimiteDesconto = planospacotesLimiteDesconto;
	}

	public Double getPlanospacotesDesconto() {
		return planospacotesDesconto;
	}

	public void setPlanospacotesDesconto(Double planospacotesDesconto) {
		this.planospacotesDesconto = planospacotesDesconto;
	}

	public Double getPlanospacotesMulta() {
		return planospacotesMulta;
	}

	public void setPlanospacotesMulta(Double planospacotesMulta) {
		this.planospacotesMulta = planospacotesMulta;
	}

	public Double getPlanospacotesJuroMes() {
		return planospacotesJuroMes;
	}

	public void setPlanospacotesJuroMes(Double planospacotesJuroMes) {
		this.planospacotesJuroMes = planospacotesJuroMes;
	}

}