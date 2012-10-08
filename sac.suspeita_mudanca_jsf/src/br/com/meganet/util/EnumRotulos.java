package br.com.meganet.util;

import java.util.HashMap;
import java.util.Map;

public enum EnumRotulos {

	 RETORNO("1","Retorno", "Log sobre o arquivo de retorno do banco" )
	,LOGIN("2","DESC_SERVIDOR", "Ip do servidor" )
	,ALTERACAO_DADOS_CLIENTE("3","AUTENTICACAO", "Autenticação" )
	,AUTENTICACAO_CLIENTE("4","AUTENTICACAO_CLIENTE", "Autenticação Cliente" );
	 
	private String codigo;
	private String sigla;
	private String descricao;
	private EnumRotulos(String codigo, String sigla, String descricao){
		this.codigo = codigo;
		this.sigla = sigla;
		this.descricao = descricao;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getSigla() {
		return sigla;
	}
	
	public String toString(){
		return "[" + codigo + "] " + sigla;
	}
	
	public static EnumRotulos getRotuloPorCodigo(String codigo){
		return rotulosPorCodigo.get(codigo);
	}
	
	public static EnumRotulos getRotuloPorSigla(String sigla){
		return rotulosPorSigla.get(sigla);
	}
	
	
	private static Map<String, EnumRotulos> rotulosPorCodigo = new HashMap<String, EnumRotulos>();
	static{
		for(EnumRotulos e: EnumRotulos.values()){
			rotulosPorCodigo.put(e.getCodigo(), e);
		}
	}

	private static Map<String, EnumRotulos> rotulosPorSigla = new HashMap<String, EnumRotulos>();
	static{
		for(EnumRotulos e: EnumRotulos.values()){
			rotulosPorSigla.put(e.getSigla(), e);
		}
        
	}

}
