package br.com.meganet.util.fakes;

import java.io.IOException;

import ch.ethz.ssh2.DHGexParameters;

public class Connection {

	public Connection(String torreIpConexao, Integer torrePorta) {
	}

	public void setDHGexParameters(DHGexParameters dhGexParameters) {
	}

	public void connect() {
	}

	public boolean authenticateWithPassword(String torreUsuario, String torreSenha) throws IOException{
		return false;
	}

	public Session openSession() {
		return null;
	}

	public void close() {
	}

}
