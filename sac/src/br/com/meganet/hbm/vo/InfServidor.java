package br.com.meganet.hbm.vo;

import java.io.Serializable;
import java.sql.Timestamp;


public class InfServidor implements Serializable{


	private static final long serialVersionUID = 7631139408117155775L;
	private Long infservidorId;
	private Torre torre;
	private Timestamp infservidorData;
	private Double infservidorDownload;
	private Double infservidorUpload;
	
	
	public Long getInfservidorId() {
		return infservidorId;
	}
	public void setInfservidorId(Long infservidorId) {
		this.infservidorId = infservidorId;
	}
	public Torre getTorre() {
		return torre;
	}
	public void setTorre(Torre torre) {
		this.torre = torre;
	}
	public Timestamp getInfservidorData() {
		return infservidorData;
	}
	public void setInfservidorData(Timestamp infservidorData) {
		this.infservidorData = infservidorData;
	}
	public Double getInfservidorDownload() {
		return infservidorDownload;
	}
	public void setInfservidorDownload(Double infservidorDownload) {
		this.infservidorDownload = infservidorDownload;
	}
	public Double getInfservidorUpload() {
		return infservidorUpload;
	}
	public void setInfservidorUpload(Double infservidorUpload) {
		this.infservidorUpload = infservidorUpload;
	}

}