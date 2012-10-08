package br.com.meganet.hbm.vo;

import java.io.Serializable;


public class EnderecoIp implements Serializable{

	private static final long serialVersionUID = -3315266694708214865L;
	private Long enderecoipId;
	private Servidor servidor;
	private String enderecoipEndereco;
	private Boolean enderecoipAtivado;
	private String enderecoipMacCadastrado;
	private String enderecoipGrupo;
	private boolean temUsuario;
	private String nomeUsr;
	private String enderecoipMascaraSubrede;

	public EnderecoIp() {
	}

	public EnderecoIp(Long enderecoipId) {
		super();
		this.enderecoipId = enderecoipId;
	}

	// Property accessors
	public Long getEnderecoipId() {
		return this.enderecoipId;
	}

	public String getNomeUsr() {
		return nomeUsr;
	}

	public void setNomeUsr(String nomeUsr) {
		this.nomeUsr = nomeUsr;
	}

	public void setEnderecoipId(Long enderecoipId) {
		this.enderecoipId = enderecoipId;
	}

	public String getEnderecoipEndereco() {
		return this.enderecoipEndereco;
	}

	public void setEnderecoipEndereco(String enderecoipEndereco) {
		this.enderecoipEndereco = enderecoipEndereco;
	}

	public String getEnderecoipMacCadastrado() {
		return this.enderecoipMacCadastrado;
	}

	public void setEnderecoipMacCadastrado(String enderecoipMacCadastrado) {
		this.enderecoipMacCadastrado = enderecoipMacCadastrado;
	}

	public String getEnderecoipGrupo() {
		return this.enderecoipGrupo;
	}

	public void setEnderecoipGrupo(String enderecoipGrupo) {
		this.enderecoipGrupo = enderecoipGrupo;
	}

	public boolean getTemUsuario() {
		return temUsuario;
	}

	public void setTemUsuario(boolean temUsuario) {
		this.temUsuario = temUsuario;
	}

	public String getEnderecoipMascaraSubrede() {
		return enderecoipMascaraSubrede;
	}

	public void setEnderecoipMascaraSubrede(String enderecoipMascaraSubrede) {
		this.enderecoipMascaraSubrede = enderecoipMascaraSubrede;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public Boolean getEnderecoipAtivado() {
		return enderecoipAtivado;
	}

	public void setEnderecoipAtivado(Boolean enderecoipAtivado) {
		this.enderecoipAtivado = enderecoipAtivado;
	}

}