package br.com.clarotriagem.controller.factory;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.clarotriagem.controller.BaseBean;
import br.com.clarotriagem.entitades.Calendario;
import br.com.clarotriagem.entitades.TriagemLote;
import br.com.clarotriagem.service.AparelhosService;
import br.com.clarotriagem.service.CalendarioService;
import br.com.clarotriagem.service.TriagemService;

public class TriagemValidador extends BaseBean{

	private static final long serialVersionUID = -7947032021158318414L;

	@Autowired
	protected CalendarioService calendarioService;
	
	@Autowired
	protected TriagemService triagemService;
	
	@Autowired
	protected AparelhosService aparelhosService;
	
	//usado pelos sub-datatables.
	protected TriagemLote triagemLote;
	
	//usados pela datatable principal
	protected LazyDataModel<Calendario> calendarios;
	protected Calendario calendario;
	
	//usados pelo caso de nova triagem
	protected TriagemLote triagemLoteNovo;
	
	protected boolean validaCamposADD() {
		boolean ret = true;
		if(triagemLoteNovo.getAparelhoModelo().getNome() == null || "".equalsIgnoreCase(triagemLoteNovo.getAparelhoModelo().getNome())){
			addErroMessage(null, getBunde("erro_msg"), getBunde("modelo_nao_encontrado"));
			ret = false;
		}
		if(triagemLoteNovo.getQuantidade() == null || triagemLoteNovo.getQuantidade().intValue() <= 0){
			addErroMessage(null, getBunde("erro_msg"), getBunde("erro_quantidade_aparelhos"));
			ret = false;
		}
		return ret;
	}

	protected boolean validaCamposEDIT(TriagemLote tl) {
		boolean ret = true;
		if(tl.getQuantidade() == null){
			addErroMessage(null, getBunde("erro_msg"), getBunde("erro_quantidade_aparelhos"));
			ret = false;
		}
		return ret;
	}
	
	
	
	
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
	public void setTriagemLoteNovo(TriagemLote triagemLoteNovo) {
		this.triagemLoteNovo = triagemLoteNovo;
	}
	public void onCancel(RowEditEvent event) {  
	}  

}
