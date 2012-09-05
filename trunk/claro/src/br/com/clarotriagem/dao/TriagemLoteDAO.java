package br.com.clarotriagem.dao;

import org.springframework.stereotype.Repository;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.TriagemLote;

@Repository("triagemLoteDAO")
public class TriagemLoteDAO extends DAO<TriagemLote>{

	public TriagemLoteDAO() {
		super(TriagemLote.class);
	}

}
