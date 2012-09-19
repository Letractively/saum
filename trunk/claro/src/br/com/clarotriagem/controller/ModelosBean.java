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
import br.com.clarotriagem.utils.enums.Familias;
import br.com.clarotriagem.utils.enums.TiposSerial;

@ManagedBean
@Scope("view")
@Component
public class ModelosBean extends BaseBean {

	private static final long serialVersionUID = -3936282489243948922L;

	@Autowired
	private AparelhosService aparelhosService;

	private List<AparelhoModelo> modelos;
	private AparelhoModelo modeloSelecionado;
	private AparelhoModelo novoModelo;
	private AparelhoMarca aparelhoMarcaEscolhida;

	private SortedMap<String, Long> marcas;
	private SortedMap<String, Integer> comboFamilia;
	private SortedMap<String, Integer> comboTiposSerial;

	public void buscaModelos() {
		getModelos();
	}
	public List<AparelhoModelo> getModelos() {
		modelos = aparelhosService.buscaModelosTodos(getAparelhoMarcaEscolhida().getId());
		return modelos;
	}

	public SortedMap<String, Long> getMarcas() {
		if (marcas == null) {
			marcas = aparelhosService.getMarcasParaCombo();
		}
		return marcas;
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
		getNovoModelo().setAparelhoMarca(aparelhoMarcaEscolhida);
		if (validaModelo()) {
			getNovoModelo().setAtivo(true);
			aparelhosService.insereModelo(getNovoModelo());
			setNovoModelo(new AparelhoModelo());
			addInfoMessage(getBunde("inserir_modelo_sucesso"));
		}
	}
	
	private boolean validaModelo() {
		boolean ret = true;
		if(getNovoModelo().getNome() == null || "".equalsIgnoreCase(getNovoModelo().getNome().trim())){
			ret = false;
			addErroMessage(null, getTituloApp(), getBunde("erro_nome_modelo"));
		}
		
		if(getNovoModelo().getFamilia() == null || getNovoModelo().getFamilia().intValue() == 0){
			ret = false;
			addErroMessage(null, getTituloApp(), getBunde("erro_familia_modelo"));
		}
		
		if(getNovoModelo().getIdentificador1() == null || getNovoModelo().getIdentificador1().intValue() == 0){
			ret = false;
			addErroMessage(null, getTituloApp(), getBunde("erro_minimo_serial_modelo"));
		}
		
		int id1 = getNovoModelo().getIdentificador1();
		int id2 = getNovoModelo().getIdentificador2();
		int id3 = getNovoModelo().getIdentificador3();
		int id4 = getNovoModelo().getIdentificador4();
		
		if(id1 == id2 && id2 != 0){
			ret = false;
			addErroMessage(null, getTituloApp(), getBunde("erro_igualdade_serial_2_modelo"));
		}
		if(id1 == id3 && id3 != 0){
			ret = false;
			addErroMessage(null, getTituloApp(), getBunde("erro_igualdade_serial_3_modelo"));
		}
		if(id1 == id4 && id4 != 0){
			ret = false;
			addErroMessage(null, getTituloApp(), getBunde("erro_igualdade_serial_4_modelo"));
		}

		if(id2 == id3 && id3 != 0){
			ret = false;
			addErroMessage(null, getTituloApp(), getBunde("erro_igualdade_serial_3_modelo"));
		}
		if(id2 == id4 && id4 != 0){
			ret = false;
			addErroMessage(null, getTituloApp(), getBunde("erro_igualdade_serial_4_modelo"));
		}

		if(id3 == id4 && id4 != 0){
			ret = false;
			addErroMessage(null, getTituloApp(), getBunde("erro_igualdade_serial_4_modelo"));
		}
		
		return ret;
	}

	public SortedMap<String, Integer> getComboFamilia() {
		if(comboFamilia == null){
			comboFamilia = Familias.getMapaRotulos();
		}
		return comboFamilia;
	}

	public SortedMap<String, Integer> getComboTiposSerial() {
		if(comboTiposSerial == null){
			comboTiposSerial = TiposSerial.getMapaRotulos();
		}
		return comboTiposSerial;
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

	public AparelhoModelo getNovoModelo() {
		if(novoModelo == null){
			novoModelo = new AparelhoModelo();
		}
		return novoModelo;
	}

	public void setNovoModelo(AparelhoModelo novoModelo) {
		this.novoModelo = novoModelo;
	}

	public AparelhoMarca getAparelhoMarcaEscolhida() {
		if(aparelhoMarcaEscolhida == null){
			aparelhoMarcaEscolhida = new AparelhoMarca();
		}
		return aparelhoMarcaEscolhida;
	}

	public void setAparelhoMarcaEscolhida(AparelhoMarca aparelhoMarcaEscolhida) {
		this.aparelhoMarcaEscolhida = aparelhoMarcaEscolhida;
	}

}
