package br.com.clarotriagem.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.controller.factory.TriagemLoteValidador;
import br.com.clarotriagem.entitades.AparelhoModelo;
import br.com.clarotriagem.entitades.Calendario;
import br.com.clarotriagem.entitades.TriagemLote;
import br.com.clarotriagem.service.lazy.CalendarioListLazy;

@ManagedBean
@Scope("view")
@Component
public class TriagemLoteBean extends TriagemLoteValidador {

	private static final long serialVersionUID = 5027471093087360454L;

	public LazyDataModel<Calendario> getCalendarios() {
		if(calendarios == null){
			calendarios = new CalendarioListLazy(calendarioService, getUsuarioLogado().getPerfil().getVisualizaTodosAgendamentos(), getUsuarioLogado());
		}
		return calendarios;
	}
	
	public void preparaObjetosParaInserirLote(){
		triagemLoteNovo = new TriagemLote();
	}
	public List<AparelhoModelo> buscaModelos(String query){
		List<AparelhoModelo> ret = aparelhosService.buscaModelosAparelhos(query);
		return ret;
	}
	public void salvaLoteTriagem(){
		if(validaCamposADD()){
			AparelhoModelo am = aparelhosService.findByName(triagemLoteNovo.getAparelhoModelo().getNome());
			if(am == null){
				addErroMessage(null, getBunde("erro_msg"), getBunde("modelo_nao_encontrado"));
			}
			triagemLoteNovo.setAparelhoModelo(am);
			triagemLoteNovo.setCalendario(calendario);
			triagemLoteNovo.setCliente(calendario.getWarehouse());
			triagemLoteNovo.setDataCriacaoLote(new Timestamp(System.currentTimeMillis()));
			triagemService.salvaLote(triagemLoteNovo);
			calendarios = new CalendarioListLazy(calendarioService, getUsuarioLogado().getPerfil().getVisualizaTodosAgendamentos(), getUsuarioLogado());
			calendario.getTriagemLotes().add(triagemLoteNovo);
			triagemLoteNovo = new TriagemLote();
		}
	}
	
	public void onEdit(RowEditEvent event) {
		if(validaCamposEDIT((TriagemLote) event.getObject())){
			TriagemLote tl = (TriagemLote) event.getObject();
			triagemService.update(tl);
		}
	}  
	public void onRowSelect(SelectEvent event) {  
		calendario = ((Calendario) event.getObject());
	}
	public TriagemLote getTriagemLoteNovo() {
		if(triagemLoteNovo == null){
			triagemLoteNovo = new TriagemLote();
		}
		return triagemLoteNovo;
	}
}
