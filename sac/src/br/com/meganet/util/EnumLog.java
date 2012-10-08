package br.com.meganet.util;

import java.util.HashMap;
import java.util.Map;

public enum EnumLog {
	RETORNO("1", "Arquivo retorno"), 
	BOLETO("2", "Impressão de boleto"), 
	LOGIN("3", "Login");
	
	private String seq;
	private String nome;
	private EnumLog(String seq, String nome){
		this.seq = seq;
		this.nome = nome;
	}
	
	public static String getLogPelaSequencia(String sequencia){
		EnumLog enumLog = rotulosPorSequencia.get(sequencia);
		return enumLog.getNome();
	}
	
	public static String getLogPeloNome(String nome){
		EnumLog enumLog = rotulosPorNome.get(nome);
		return enumLog.getSeq();
	}
	public String toString(){
		return seq;
	}
	
	
//////////////////////////////////////////////////////////////////////////////////////////
	private String getSeq() {
		return seq;
	}
	private String getNome() {
		return nome;
	}
	private static Map<String, EnumLog> rotulosPorSequencia = new HashMap<String, EnumLog>();
	static{
		for(EnumLog e: EnumLog.values()){
			rotulosPorSequencia.put(e.getSeq(), e);
		}
	}
	private static Map<String, EnumLog> rotulosPorNome = new HashMap<String, EnumLog>();
	static{
		for(EnumLog e: EnumLog.values()){
			rotulosPorNome.put(e.getSeq(), e);
		}
        
	}

}
