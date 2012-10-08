package br.com.meganet.hbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class StatusCliente implements Serializable{

	private static final long serialVersionUID = -4108605257394066061L;
	private Long statusclienteId;
	private Usuario usuario;
	private String statusclienteTxrate;
	private String statusclienteRxrate;
	private String statusclienteTxbytes;
	private String statusclienteRxbytes;
	private String statusclienteSignalstrength;
	private String statusclienteTxccq;
	private String statusclienteThroughput;
	private Timestamp statusclienteDatamedicao;
	private String data;
	private String data2;

	private String dataMedicao;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Long getStatusclienteId() {
		return this.statusclienteId;
	}

	public void setStatusclienteId(Long statusclienteId) {
		this.statusclienteId = statusclienteId;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getStatusclienteTxrate() {
		return statusclienteTxrate;
	}

	public void setStatusclienteTxrate(String statusclienteTxrate) {
		this.statusclienteTxrate = statusclienteTxrate;
	}

	public String getStatusclienteRxrate() {
		return statusclienteRxrate;
	}

	public void setStatusclienteRxrate(String statusclienteRxrate) {
		this.statusclienteRxrate = statusclienteRxrate;
	}

	public String getStatusclienteTxbytes() {
		return statusclienteTxbytes;
	}

	public void setStatusclienteTxbytes(String statusclienteTxbytes) {
		this.statusclienteTxbytes = statusclienteTxbytes;
	}

	public String getStatusclienteRxbytes() {
		return statusclienteRxbytes;
	}

	public void setStatusclienteRxbytes(String statusclienteRxbytes) {
		this.statusclienteRxbytes = statusclienteRxbytes;
	}

	public String getStatusclienteSignalstrength() {
		return statusclienteSignalstrength;
	}

	public void setStatusclienteSignalstrength(String statusclienteSignalstrength) {
		this.statusclienteSignalstrength = statusclienteSignalstrength;
	}

	public String getStatusclienteTxccq() {
		return statusclienteTxccq;
	}

	public void setStatusclienteTxccq(String statusclienteTxccq) {
		this.statusclienteTxccq = statusclienteTxccq;
	}

	public String getStatusclienteThroughput() {
		return statusclienteThroughput;
	}

	public void setStatusclienteThroughput(String statusclienteThroughput) {
		this.statusclienteThroughput = statusclienteThroughput;
	}

	public Timestamp getStatusclienteDatamedicao() {
		return statusclienteDatamedicao;
	}

	public void setStatusclienteDatamedicao(Timestamp statusclienteDatamedicao) {
		this.statusclienteDatamedicao = statusclienteDatamedicao;
	}

	public String getDataMedicao() {
		return dataMedicao;
	}

	public void setDataMedicao(String dataMedicao) {
		this.dataMedicao = dataMedicao;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

}