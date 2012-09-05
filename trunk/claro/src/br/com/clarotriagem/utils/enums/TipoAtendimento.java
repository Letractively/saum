package br.com.clarotriagem.utils.enums;

import java.util.SortedMap;
import java.util.TreeMap;

public enum TipoAtendimento {
	
    ATENDIMENTO("Atendimento", 0),
    OCORRENCIA("Ocorrência", 1);
	
	private String descricao;
	private Integer cod;
	
	private static SortedMap<String, TipoAtendimento> rotulosPorDescricao = new TreeMap<String, TipoAtendimento>();
	private static SortedMap<Integer, TipoAtendimento> rotulosPorCod = new TreeMap<Integer, TipoAtendimento>();
	private static SortedMap<String, Integer> mapaRotulos = new TreeMap<String, Integer>();

	private TipoAtendimento(String descricao, Integer cod) {
		this.descricao = descricao;
		this.cod = cod;
	}

	static {
		for (TipoAtendimento e : TipoAtendimento.values()) {
			rotulosPorCod.put(e.getCod(), e);
		}
		for (TipoAtendimento e : TipoAtendimento.values()) {
			rotulosPorDescricao.put(e.getDescricao(), e);
		}
		for (TipoAtendimento e : TipoAtendimento.values()) {
			mapaRotulos.put(e.getDescricao(), e.getCod());
		}
	}

	public String toString() {
		return "[" + descricao + "] " + cod;
	}

	public static TipoAtendimento getRotuloPorCod(Integer codigo) {
		return rotulosPorCod.get(codigo);
	}

	public static TipoAtendimento getRotuloPorSigla(String desc) {
		return rotulosPorDescricao.get(desc);
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public static SortedMap<String, Integer> getMapaRotulos() {
		return mapaRotulos;
	}

	public static void setMapaRotulos(SortedMap<String, Integer> mapaRotulos) {
		TipoAtendimento.mapaRotulos = mapaRotulos;
	}

}