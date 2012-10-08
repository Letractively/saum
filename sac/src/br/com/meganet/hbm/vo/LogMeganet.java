package br.com.meganet.hbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;


public class LogMeganet implements Serializable{

	private static final long serialVersionUID = 2294842502872545729L;
	private Long logId;
	private Long usuarioIdFk;
	private String logTipo;
	private String logDescricao;
	private Timestamp logData;
	private String logAcao;
	
	// Property accessors

	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Long getUsuarioIdFk() {
		return this.usuarioIdFk;
	}

	public void setUsuarioIdFk(Long usuarioIdFk) {
		this.usuarioIdFk = usuarioIdFk;
	}

	public String getLogTipo() {
		return this.logTipo;
	}

	public void setLogTipo(String logTipo) {
		this.logTipo = logTipo;
	}

	public String getLogDescricao() {
		return this.logDescricao;
	}

	public void setLogDescricao(String logDescricao) {
		this.logDescricao = logDescricao;
	}

	public Timestamp getLogData() {
		return this.logData;
	}

	public void setLogData(Timestamp logData) {
		this.logData = logData;
	}

	public String getLogAcao() {
		return this.logAcao;
	}

	public void setLogAcao(String logAcao) {
		this.logAcao = logAcao;
	}

}