package br.com.clarotriagem.utils.enums;

import java.util.SortedMap;
import java.util.TreeMap;

public enum TiposSerial {
	//desc, cod, tam, onkey, onblur
	SSN			("SSN", 1, 11, null, null),
	SSN_CAM		("SSN_CAM", 2, 15, null, null),
	SSN_CTV		("SSN_CTV", 3, 15, null, null),
	SSN_SWA		("SSN_SWA", 4, 14, null, null),
	SSN_DSC		("SSN_DSC", 5, 15, null, null),
	SSN_DVD		("SSN_DVD", 6, 15, null, null),
	SSN_MON		("SSN_MON", 6, 15, null, null),
	SSN_PRT		("SSN_PRT", 7, 25, null, null),
	SSN_REF		("SSN_REF", 8, 15, null, null),
	SSN_VC		("SSN_V/C", 9, 15, null, null),
	SSN_WM		("SSN_W/M", 10, 15, null, null),
	SSN_YEPP	("SSN_YEPP", 11, 15, null, null),
	MSN			("MSN", 12, 10, null, null),
	MSN_NEXTEL	("MSN_NEXTEL", 13, 10, null, null),
	ESN			("ESN", 14, 11, null, null),
	SSN_MDM		("SSN_MDM", 15, 16, null, null),
	SSN_NBK		("SSN_NBK", 16, 15, null, null),
	SSN_NPC		("SSN_NPC", 17, 15, null, null),
	SSN_LG		("SSN_LG", 18, 50, null, null),
	DATECODE	("DATECODE", 19, 5, null, null),
	IMEI		("IMEI", 20, 50, "formatarNumero(this);", null),
	SERIAL		("SERIAL", 21, 50, null, null),
	OUTRO		("Outro tipo", 22, 50, null, null);
	
	private String descricao;
	private Integer cod;
	private Integer tamanho;
	private String onkeyup;
	private String onblur;
	
	private static SortedMap<String, TiposSerial> rotulosPorDescricao = new TreeMap<String, TiposSerial>();
	private static SortedMap<Integer, TiposSerial> rotulosPorCod = new TreeMap<Integer, TiposSerial>();
	private static SortedMap<String, Integer> mapaRotulos = new TreeMap<String, Integer>();

	private TiposSerial(String descricao, Integer cod, Integer tamanho, String onkeyup, String onblur) {
		this.descricao = descricao;
		this.cod = cod;
		this.tamanho = tamanho;
		this.onkeyup = onkeyup;
		this.onblur = onblur;
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

	public Integer getTamanho() {
		return tamanho;
	}

	public String getOnkeyup() {
		return onkeyup;
	}

	public String getOnblur() {
		return onblur;
	}
	
	public static SortedMap<String, Integer> getMapaRotulos() {
		return mapaRotulos;
	}

	public static void setMapaRotulos(SortedMap<String, Integer> mapaRotulos) {
		TiposSerial.mapaRotulos = mapaRotulos;
	}


}