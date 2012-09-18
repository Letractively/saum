package br.com.clarotriagem.utils.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public enum Familias {
	
    TC("TC", "Telefonia Celular", 0),
    LB("LB", "Atendimento", 2),
    TM("TM", "Atendimento", 3),
    MP3("MP3", "MP3", 4),
    AV("AV", "Audio e vídeo", 5),
    EL("EL", "Atendimento", 6),
    CAM("CAM", "Câmera", 7),
    HA("HA", "Atendimento", 8),
    INFO("INFO", "Informática", 9),
    PC("PC", "Atendimento", 10),
    IT("IT", "Atendimento", 11),
    TF("TF", "Telefonia fixa", 12),
    SCCR("SC/CR", "Atendimento", 13);
	
    private String sigla;
	private String descricao;
	private Integer cod;
	private boolean selecionado = false;
	
	private static SortedMap<String, Familias> rotulosPorSigla = new TreeMap<String, Familias>();
	private static SortedMap<Integer, Familias> rotulosPorCod = new TreeMap<Integer, Familias>();
	private static List<Familias> listaFamilias = new ArrayList<Familias>();
	private static SortedMap<String, Integer> mapaRotulos = new TreeMap<String, Integer>();

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
		for (Familias e : Familias.values()) {
			mapaRotulos.put(e.getSigla() + " - " + e.getDescricao(), e.getCod());
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

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}
	
}