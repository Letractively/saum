package br.com.clarotriagem.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.AparelhoModelo;
import br.com.clarotriagem.entitades.Triagem;
import br.com.clarotriagem.entitades.TriagemLote;

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

	@Transactional(readOnly = true)
	public List<Triagem> buscaTriagemAntiga(Triagem triagem, TriagemLote triagemLote) throws Exception{
		String jpql = "from Triagem t where " +
				"((t.identificador1 = :at1 and t.identificador1Utilizado = true) or " +
				"(t.identificador2 = :at2 and t.identificador2Utilizado = true) or " +
				"(t.identificador3 = :at3 and t.identificador3Utilizado = true) or " +
				"(t.identificador4 = :at4 and t.identificador4Utilizado = true) or " +
				"(t.identificador5 = :at5 and t.identificador5Utilizado = true)) and " +
				"t.triagemLote.id = :tl";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("at1", triagem.getIdentificador1());
		query.setParameter("at2", triagem.getIdentificador2());
		query.setParameter("at3", triagem.getIdentificador3());
		query.setParameter("at4", triagem.getIdentificador4());
		query.setParameter("at5", triagem.getIdentificador5());
		query.setParameter("tl", triagemLote.getId());
		
		@SuppressWarnings("unchecked")
		List<Triagem> ret = (List<Triagem>) query.getResultList();
		return ret;
	}

}
