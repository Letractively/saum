package br.com.clarotriagem.utils.singleton.mapas;

import java.util.List;

import br.com.clarotriagem.entitades.Menu;

public class MenuCache {
	
	private static MenuCache menuCache;
	private List<Menu> menus;
	
	
	private MenuCache(){
		super();
	}

	public static MenuCache getInstance(){
		if(menuCache == null){
			menuCache = new MenuCache();
		}
		return menuCache;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
}
