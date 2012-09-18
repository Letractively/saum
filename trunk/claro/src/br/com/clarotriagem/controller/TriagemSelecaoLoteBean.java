package br.com.clarotriagem.controller;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.LazyDataModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.controller.factory.TriagemSelecaoLoteValidador;
import br.com.clarotriagem.entitades.Calendario;
import br.com.clarotriagem.service.lazy.CalendarioListLazy;

@ManagedBean
@Scope("view")
@Component
public class TriagemSelecaoLoteBean extends TriagemSelecaoLoteValidador {

	private static final long serialVersionUID = -7700015493875891556L;

	public LazyDataModel<Calendario> getCalendarios() {
		if(calendarios == null){
			calendarios = new CalendarioListLazy(calendarioService, getUsuarioLogado().getPerfil().getVisualizaTodosAgendamentos(), getUsuarioLogado());
		}
		return calendarios;
	}
}
