package br.com.clarotriagem.controller;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.AparelhoMarca;
import br.com.clarotriagem.service.AparelhosService;
import br.com.clarotriagem.service.lazy.AparelhoMarcasListLazy;

@ManagedBean
@Scope("view")
@Component
public class MarcasBean extends BaseBean {

	private static final long serialVersionUID = -3936282489243948922L;

	@Autowired
	private AparelhosService aparelhosService;
	
	private LazyDataModel<AparelhoMarca> marcas;
	private AparelhoMarca marcaSelecionada;

	public LazyDataModel<AparelhoMarca> getMarcas() {
		if(marcas == null){
			marcas = new AparelhoMarcasListLazy(aparelhosService);
		}
		return marcas;
	}
	public void ativaMarca(){
		marcaSelecionada.setAtivo(true);
		aparelhosService.atualizaMarca(marcaSelecionada);
	}
	public void desativaMarca(){
		marcaSelecionada.setAtivo(false);
		aparelhosService.atualizaMarca(marcaSelecionada);
	}
	
	

	public AparelhosService getAparelhosService() {
		return aparelhosService;
	}
	public void setAparelhosService(AparelhosService aparelhosService) {
		this.aparelhosService = aparelhosService;
	}
	public AparelhoMarca getMarcaSelecionada() {
		return marcaSelecionada;
	}
	public void setMarcaSelecionada(AparelhoMarca marcaSelecionada) {
		this.marcaSelecionada = marcaSelecionada;
	}

}
