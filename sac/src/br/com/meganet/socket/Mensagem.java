package br.com.meganet.socket;

import java.util.TreeSet;

public abstract class Mensagem{
	
	private TreeSet<Comando> comando = new TreeSet<Comando>();
	private String mensagem;
	
	public void setComando(TreeSet<Comando> comando) {
		this.comando = comando;
	}
	
	public void addComando(Comando comando) {
		this.comando.add(comando);
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public TreeSet<Comando> getComando() {
		return comando;
	}

}
