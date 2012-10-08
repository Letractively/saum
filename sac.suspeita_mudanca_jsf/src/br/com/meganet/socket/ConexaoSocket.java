package br.com.meganet.socket;

import ch.ethz.ssh2.Connection;

public class ConexaoSocket {

	private Connection con;
	private long horaCriacao;
	private boolean conectado = false;
	
	public ConexaoSocket(Connection con, boolean estaConetado){
		this.con = con;
		this.horaCriacao = System.currentTimeMillis();
		this.conectado = estaConetado;
	}
	
	protected void atualizaHoraConexao(){
		this.horaCriacao = System.currentTimeMillis();
	}
	public Connection getCon() {
		return con;
	}
	public long getHoraCriacao() {
		return horaCriacao;
	}

	public boolean isConectado() {
		return conectado;
	}

	public void setConectado(boolean conectado) {
		this.conectado = conectado;
	}

}
