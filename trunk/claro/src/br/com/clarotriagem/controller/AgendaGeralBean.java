package br.com.clarotriagem.controller;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.Calendario;
import br.com.clarotriagem.service.CalendarioService;
import br.com.clarotriagem.service.lazy.CalendarioGeralOuUsuarioNaoConcluidoListLazy;

@ManagedBean
@Scope("view")
@Component
public class AgendaGeralBean extends BaseBean {

	private static final long serialVersionUID = -2357716957332910635L;

	@Autowired
	private CalendarioService calendarioService;
	
	private LazyDataModel<Calendario> calendarios;
	

	public LazyDataModel<Calendario> getCalendarios() {
		if(calendarios == null){
			calendarios = new CalendarioGeralOuUsuarioNaoConcluidoListLazy(calendarioService, true, getUsuarioLogado());
		}
		return calendarios;
	}

	public CalendarioService getCalendarioService() {
		return calendarioService;
	}

	public void setCalendarioService(CalendarioService calendarioService) {
		this.calendarioService = calendarioService;
	}
	
}
