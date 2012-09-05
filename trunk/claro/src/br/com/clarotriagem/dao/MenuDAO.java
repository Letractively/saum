package br.com.clarotriagem.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.Menu;

@Repository("menuDAO")
public class MenuDAO extends DAO<Menu>{

	public MenuDAO(){
		super(Menu.class);
	}

	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	public List<Menu> buscaMenus() throws HibernateException, Exception{
		String jpql = "from Menu c where c.ativo = true order by c.nome";
		Query query = entityManager.createQuery(jpql);
		List<Menu> menus = (List<Menu>) query.getResultList();
		return detatchObjects(menus);
	}
	
}
