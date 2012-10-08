package br.com.meganet.hbm.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Contrato implements Serializable{

	private static final long serialVersionUID = 2812692477795422068L;
	private Long contratoId;
	private String contratoContrato;
	private String contratoNome;
	private Set<Usuario> usuarios = new HashSet<Usuario>(0);
	
	public Contrato() {
	}
	
	public Contrato(Long contratoId) {
		super();
		this.contratoId = contratoId;
	}

	public String getContratoNome() {
		return contratoNome;
	}

	public void setContratoNome(String contratoNome) {
		this.contratoNome = contratoNome;
	}


	public Long getContratoId() {
		return this.contratoId;
	}

	public void setContratoId(Long contratoId) {
		this.contratoId = contratoId;
	}

	public String getContratoContrato() {
		return this.contratoContrato;
	}

	public void setContratoContrato(String contratoContrato) {
		this.contratoContrato = contratoContrato;
	}

	public Set<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}