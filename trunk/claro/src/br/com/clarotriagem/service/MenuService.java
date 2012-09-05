package br.com.clarotriagem.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.entitades.Menu;
import br.com.clarotriagem.service.factory.ServiceFactory;
import br.com.clarotriagem.utils.Logger;

@Service("menuService")
@Transactional
public class MenuService extends ServiceFactory<MenuService> {

	private final static Logger log = new Logger(MenuService.class);
	
	public MenuService(){
		super();
	}

	public List<Menu> buscaMenus() {
		try {
			return getMenuDAO().buscaMenus();
		} catch (HibernateException e) {
			log.erro(e);
			return null;
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

}
