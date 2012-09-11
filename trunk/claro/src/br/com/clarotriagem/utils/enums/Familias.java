package br.com.clarotriagem.utils.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public enum Familias {
	
    TC("TC", "Atendimento", 0),
    LB("LB", "Atendimento", 2),
    TM("TM", "Atendimento", 3),
    MP3("MP3", "MP3", 4),
    AV("AV", "Audio e v�deo", 5),
    EL("EL", "Atendimento", 6),
    CAM("CAM", "C�mera", 7),
    HA("HA", "Atendimento", 8),
    INFO("INFO", "Inform�tica", 9),
    PC("PC", "Atendimento", 10),
    IT("IT", "Atendimento", 11),
    TF("TF", "Atendimento", 12),
    SCCR("SC/CR", "Atendimento", 13);
	
    private String sigla;
	private String descricao;
	private Integer cod;
	
	private static SortedMap<String, Familias> rotulosPorSigla = new TreeMap<String, Familias>();
	private static SortedMap<Integer, Familias> rotulosPorCod = new TreeMap<Integer, Familias>();
	private static List<Familias> listaFamilias = new ArrayList<Familias>();

	private Familias(String sigla, String descricao, Integer cod) {
		this.descricao = descricao;
		this.cod = cod;
		this.sigla = sigla;
	}

	static {
		for (Familias e : Familias.values()) {
			rotulosPorCod.put(e.getCod(), e);
		}
		for (Familias e : Familias.values()) {
			rotulosPorSigla.put(e.getSigla(), e);
		}
		for (Familias e : Familias.values()) {
			listaFamilias.add(e);
		}
	}

	public String toString() {
		return "[" + sigla + "] " + cod + ": " + descricao;
	}

	public static Familias getRotuloPorCod(Integer codigo) {
		return rotulosPorCod.get(codigo);
	}

	public static Familias getRotuloPorSigla(String desc) {
		return rotulosPorSigla.get(desc);
	}
	
	public static List<Familias> getListaFamilia() {
		return listaFamilias;
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
	
}