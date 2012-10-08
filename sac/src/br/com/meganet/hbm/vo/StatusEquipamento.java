package br.com.meganet.hbm.vo;

import java.io.Serializable;

public class StatusEquipamento implements Serializable{
	
	private static final long serialVersionUID = -8784198530688815606L;

	private int tipoEquipamento;
	
	//modem
	private String trafegoAtualDownload;
	private String trafegoAtualUpload;
	private String atenuacaoModem;
	private String velocidadeNegociadaUpload;
	private String velocidadeNegociadaDownload;
	private String ip;
	private String Login;
	private String modelo;
	
	//mikrotik
	private String nomeTorre;
	private String qtdClienteRegistrado;
	private String trfficTXAtual;
	private String trfficRXAtual;
	private String noiseFloor;
	private String txCCQ;
	
	//PPPoE
	private String nome;
	private String status;
	private String tempoConectado;
	
	
	public String getTrafegoAtualDownload() {
		return trafegoAtualDownload;
	}
	public void setTrafegoAtualDownload(String trafegoAtualDownload) {
		this.trafegoAtualDownload = trafegoAtualDownload;
	}
	public String getTrafegoAtualUpload() {
		return trafegoAtualUpload;
	}
	public void setTrafegoAtualUpload(String trafegoAtualUpload) {
		this.trafegoAtualUpload = trafegoAtualUpload;
	}
	public String getAtenuacaoModem() {
		return atenuacaoModem;
	}
	public void setAtenuacaoModem(String atenuacaoModem) {
		this.atenuacaoModem = atenuacaoModem;
	}
	public String getVelocidadeNegociadaUpload() {
		return velocidadeNegociadaUpload;
	}
	public void setVelocidadeNegociadaUpload(String velocidadeNegociadaUpload) {
		this.velocidadeNegociadaUpload = velocidadeNegociadaUpload;
	}
	public String getVelocidadeNegociadaDownload() {
		return velocidadeNegociadaDownload;
	}
	public void setVelocidadeNegociadaDownload(String velocidadeNegociadaDownload) {
		this.velocidadeNegociadaDownload = velocidadeNegociadaDownload;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNomeTorre() {
		return nomeTorre;
	}
	public void setNomeTorre(String nomeTorre) {
		this.nomeTorre = nomeTorre;
	}
	public String getQtdClienteRegistrado() {
		return qtdClienteRegistrado;
	}
	public void setQtdClienteRegistrado(String qtdClienteRegistrado) {
		this.qtdClienteRegistrado = qtdClienteRegistrado;
	}
	public String getTrfficTXAtual() {
		return trfficTXAtual;
	}
	public void setTrfficTXAtual(String trfficTXAtual) {
		this.trfficTXAtual = trfficTXAtual;
	}
	public String getTrfficRXAtual() {
		return trfficRXAtual;
	}
	public void setTrfficRXAtual(String trfficRXAtual) {
		this.trfficRXAtual = trfficRXAtual;
	}
	public String getNoiseFloor() {
		return noiseFloor;
	}
	public void setNoiseFloor(String noiseFloor) {
		this.noiseFloor = noiseFloor;
	}
	public String getTxCCQ() {
		return txCCQ;
	}
	public void setTxCCQ(String txCCQ) {
		this.txCCQ = txCCQ;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTempoConectado() {
		return tempoConectado;
	}
	public void setTempoConectado(String tempoConectado) {
		this.tempoConectado = tempoConectado;
	}
	public int getTipoEquipamento() {
		return tipoEquipamento;
	}
	public void setTipoEquipamento(int tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}
}