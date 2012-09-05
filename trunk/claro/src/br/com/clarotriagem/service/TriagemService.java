package br.com.clarotriagem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.entitades.TriagemLote;
import br.com.clarotriagem.service.factory.ServiceFactory;
import br.com.clarotriagem.utils.Logger;

@Service("triagemService")
@Transactional
public class TriagemService extends ServiceFactory<TriagemService>{
	
	private final static Logger log = new Logger(TriagemService.class);
	
	public TriagemService(){
		super();
	}

	public void update(TriagemLote tl) {
		try {
			getTriagemLoteDAO().update(tl);
		} catch (Exception e) {
			log.erro(e);
		}
	}

	public void salvaLote(TriagemLote triagemLoteNovo) {
		try {
			getTriagemLoteDAO().save(triagemLoteNovo);
		} catch (Exception e) {
			log.erro(e);
		}
	}

}
