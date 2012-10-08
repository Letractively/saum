package br.com.meganet.socket;

import br.com.meganet.hbm.vo.Torre;

public class Comando implements Comparable<Comando>{
	
	private Long idComando;
	private Integer ordem = 0;
	private String comando;
	private Torre torre;
	
	public Long getIdComando() {
		return idComando;
	}
	public void setIdComando(Long idComando) {
		this.idComando = idComando;
	}
	public String getComando() {
		return comando;
	}
	public void setComando(String comando) {
		this.comando = comando;
	}

	public Integer getOrdem() {
		return ordem;
	}
	
	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}
	
	public long getComparador(){
		long i = this.getIdComando() + this.getOrdem();
		return i;
	}
	
	public int compareTo(Comando o) {
		if(this.getComparador() > o.getComparador()){
			return 1;
		}else if(this.getComparador() < o.getComparador()){
			return -1;
		}else{
			return 0;
		}
	}
	public Torre getTorre() {
		return torre;
	}
	public void setTorre(Torre torre) {
		this.torre = torre;
	}
	
	public void setEnderecoPelaTorre(Torre torre){
		this.torre = torre;
	}
	
}
