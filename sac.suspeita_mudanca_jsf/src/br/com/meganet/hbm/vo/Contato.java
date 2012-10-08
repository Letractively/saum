package br.com.meganet.hbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;


public class Contato implements Serializable{

	private static final long serialVersionUID = -5561452401177347563L;
	private Long contatoId;
	private String contatoNome;
	private String contatoEmail;
	private String contatoEndCr;
	private String contatoEndCasa;
	private String contatoTelefone;
	private String contatoMensagem;
	private String contatoTipo;
	private String contatoResposta;
	private Timestamp contatoDataEnvio;
	private Timestamp contatoDataResposta;
	
	private String dataEnvio;
	private String dataResposta;
	private String fechar;

	// Property accessors

	public Long getContatoId() {
		return this.contatoId;
	}

	public void setContatoId(Long contatoId) {
		this.contatoId = contatoId;
	}

	public String getContatoNome() {
		return this.contatoNome;
	}

	public void setContatoNome(String contatoNome) {
		this.contatoNome = contatoNome;
	}

	public String getContatoEmail() {
		return this.contatoEmail;
	}

	public void setContatoEmail(String contatoEmail) {
		this.contatoEmail = contatoEmail;
	}

	public String getContatoEndCr() {
		return this.contatoEndCr;
	}

	public void setContatoEndCr(String contatoEndCr) {
		this.contatoEndCr = contatoEndCr;
	}

	public String getContatoEndCasa() {
		return this.contatoEndCasa;
	}

	public void setContatoEndCasa(String contatoEndCasa) {
		this.contatoEndCasa = contatoEndCasa;
	}

	public String getContatoTelefone() {
		return this.contatoTelefone;
	}

	public void setContatoTelefone(String contatoTelefone) {
		this.contatoTelefone = contatoTelefone;
	}

	public String getContatoMensagem() {
		return this.contatoMensagem;
	}

	public void setContatoMensagem(String contatoMensagem) {
		this.contatoMensagem = contatoMensagem;
	}

	public String getContatoTipo() {
		return this.contatoTipo;
	}

	public void setContatoTipo(String contatoTipo) {
		this.contatoTipo = contatoTipo;
	}

	public String getContatoResposta() {
		return contatoResposta;
	}

	public void setContatoResposta(String contatoResposta) {
		this.contatoResposta = contatoResposta;
	}

	public Timestamp getContatoDataEnvio() {
		return contatoDataEnvio;
	}

	public void setContatoDataEnvio(Timestamp contatoDataEnvio) {
		this.contatoDataEnvio = contatoDataEnvio;
	}

	public Timestamp getContatoDataResposta() {
		return contatoDataResposta;
	}

	public void setContatoDataResposta(Timestamp contatoDataResposta) {
		this.contatoDataResposta = contatoDataResposta;
	}

	public String getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getDataResposta() {
		return dataResposta;
	}

	public void setDataResposta(String dataResposta) {
		this.dataResposta = dataResposta;
	}

	public String getFechar() {
		return fechar;
	}

	public void setFechar(String fechar) {
		this.fechar = fechar;
	}

}