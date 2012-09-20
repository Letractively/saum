package br.com.clarotriagem.utils.enums;

import java.util.SortedMap;
import java.util.TreeMap;

public enum AparenciaAparelho {
	
	ACESSÓRIOS_DANIFICADOS("ACESSÓRIOS DANIFICADOS (FG)", 1, TiposResultadosTriagem.FG),
	ACIDENTADO("ACIDENTADO (FG)", 2, TiposResultadosTriagem.FG),
	APARELHO_AMASSADO("APARELHO AMASSADO (FG)", 3, TiposResultadosTriagem.FG),
	APARELHO_BLOQUEADO("APARELHO BLOQUEADO (FG)", 4, TiposResultadosTriagem.FG),
	APARELHO_CELULAR__OXIDADO_DANIFICADO("APARELHO CELULAR CONECTOR OXIDADO / DANIFICADO (FG)", 5, TiposResultadosTriagem.FG),
	APARELHO_CELULAR_SEM_EMBALAGEM_GIFT_BOX("APARELHO CELULAR SEM EMBALAGEM / GIFT-BOX (FG)", 6, TiposResultadosTriagem.FG),
	APARELHO_ETIQUETA_SERIAL_DANIFICADA("APARELHO COM ETIQUETA DE SERIAL DANIFICADA (FG)", 7, TiposResultadosTriagem.FG),
	APARELHO_SEM_ETIQUETA_SERIAL("APARELHO SEM ETIQUETA DE SERIAL (FG)", 8, TiposResultadosTriagem.FG),
	BOM("BOM (DOA)", 9, TiposResultadosTriagem.DOA),
	FALTANDO_PECA("FALTANDO PECA (FG)", 10, TiposResultadosTriagem.FG),
	GABINETE_FRONTAL_RISCADO("GABINETE FRONTAL RISCADO (FG)", 11, TiposResultadosTriagem.FG),
	PAINEL_LCD_PDP_CDT_DISPLAY_RISCADO("PAINEL LCD/PDP/CDT/DISPLAY RISCADO (G)", 12, TiposResultadosTriagem.G),
	PAINEL_LCD_PDP_CDT_DISPLAY_TRINCADO("PAINEL LCD/PDP/CDT/DISPLAY TRINCADO (FG)", 13, TiposResultadosTriagem.FG),
	PEQUENOS_RISCOS("PEQUENOS RISCOS (DOA)", 14, TiposResultadosTriagem.DOA),
	RISCOS_PROFUNDOS("RISCOS PROFUNDOS (G)", 15, TiposResultadosTriagem.G),
	SEM_EMBALAGEM("SEM EMBALAGEM (DOA)", 16, TiposResultadosTriagem.DOA),
	SISTEMA_OPERACIONAL_MODIFICADO("SISTEMA OPERACIONAL MODIFICADO (FG)", 17, TiposResultadosTriagem.FG),
	TAMPA_QUEBRADA("TAMPA QUEBRADA (FG)", 18, TiposResultadosTriagem.FG);
	
	
	private String descricao;
	private Integer cod;
	private TiposResultadosTriagem tiposResultadosTriagem;
	
	private static SortedMap<String, AparenciaAparelho> rotulosPorDescricao = new TreeMap<String, AparenciaAparelho>();
	private static SortedMap<Integer, AparenciaAparelho> rotulosPorCod = new TreeMap<Integer, AparenciaAparelho>();
	private static SortedMap<String, Integer> mapaRotulos = new TreeMap<String, Integer>();

	private AparenciaAparelho(String descricao, Integer cod, TiposResultadosTriagem tiposResultadosTriagem) {
		this.descricao = descricao;
		this.cod = cod;
		this.tiposResultadosTriagem = tiposResultadosTriagem;
	}

	static {
		for (AparenciaAparelho e : AparenciaAparelho.values()) {
			rotulosPorCod.put(e.getCod(), e);
		}
		for (AparenciaAparelho e : AparenciaAparelho.values()) {
			rotulosPorDescricao.put(e.getDescricao(), e);
		}
		for (AparenciaAparelho e : AparenciaAparelho.values()) {
			mapaRotulos.put(e.getDescricao(), e.getCod());
		}
	}

	public String toString() {
		return "[" + descricao + "] " + cod;
	}

	public static AparenciaAparelho getRotuloPorCod(Integer codigo) {
		return rotulosPorCod.get(codigo);
	}

	public static AparenciaAparelho getRotuloPorSigla(String desc) {
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
		AparenciaAparelho.mapaRotulos = mapaRotulos;
	}

	public TiposResultadosTriagem getTiposResultadosTriagem() {
		return tiposResultadosTriagem;
	}

}