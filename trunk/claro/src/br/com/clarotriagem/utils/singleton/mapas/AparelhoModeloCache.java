package br.com.clarotriagem.utils.singleton.mapas;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import br.com.clarotriagem.entitades.AparelhoModelo;

public class AparelhoModeloCache {
	
	private static AparelhoModeloCache menuCache;
	private SortedMap<Long, AparelhoModelo> modelosPorID = new TreeMap<Long, AparelhoModelo>();
	private SortedMap<String, AparelhoModelo> modelosPorNOME = new TreeMap<String, AparelhoModelo>();
	
	private AparelhoModeloCache(){
		super();
	}

	public static AparelhoModeloCache getInstance(){
		if(menuCache == null){
			menuCache = new AparelhoModeloCache();
		}
		return menuCache;
	}
	
	public void addMap(List<AparelhoModelo> modelos){
		for (AparelhoModelo am : modelos) {
			this.modelosPorID.put(am.getId(), am);
			this.modelosPorNOME.put(am.getNome().toLowerCase(), am);
		}
	}

	public AparelhoModelo getModelosPorID(Long id) {
		return modelosPorID.get(id);
	}
	public SortedMap<Long, AparelhoModelo> getModelosPorID() {
		return modelosPorID;
	}

	public void setModelosPorID(SortedMap<Long, AparelhoModelo> modelosPorID) {
		this.modelosPorID = modelosPorID;
	}

	public AparelhoModelo getModelosPorNOME(String nome) {
		return modelosPorNOME.get(nome.toLowerCase());
	}
	public SortedMap<String, AparelhoModelo> getModelosPorNOME() {
		return modelosPorNOME;
	}

	public void setModelosPorNOME(SortedMap<String, AparelhoModelo> modelosPorNOME) {
		this.modelosPorNOME = modelosPorNOME;
	}

}
