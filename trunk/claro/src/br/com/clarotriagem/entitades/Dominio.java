package br.com.clarotriagem.entitades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="dominios")
public class Dominio implements Serializable {

	private static final long serialVersionUID = 173672024927313076L;

	@Id
	@SequenceGenerator(name="ID_GENERATOR", sequenceName="DOMINIOS_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ID_GENERATOR")
	@Column(name="dominios_id", unique=true, nullable=false)
	private Long dominiosId;

	@Column(name="dominios_chave", length=60)
	private String dominiosChave;

	@Column(name="dominios_descricao", length=2147483647)
	private String dominiosDescricao;

	@Column(name="dominios_tipo")
	private Long dominiosTipo;

	@Column(name="dominios_tratamento_especial")
	private Boolean dominiosTratamentoEspecial;

	@Column(name="dominios_valor", length=100)
	private String dominiosValor;

    public Dominio() {
    }

	public Long getDominiosId() {
		return this.dominiosId;
	}

	public void setDominiosId(Long dominiosId) {
		this.dominiosId = dominiosId;
	}

	public String getDominiosChave() {
		return this.dominiosChave;
	}

	public void setDominiosChave(String dominiosChave) {
		this.dominiosChave = dominiosChave;
	}

	public String getDominiosDescricao() {
		return this.dominiosDescricao;
	}

	public void setDominiosDescricao(String dominiosDescricao) {
		this.dominiosDescricao = dominiosDescricao;
	}

	public Long getDominiosTipo() {
		return this.dominiosTipo;
	}

	public void setDominiosTipo(Long dominiosTipo) {
		this.dominiosTipo = dominiosTipo;
	}

	public Boolean getDominiosTratamentoEspecial() {
		return this.dominiosTratamentoEspecial;
	}

	public void setDominiosTratamentoEspecial(Boolean dominiosTratamentoEspecial) {
		this.dominiosTratamentoEspecial = dominiosTratamentoEspecial;
	}

	public String getDominiosValor() {
		return this.dominiosValor;
	}

	public void setDominiosValor(String dominiosValor) {
		this.dominiosValor = dominiosValor;
	}

}