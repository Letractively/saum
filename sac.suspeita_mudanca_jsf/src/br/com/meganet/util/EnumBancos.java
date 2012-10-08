package br.com.meganet.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public enum EnumBancos {
	
	BANCO_DO_BRASIL("1", 10),
	BANCO_REAL("356", 13),
	BRADESCO("237", 11),
	CAIXA_ECONOMICA("104", 8),
	F2B("0", 8),//colocado mesmo valor do itau, pq quando  for gerar boleto da F2B é gerado boleto do Itau.
	HSBC("399", 13),
	ITAU("341", 8),
	NOSSACAIXA("151", 7),
	SANTANDER("33", 7),
    UNIBANCO("409", 14);
	
	private String codigo;
	private Integer tamNossoNumero;

	private EnumBancos(String codigo, Integer tamNossoNumero) {
		this.codigo = codigo;
		this.tamNossoNumero = tamNossoNumero;
	}

	public String getCodigo() {
		return codigo;
	}

	public int getTamNossoNumero() {
		return tamNossoNumero;
	}

	public String toString() {
		return "[" + codigo + "] " + tamNossoNumero;
	}

	public static EnumBancos getRotuloPorCodigo(String codigo) {
		return rotulosPorCodigo.get(codigo);
	}

	public static EnumBancos getRotuloPorSigla(Integer tamNossoNumero) {
		return rotulosPorTamNossoNumero.get(tamNossoNumero);
	}

	private static Map<String, EnumBancos> rotulosPorCodigo = new HashMap<String, EnumBancos>();
	static {
		for (EnumBancos e : EnumBancos.values()) {
			rotulosPorCodigo.put(e.getCodigo(), e);
		}
	}

	private static Map<Integer, EnumBancos> rotulosPorTamNossoNumero = new HashMap<Integer, EnumBancos>();
	static {
		for (EnumBancos e : EnumBancos.values()) {
			rotulosPorTamNossoNumero.put(e.getTamNossoNumero(), e);
		}
	}
	
	private static TreeMap<String, Integer> mapaRotulos = new TreeMap<String, Integer>();
	static {
		for (EnumBancos e : EnumBancos.values()) {
			mapaRotulos.put(e.getCodigo(), e.getTamNossoNumero());
		}
	}
	
	public static TreeMap<String, Integer> getMapaRotulos() {
		return mapaRotulos;
	}
}
