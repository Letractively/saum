package br.com.clarotriagem.utils.enums;

import java.util.SortedMap;
import java.util.TreeMap;

public enum TiposSerial {
	
	SSN("SSN", 1),
	SSN_CAM("SSN_CAM", 2),
	SSN_CTV("SSN_CTV", 3),
	SSN_SWA("SSN_SWA", 4),
	SSN_DSC("SSN_DSC", 5),
	SSN_DVD("SSN_DVD", 6),
	SSN_MON("SSN_MON", 6),
	SSN_PRT("SSN_PRT", 7),
	SSN_REF("SSN_REF", 8),
	SSN_VC("SSN_V/C", 9),
	SSN_WM("SSN_W/M", 10),
	SSN_YEPP("SSN_YEPP", 11),
	MSN("MSN", 12),
	MSN_NEXTEL("MSN_NEXTEL", 13),
	ESN("ESN", 14),
	SSN_MDM("SSN_MDM", 15),
	SSN_NBK("SSN_NBK", 16),
	SSN_NPC("SSN_NPC", 17),
	SSN_LG("SSN_LG", 18),
	DATECODE("DATECODE", 19),
	IMEI("IMEI", 20),
	SERIAL("SERIAL", 21),
	OUTRO("Outro tipo", 22);
	
	private String descricao;
	private Integer cod;
	
	private static SortedMap<String, TiposSerial> rotulosPorDescricao = new TreeMap<String, TiposSerial>();
	private static SortedMap<Integer, TiposSerial> rotulosPorCod = new TreeMap<Integer, TiposSerial>();
	private static SortedMap<String, Integer> mapaRotulos = new TreeMap<String, Integer>();

	private TiposSerial(String descricao, Integer cod) {
		this.descricao = descricao;
		this.cod = cod;
	}

	static {
		for (TiposSerial e : TiposSerial.values()) {
			rotulosPorCod.put(e.getCod(), e);
		}
		for (TiposSerial e : TiposSerial.values()) {
			rotulosPorDescricao.put(e.getDescricao(), e);
		}
		for (TiposSerial e : TiposSerial.values()) {
			mapaRotulos.put(e.getDescricao(), e.getCod());
		}
	}

	public String toString() {
		return "[" + descricao + "] " + cod;
	}

	public static TiposSerial getRotuloPorCod(Integer codigo) {
		return rotulosPorCod.get(codigo);
	}

	public static TiposSerial getRotuloPorSigla(String desc) {
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
		TiposSerial.mapaRotulos = mapaRotulos;
	}

}