package br.com.meganet.hbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Gasto implements Serializable{

	private static final long serialVersionUID = -3658950517614362209L;
	
	private Long gastoId;
	private String gastoValor;
	private String gastoMotivo;
	private Timestamp gastoData;
	private Timestamp gastoDataFim;
	private String dataTemp;
	private String gastoDescricaoMotivo;
	private Usuario usuario;
	
	
	public Long getGastoId() {
		return gastoId;
	}
	public void setGastoId(Long gastoId) {
		this.gastoId = gastoId;
	}
	public String getGastoValor() {
		return gastoValor;
	}
	public void setGastoValor(String gastoValor) {
		this.gastoValor = gastoValor;
	}
	public String getGastoMotivo() {
		return gastoMotivo;
	}
	public void setGastoMotivo(String gastoMotivo) {
		this.gastoMotivo = gastoMotivo;
	}
	public Timestamp getGastoData() {
		return gastoData;
	}
	public void setGastoData(Timestamp gastoData) {
		this.gastoData = gastoData;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getDataTemp() {
		return dataTemp;
	}
	public void setDataTemp(String dataTemp) {
		this.dataTemp = dataTemp;
	}
	public String getGastoDescricaoMotivo() {
		return gastoDescricaoMotivo;
	}
	public void setGastoDescricaoMotivo(String gastoDescricaoMotivo) {
		this.gastoDescricaoMotivo = gastoDescricaoMotivo;
	}
	public Timestamp getGastoDataFim() {
		return gastoDataFim;
	}
	public void setGastoDataFim(Timestamp gastoDataFim) {
		this.gastoDataFim = gastoDataFim;
	}

}