package br.com.meganet.hbm.vo;

import java.io.Serializable;

public class PoolComandos implements Serializable{

	private static final long serialVersionUID = -6009443293853335694L;
	private Long poolcomandosId;
	private String poolcomandosComando;
	private String comandoExemplo;
	private Long poolcomandosIdentificador;
	private Torre torre;
	private Servidor servidor;
	private Long poolcomandosTipo;
	private Integer poolcomandosOrdem;

	public Long getPoolcomandosId() {
		return this.poolcomandosId;
	}

	public void setPoolcomandosId(Long poolcomandosId) {
		this.poolcomandosId = poolcomandosId;
	}

	public String getPoolcomandosComando() {
		return this.poolcomandosComando;
	}

	public void setPoolcomandosComando(String poolcomandosComando) {
		this.poolcomandosComando = poolcomandosComando;
	}

	public Long getPoolcomandosIdentificador() {
		return poolcomandosIdentificador;
	}

	public void setPoolcomandosIdentificador(Long poolcomandosIdentificador) {
		this.poolcomandosIdentificador = poolcomandosIdentificador;
	}

	public Torre getTorre() {
		return torre;
	}

	public void setTorre(Torre torre) {
		this.torre = torre;
	}

	public Long getPoolcomandosTipo() {
		return poolcomandosTipo;
	}

	public void setPoolcomandosTipo(Long poolcomandosTipo) {
		this.poolcomandosTipo = poolcomandosTipo;
	}

	public Integer getPoolcomandosOrdem() {
		return poolcomandosOrdem;
	}

	public void setPoolcomandosOrdem(Integer poolcomandosOrdem) {
		this.poolcomandosOrdem = poolcomandosOrdem;
	}

	public String getComandoExemplo() {
		return comandoExemplo;
	}

	public void setComandoExemplo(String comandoExemplo) {
		this.comandoExemplo = comandoExemplo;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

}