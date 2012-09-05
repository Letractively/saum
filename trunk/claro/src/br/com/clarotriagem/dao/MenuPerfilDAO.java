package br.com.clarotriagem.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.MenuPerfil;
import br.com.clarotriagem.entitades.Perfil;

@Repository("menuPerfilDAO")
public class MenuPerfilDAO extends DAO<MenuPerfil>{

	public MenuPerfilDAO(){
		super(MenuPerfil.class);
	}

	@Transactional(readOnly = true)
	public List<MenuPerfil> buscaMenuLateralEsquerda(Perfil perfil) throws Exception {
		String jpql = "from MenuPerfil c where perfil.perfilId = :perfil order by menu.coluna, menu.ordem, menu.nome";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("perfil", perfil.getPerfilId());
		
		@SuppressWarnings("unchecked")
		List<MenuPerfil> menus = (List<MenuPerfil>) query.getResultList();
		return detatchObjects(menus);
	}

	
}
