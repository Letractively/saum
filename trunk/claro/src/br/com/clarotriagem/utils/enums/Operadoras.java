package br.com.clarotriagem.utils.enums;

import java.util.SortedMap;
import java.util.TreeMap;

public enum Operadoras {
	
    BRT("BRT", 0),
    CLARO("Claro", 1),
    OI("OI", 2),
    OPEN("OPEN", 3),
    TELEMIG("Telemig", 4),
    TIM("TIM", 5),
    VIVO("Vivo", 6);
	
	private String descricao;
	private Integer cod;
	
	private static SortedMap<String, Operadoras> rotulosPorDescricao = new TreeMap<String, Operadoras>();
	private static SortedMap<Integer, Operadoras> rotulosPorCod = new TreeMap<Integer, Operadoras>();
	private static SortedMap<String, Integer> mapaRotulos = new TreeMap<String, Integer>();

	private Operadoras(String descricao, Integer cod) {
		this.descricao = descricao;
		this.cod = cod;
	}

	static {
		for (Operadoras e : Operadoras.values()) {
			rotulosPorCod.put(e.getCod(), e);
		}
		for (Operadoras e : Operadoras.values()) {
			rotulosPorDescricao.put(e.getDescricao(), e);
		}
		for (Operadoras e : Operadoras.values()) {
			mapaRotulos.put(e.getDescricao(), e.getCod());
		}
	}

	public String toString() {
		return "[" + descricao + "] " + cod;
	}

	public static Operadoras getRotuloPorCod(Integer codigo) {
		return rotulosPorCod.get(codigo);
	}

	public static Operadoras getRotuloPorSigla(String desc) {
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
		Operadoras.mapaRotulos = mapaRotulos;
	}

}