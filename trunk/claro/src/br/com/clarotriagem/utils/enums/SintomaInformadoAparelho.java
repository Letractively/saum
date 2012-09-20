package br.com.clarotriagem.utils.enums;

import java.util.SortedMap;
import java.util.TreeMap;

public enum SintomaInformadoAparelho {
	
	APARELHO_SEM_DEFEITO("APARELHO SEM DEFEITO", 1, TiposResultadosTriagem.DOA),
	CAMERA_NÃO_FUNCIONA("CAMERA NÃO FUNCIONA", 2, TiposResultadosTriagem.DOA),
	CARREGADOR_COM_DEFEITO("CARREGADOR COM DEFEITO", 3, TiposResultadosTriagem.DOA),
	FONE_DE_OUVIDO_NÃO_FUNCIONA("FONE DE OUVIDO NÃO FUNCIONA", 4, TiposResultadosTriagem.DOA),
	LIGA_MAS_NÃO_FUNCIONA("LIGA MAS NÃO FUNCIONA", 5, TiposResultadosTriagem.DOA),
	NÃO_INFORMADO("NÃO INFORMADO", 6, TiposResultadosTriagem.DOA),
	NÃO_LIGA("NÃO LIGA", 7, TiposResultadosTriagem.DOA),
	SEM_SERVIÇO("SEM SERVIÇO", 8, TiposResultadosTriagem.DOA),
	SEM_SOM("SEM SOM", 9, TiposResultadosTriagem.DOA),
	SEM_TOM("SEM TOM", 10, TiposResultadosTriagem.DOA),
	APARELHO_COM_DESCOLORACAO("APARELHO COM DESCOLORACAO", 11, TiposResultadosTriagem.DOA),
	TECLADO_NÃO_FUNCIONA("TECLADO NÃO FUNCIONA", 12, TiposResultadosTriagem.DOA);
	
	private String descricao;
	private Integer cod;
	private TiposResultadosTriagem tiposResultadosTriagem;
	
	private static SortedMap<String, SintomaInformadoAparelho> rotulosPorDescricao = new TreeMap<String, SintomaInformadoAparelho>();
	private static SortedMap<Integer, SintomaInformadoAparelho> rotulosPorCod = new TreeMap<Integer, SintomaInformadoAparelho>();
	private static SortedMap<String, Integer> mapaRotulos = new TreeMap<String, Integer>();

	private SintomaInformadoAparelho(String descricao, Integer cod, TiposResultadosTriagem tiposResultadosTriagem) {
		this.descricao = descricao;
		this.cod = cod;
		this.tiposResultadosTriagem = tiposResultadosTriagem;
	}

	static {
		for (SintomaInformadoAparelho e : SintomaInformadoAparelho.values()) {
			rotulosPorCod.put(e.getCod(), e);
		}
		for (SintomaInformadoAparelho e : SintomaInformadoAparelho.values()) {
			rotulosPorDescricao.put(e.getDescricao(), e);
		}
		for (SintomaInformadoAparelho e : SintomaInformadoAparelho.values()) {
			mapaRotulos.put(e.getDescricao(), e.getCod());
		}
	}

	public String toString() {
		return "[" + descricao + "] " + cod;
	}

	public static SintomaInformadoAparelho getRotuloPorCod(Integer codigo) {
		return rotulosPorCod.get(codigo);
	}

	public static SintomaInformadoAparelho getRotuloPorSigla(String desc) {
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
		SintomaInformadoAparelho.mapaRotulos = mapaRotulos;
	}

	public TiposResultadosTriagem getTiposResultadosTriagem() {
		return tiposResultadosTriagem;
	}

}