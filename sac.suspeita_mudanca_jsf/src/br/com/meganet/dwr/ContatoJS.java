package br.com.meganet.dwr;

import br.com.meganet.facade.ContatoFacade;
import br.com.meganet.hbm.vo.Contato;


public class ContatoJS {
	
	private ContatoFacade contatoFacade;
	public void setContatoFacade(ContatoFacade contatoFacade) {
		this.contatoFacade = contatoFacade;
	}

	public int insereContato(Contato contato){
		int retorno = contatoFacade.insereContato(contato);
		return retorno;
	}

}
