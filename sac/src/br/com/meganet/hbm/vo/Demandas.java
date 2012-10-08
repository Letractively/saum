package br.com.meganet.hbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;


public class Demandas implements Serializable{

	private static final long serialVersionUID = 1365808391005827296L;
	private Long demandasId;
	private Usuario usuarioAbriu;
	private Usuario usuarioFechou;
	private Usuario usuarioResponsavel;
	private Usuario usuarioSolicitante;
	private Timestamp demandasDataAbertura;
	private Timestamp demandasDataEncerramento;
	private Timestamp demandasDataPrevistaAtendimento;
	private String demandasDescricao;
	private String demandasTipoErro;
	private String demandasDescricaoEncerramento;
	
	private String cliente;
	private String tecnicoAbriu;
	private String responsavel;
	private String tecnicoFechou;

	private String dataEncerramento;
	private String dataPrevistaAtendimento;
	private String dataAbertura;
	private String fechar;

	// Property accessors

	public String getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Long getDemandasId() {
		return this.demandasId;
	}

	public void setDemandasId(Long demandasId) {
		this.demandasId = demandasId;
	}

	public Timestamp getDemandasDataAbertura() {
		return this.demandasDataAbertura;
	}

	public void setDemandasDataAbertura(Timestamp demandasDataAbertura) {
		this.demandasDataAbertura = demandasDataAbertura;
	}

	public Timestamp getDemandasDataEncerramento() {
		return this.demandasDataEncerramento;
	}

	public void setDemandasDataEncerramento(Timestamp demandasDataEncerramento) {
		this.demandasDataEncerramento = demandasDataEncerramento;
	}

	public String getDemandasDescricao() {
		return this.demandasDescricao;
	}

	public void setDemandasDescricao(String demandasDescricao) {
		this.demandasDescricao = demandasDescricao;
	}

	public String getDemandasDescricaoEncerramento() {
		return this.demandasDescricaoEncerramento;
	}

	public void setDemandasDescricaoEncerramento(
			String demandasDescricaoEncerramento) {
		this.demandasDescricaoEncerramento = demandasDescricaoEncerramento;
	}

	public Usuario getUsuarioAbriu() {
		return usuarioAbriu;
	}

	public void setUsuarioAbriu(Usuario usuarioAbriu) {
		this.usuarioAbriu = usuarioAbriu;
	}

	public Usuario getUsuarioFechou() {
		return usuarioFechou;
	}

	public void setUsuarioFechou(Usuario usuarioFechou) {
		this.usuarioFechou = usuarioFechou;
	}

	public Timestamp getDemandasDataPrevistaAtendimento() {
		return demandasDataPrevistaAtendimento;
	}

	public void setDemandasDataPrevistaAtendimento(Timestamp demandasDataPrevistaAtendimento) {
		this.demandasDataPrevistaAtendimento = demandasDataPrevistaAtendimento;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getTecnicoAbriu() {
		return tecnicoAbriu;
	}

	public void setTecnicoAbriu(String tecnicoAbriu) {
		this.tecnicoAbriu = tecnicoAbriu;
	}

	public String getTecnicoFechou() {
		return tecnicoFechou;
	}

	public void setTecnicoFechou(String tecnicoFechou) {
		this.tecnicoFechou = tecnicoFechou;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getDataPrevistaAtendimento() {
		return dataPrevistaAtendimento;
	}

	public void setDataPrevistaAtendimento(String dataPrevistaAtendimento) {
		this.dataPrevistaAtendimento = dataPrevistaAtendimento;
	}

	public String getFechar() {
		return fechar;
	}

	public void setFechar(String fechar) {
		this.fechar = fechar;
	}

	public Usuario getUsuarioResponsavel() {
		return usuarioResponsavel;
	}

	public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
		this.usuarioResponsavel = usuarioResponsavel;
	}

	public Usuario getUsuarioSolicitante() {
		return usuarioSolicitante;
	}

	public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getDemandasTipoErro() {
		return demandasTipoErro;
	}

	public void setDemandasTipoErro(String demandasTipoErro) {
		this.demandasTipoErro = demandasTipoErro;
	}

}