package br.com.clarotriagem.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.AparelhoModelo;

@Repository("aparelhoModeloDAO")
public class AparelhoModeloDAO extends DAO<AparelhoModelo>{

	public AparelhoModeloDAO() {
		super(AparelhoModelo.class);
	}

	public List<AparelhoModelo> findModelosAtivos() throws Exception{
		String jpql = "from AparelhoModelo am where am.aparelhoMarca.ativo = :at and am.ativo = :atm";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("at", true);
		query.setParameter("atm", true);
		
		@SuppressWarnings("unchecked")
		List<AparelhoModelo> ret = (List<AparelhoModelo>) query.getResultList();
		return ret;
	}

	public int getQtdTotalModelos(Long idMarca) throws Exception{
		String jpql = "select COUNT(a) from AparelhoModelo a where a.aparelhoMarca.id = :idMarca";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("idMarca", idMarca);

		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}

	public List<AparelhoModelo> getModelos(Long idMarca) throws Exception{
		
		String jpql = "from AparelhoModelo a where a.aparelhoMarca.id = :idMarca order by a.nome asc";

		Query query = entityManager.createQuery(jpql);
		query.setParameter("idMarca", idMarca);

		@SuppressWarnings("unchecked")
		List<AparelhoModelo> ret = (List<AparelhoModelo>) query.getResultList();
		return detatchObjects(ret);
	}

}
