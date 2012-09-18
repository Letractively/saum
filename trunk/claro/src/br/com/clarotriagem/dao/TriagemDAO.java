package br.com.clarotriagem.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.Triagem;

@Repository("triagemDAO")
public class TriagemDAO extends DAO<Triagem>{

	public TriagemDAO() {
		super(Triagem.class);
	}

	@Transactional(readOnly = true)
	public int getQuantidadeAparelhoTriado(Long idTriagemLote) throws Exception{
		String jpql = "select COUNT(t) from Triagem t where t.triagemLote.id = :idLT";
		Query query = entityManager.createQuery(jpql);
		
		query.setParameter("idLT", idTriagemLote);

		Number result = (Number) query.getSingleResult();
		return result.intValue();

	}

}
