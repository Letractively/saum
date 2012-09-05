package br.com.clarotriagem.filtrosEMonitores;

import java.util.List;

import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.Menu;
import br.com.clarotriagem.filtrosEMonitores.interfaces.CarregarMenuServiceI;
import br.com.clarotriagem.service.MenuService;
import br.com.clarotriagem.utils.Logger;
import br.com.clarotriagem.utils.singleton.mapas.MenuCache;

@Component("carregarMenuService")
public class CarregarMenuService implements CarregarMenuServiceI {
	
	private static Logger log = new Logger(CarregarMenuService.class);
	
	@Autowired
	@ManagedProperty("#{menuService}")
	private MenuService menuService;

	@Async	
	@Override
	public void job() {
		log.info("Carregando os menus do sistema");
		try {
			List<Menu> menus = menuService.buscaMenus();
			if(menus != null && menus.size() > 0){
				MenuCache.getInstance().setMenus(menus);
			}
		} catch (Exception e) {
			log.erro(e);
			Thread.currentThread().interrupt();
		}
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

}
