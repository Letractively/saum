package br.com.clarotriagem.controller.factory;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.clarotriagem.controller.BaseBean;
import br.com.clarotriagem.entitades.Calendario;
import br.com.clarotriagem.entitades.TriagemLote;
import br.com.clarotriagem.service.AparelhosService;
import br.com.clarotriagem.service.CalendarioService;
import br.com.clarotriagem.service.TriagemService;

public class TriagemSelecaoLoteValidador extends BaseBean{

	private static final long serialVersionUID = 8982154360605463893L;

	@Autowired
	protected CalendarioService calendarioService;
	
	@Autowired
	protected TriagemService triagemService;
	
	@Autowired
	protected AparelhosService aparelhosService;
	
	//usados pela datatable principal
	protected LazyDataModel<Calendario> calendarios;
	//usados pela datatable principal como selecao
	protected Calendario calendario;
	//usado pelos sub-datatable como seleção.
	protected TriagemLote triagemLote;
	
	public Calendario getCalendario() {
		return calendario;
	}
	public void setCalendario(Calendario calendario) {
		this.calendario = calendario;
	}
	public TriagemLote getTriagemLote() {
		return triagemLote;
	}
	public void setTriagemLote(TriagemLote triagemLote) {
		this.triagemLote = triagemLote;
	}
}
