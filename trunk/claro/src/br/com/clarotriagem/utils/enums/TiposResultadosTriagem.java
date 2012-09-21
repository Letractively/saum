package br.com.clarotriagem.utils.enums;

import java.util.SortedMap;
import java.util.TreeMap;

public enum TiposResultadosTriagem {
	
    FG("FG", "fora de garantia", 1),
    G("G", "Garantia", 2),
    DOA("DOA", "DOA", 3),
    NAO_DOA("NAO DOA", "Não DOA", 4),
    INVALIDO("INV", "Inválido", 5);
	
    private String sigla;
	private String descricao;
	private Integer cod;
	private String msgRetorno;
	
	private static SortedMap<String, TiposResultadosTriagem> rotulosPorSigla = new TreeMap<String, TiposResultadosTriagem>();
	private static SortedMap<Integer, TiposResultadosTriagem> rotulosPorCod = new TreeMap<Integer, TiposResultadosTriagem>();
	private static SortedMap<String, Integer> mapaRotulos = new TreeMap<String, Integer>();

	private TiposResultadosTriagem(String sigla, String descricao, Integer cod) {
		this.descricao = descricao;
		this.cod = cod;
		this.sigla = sigla;
	}

	static {
		for (TiposResultadosTriagem e : TiposResultadosTriagem.values()) {
			rotulosPorCod.put(e.getCod(), e);
		}
		for (TiposResultadosTriagem e : TiposResultadosTriagem.values()) {
			rotulosPorSigla.put(e.getSigla(), e);
		}
		for (TiposResultadosTriagem e : TiposResultadosTriagem.values()) {
			mapaRotulos.put(e.getSigla() + " - " + e.getDescricao(), e.getCod());
		}
	}

	public String toString() {
		return "[" + sigla + "] " + cod + ": " + descricao;
	}

	public static TiposResultadosTriagem getRotuloPorCod(Integer codigo) {
		return rotulosPorCod.get(codigo);
	}

	public static TiposResultadosTriagem getRotuloPorSigla(String desc) {
		return rotulosPorSigla.get(desc);
	}
	
	public static SortedMap<String, Integer> getMapaRotulos() {
		return mapaRotulos;
	}


	public String getSigla() {
		return sigla;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Integer getCod() {
		return cod;
	}

	public String getMsgRetorno() {
		return msgRetorno;
	}

	public void setMsgRetorno(String msgRetorno) {
		this.msgRetorno = msgRetorno;
	}

}