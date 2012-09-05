package br.com.clarotriagem.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.Dominio;

@Repository("dominiosDAO")
public class DominiosDAO extends DAO<Dominio>{

	public DominiosDAO() {
		super(Dominio.class);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Dominio> findAll() throws Exception {
		String jpql = "from Dominio";
		Query query = entityManager.createQuery(jpql);
		List<Dominio> menus = (List<Dominio>) query.getResultList();
		return detatchObjects(menus);
	}

}
