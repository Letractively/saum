package br.com.clarotriagem.controller.factory;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.clarotriagem.controller.BaseBean;
import br.com.clarotriagem.entitades.TriagemLote;
import br.com.clarotriagem.service.CalendarioService;
import br.com.clarotriagem.service.TriagemService;

public class TriagemValidador extends BaseBean{

	private static final long serialVersionUID = 8982154360605463893L;

	@Autowired
	protected CalendarioService calendarioService;
	
	@Autowired
	protected TriagemService triagemService;
	
	protected TriagemLote triagemLote;

	public TriagemLote getTriagemLote() {
		return triagemLote;
	}


	public void setTriagemLote(TriagemLote triagemLote) {
		this.triagemLote = triagemLote;
	}
	
}
