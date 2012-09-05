package br.com.clarotriagem.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.Perfil;

@Repository("perfilDAO")
public class PerfilDAO extends DAO<Perfil>{

	public PerfilDAO(){
		super(Perfil.class);
	}

	@Transactional(readOnly = true)
	public List<Perfil> buscaPerfisAtivados() throws Exception{
		String jpql = "from Perfil p where p.ativado = :ativado order by p.nome desc";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("ativado", true);
		
		@SuppressWarnings("unchecked")
		List<Perfil> perfis = (List<Perfil>) query.getResultList();
		return perfis;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Perfil> findAll() throws Exception{
		String jpql = "from Perfil";
		Query query = entityManager.createQuery(jpql);
		
		List<Perfil> perfis = (List<Perfil>) query.getResultList();
		return perfis;
	}

}
