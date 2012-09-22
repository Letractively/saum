package br.com.clarotriagem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.entitades.Triagem;
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

	public int salvaTriagem(Triagem triagem) {
		try {
			getTriagemDAO().save(triagem);
			return 0;
		} catch (Exception e) {
			log.erro(e);
			return 1;
		}
	}

	public int salvaLote(TriagemLote triagemLoteNovo) {
		try {
			getTriagemLoteDAO().save(triagemLoteNovo);
			return 0;
		} catch (Exception e) {
			log.erro(e);
			return 1;
		}
	}

	public int getQuantidadeAparelhoTriado(Long idTriagemLote) {
		try {
			int ret = getTriagemDAO().getQuantidadeAparelhoTriado(idTriagemLote);
			return ret;
		} catch (Exception e) {
			log.erro(e);
			return 0;
		}
	}

	public List<Triagem> buscaTriagemAntiga(Triagem triagem, TriagemLote triagemLote) {
		try {
			List<Triagem> ret = getTriagemDAO().buscaTriagemAntiga(triagem, triagemLote);
			return ret;
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public void setTriagemLoteConcluido(TriagemLote triagemLote) {
		try {
			triagemLote.setConcluido(true);
			getTriagemLoteDAO().update(triagemLote);
		} catch (Exception e) {
			log.erro(e);
		}
	}

}
