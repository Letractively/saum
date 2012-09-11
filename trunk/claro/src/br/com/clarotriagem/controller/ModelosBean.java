package br.com.clarotriagem.controller;

import java.util.List;
import java.util.SortedMap;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.AparelhoMarca;
import br.com.clarotriagem.entitades.AparelhoModelo;
import br.com.clarotriagem.service.AparelhosService;

@ManagedBean
@Scope("view")
@Component
public class ModelosBean extends BaseBean {

	private static final long serialVersionUID = -3936282489243948922L;

	@Autowired
	private AparelhosService aparelhosService;

	private List<AparelhoModelo> modelos;
	private AparelhoModelo modeloSelecionado;
	private Long idMarcaSelecionada;
	private String nomeNovoModelo;
	private SortedMap<String, Long> marcas;

	public List<AparelhoModelo> getModelos() {
		if (idMarcaSelecionada != null) {
			modelos = aparelhosService.buscaModelosTodos(idMarcaSelecionada);
		}
		return modelos;
	}

	public SortedMap<String, Long> getMarcas() {
		if (marcas == null) {
			marcas = aparelhosService.getMarcasParaCombo();
		}
		return marcas;
	}

	public void buscaModelos() {
		modelos = aparelhosService.buscaModelosTodos(idMarcaSelecionada);
	}

	public void ativaModelo() {
		if (!modeloSelecionado.getAtivo()) {
			modeloSelecionado.setAtivo(true);
			aparelhosService.atualizaModelo(modeloSelecionado);
			addInfoMessage(getBunde("alterado_sucesso"));
		}
	}

	public void desativaModelo() {
		if (modeloSelecionado.getAtivo()) {
			modeloSelecionado.setAtivo(false);
			aparelhosService.atualizaModelo(modeloSelecionado);
			addInfoMessage(getBunde("alterado_sucesso"));
		}
	}

	public void salva() {
		if (nomeNovoModelo != null && !"".equalsIgnoreCase(nomeNovoModelo.trim())) {
			AparelhoModelo mod = new AparelhoModelo();
			mod.setAparelhoMarca(new AparelhoMarca(idMarcaSelecionada));
			mod.setAtivo(true);
			mod.setNome(nomeNovoModelo);
			aparelhosService.insereModelo(mod);
			nomeNovoModelo = "";
			addInfoMessage(getBunde("inserir_modelo_sucesso"));
		} else {
			addErroMessage("marca_novo", getTituloApp(), getBunde("erro_inserir_modelo"));
		}
	}

	public void setMarcas(SortedMap<String, Long> marcas) {
		this.marcas = marcas;
	}

	public AparelhosService getAparelhosService() {
		return aparelhosService;
	}

	public void setAparelhosService(AparelhosService aparelhosService) {
		this.aparelhosService = aparelhosService;
	}

	public AparelhoModelo getModeloSelecionado() {
		return modeloSelecionado;
	}

	public void setModeloSelecionado(AparelhoModelo modeloSelecionado) {
		this.modeloSelecionado = modeloSelecionado;
	}

	public Long getIdMarcaSelecionada() {
		return idMarcaSelecionada;
	}

	public void setIdMarcaSelecionada(Long idMarcaSelecionada) {
		this.idMarcaSelecionada = idMarcaSelecionada;
	}

	public String getNomeNovoModelo() {
		return nomeNovoModelo;
	}

	public void setNomeNovoModelo(String nomeNovoModelo) {
		this.nomeNovoModelo = nomeNovoModelo;
	}

}
