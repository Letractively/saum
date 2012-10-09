package br.com.meganet.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.meganet.hbm.DAO.MenuDAO;
import br.com.meganet.hbm.vo.Menu;
import br.com.meganet.hbm.vo.MenuVO;
import br.com.meganet.hbm.vo.SubMenuVO;
import br.com.meganet.util.ConfigUtil;

public class MenuBO {
	
	private MenuDAO menuDAO;
	public MenuDAO getMenuDAO() {
		return menuDAO;
	}
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}


	public List<MenuVO> montaMenu(int opcao, String indiceMenu) {
		
		List<MenuVO> mapMenu = new ArrayList<MenuVO>();
		List<Menu> listaMenu = new ArrayList<Menu>();
		if(opcao == 0){
			listaMenu = menuDAO.procuraMenusUsuario(indiceMenu);
		}else if(opcao == 1){
			listaMenu = menuDAO.procuraMenusUsuarioIphone(indiceMenu);
		}else if(opcao == 2){
			listaMenu = menuDAO.procuraMenusUsuarioSemLogin();
		}

		MenuVO mn0 = new MenuVO();
		mn0.setSeq(0);
		mn0.setId(ConfigUtil.getInstance().getProperty("empresa2",ConfigUtil.getInstance().getProperty("empresa2", "Meganet")));
		Collection<SubMenuVO> colSb0 = new ArrayList<SubMenuVO>();

		MenuVO mn1 = new MenuVO();
		mn1.setSeq(1);
		mn1.setId("Pagamentos");
		Collection<SubMenuVO> colSb1 = new ArrayList<SubMenuVO>();

		MenuVO mn2 = new MenuVO();
		mn2.setSeq(2);
		mn2.setId("Cadastro");
		Collection<SubMenuVO> colSb2 = new ArrayList<SubMenuVO>();

		MenuVO mn3 = new MenuVO();
		mn3.setSeq(3);
		mn3.setId("Dúvidas");
		Collection<SubMenuVO> colSb3 = new ArrayList<SubMenuVO>();

		MenuVO mn4 = new MenuVO();
		mn4.setSeq(4);
		mn4.setId("Clientes");
		Collection<SubMenuVO> colSb4 = new ArrayList<SubMenuVO>();

		MenuVO mn5 = new MenuVO();
		mn5.setSeq(5);
		mn5.setId("Boletos");
		Collection<SubMenuVO> colSb5 = new ArrayList<SubMenuVO>();

		MenuVO mn6 = new MenuVO();
		mn6.setSeq(6);
		mn6.setId("Administrativo");
		Collection<SubMenuVO> colSb6 = new ArrayList<SubMenuVO>();

		MenuVO mn7 = new MenuVO();
		mn7.setSeq(7);
		mn7.setId("Relatorio");
		Collection<SubMenuVO> colSb7 = new ArrayList<SubMenuVO>();

		MenuVO mn8 = new MenuVO();
		mn8.setSeq(8);
		mn8.setId("Demandas");
		Collection<SubMenuVO> colSb8 = new ArrayList<SubMenuVO>();

		MenuVO mn9 = new MenuVO();
		mn9.setSeq(9);
		mn9.setId("Painel de controle");
		Collection<SubMenuVO> colSb9 = new ArrayList<SubMenuVO>();
		
		for (Iterator<Menu> iterator = listaMenu.iterator(); iterator.hasNext();) {
			Menu menu = (Menu) iterator.next();

			switch (menu.getMenuColuna()) {
			case 0:
				SubMenuVO sb0 = new SubMenuVO();
				sb0.setText(menu.getMenuText());
				sb0.setUrl(menu.getMenuFuncaoJs());
				sb0.setMenuAtivado(menu.getMenuAtivado());
				mn0.setPossuiItem(true);
				colSb0.add(sb0);
				break;
			case 1:
				SubMenuVO sb1 = new SubMenuVO();
				sb1.setText(menu.getMenuText());
				sb1.setUrl(menu.getMenuFuncaoJs());
				sb1.setMenuAtivado(menu.getMenuAtivado());
				mn1.setPossuiItem(true);
				colSb1.add(sb1);
				break;
			case 2:
				SubMenuVO sb2 = new SubMenuVO();
				sb2.setText(menu.getMenuText());
				sb2.setUrl(menu.getMenuFuncaoJs());
				sb2.setMenuAtivado(menu.getMenuAtivado());
				mn2.setPossuiItem(true);
				colSb2.add(sb2);
				break;
			case 3:
				SubMenuVO sb3 = new SubMenuVO();
				sb3.setText(menu.getMenuText());
				sb3.setUrl(menu.getMenuFuncaoJs());
				sb3.setMenuAtivado(menu.getMenuAtivado());
				mn3.setPossuiItem(true);
				colSb3.add(sb3);
				break;
			case 4:
				SubMenuVO sb4 = new SubMenuVO();
				sb4.setText(menu.getMenuText());
				sb4.setUrl(menu.getMenuFuncaoJs());
				sb4.setMenuAtivado(menu.getMenuAtivado());
				mn4.setPossuiItem(true);
				colSb4.add(sb4);
				break;
			case 5:
				SubMenuVO sb5 = new SubMenuVO();
				sb5.setText(menu.getMenuText());
				sb5.setUrl(menu.getMenuFuncaoJs());
				sb5.setMenuAtivado(menu.getMenuAtivado());
				mn5.setPossuiItem(true);
				colSb5.add(sb5);
				break;
			case 6:
				SubMenuVO sb6 = new SubMenuVO();
				sb6.setText(menu.getMenuText());
				sb6.setUrl(menu.getMenuFuncaoJs());
				sb6.setMenuAtivado(menu.getMenuAtivado());
				mn6.setPossuiItem(true);
				colSb6.add(sb6);
				break;
			case 7:
				SubMenuVO sb7 = new SubMenuVO();
				sb7.setText(menu.getMenuText());
				sb7.setUrl(menu.getMenuFuncaoJs());
				sb7.setMenuAtivado(menu.getMenuAtivado());
				mn7.setPossuiItem(true);
				colSb7.add(sb7);
				break;
			case 8:
				SubMenuVO sb8 = new SubMenuVO();
				sb8.setText(menu.getMenuText());
				sb8.setUrl(menu.getMenuFuncaoJs());
				sb8.setMenuAtivado(menu.getMenuAtivado());
				mn8.setPossuiItem(true);
				colSb8.add(sb8);
				break;
			case 9:
				SubMenuVO sb9 = new SubMenuVO();
				sb9.setText(menu.getMenuText());
				sb9.setUrl(menu.getMenuFuncaoJs());
				sb9.setMenuAtivado(menu.getMenuAtivado());
				mn9.setPossuiItem(true);
				colSb9.add(sb9);
				break;
			}

		}

		mn9.setItemdata(colSb9);
		mn8.setItemdata(colSb8);
		mn7.setItemdata(colSb7);
		mn6.setItemdata(colSb6);
		mn5.setItemdata(colSb5);
		mn4.setItemdata(colSb4);
		mn3.setItemdata(colSb3);
		mn2.setItemdata(colSb2);
		mn1.setItemdata(colSb1);
		mn0.setItemdata(colSb0);

		mapMenu.add(mn0);
		mapMenu.add(mn1);
		mapMenu.add(mn2);
		mapMenu.add(mn3);
		mapMenu.add(mn4);
		mapMenu.add(mn5);
		mapMenu.add(mn6);
		mapMenu.add(mn7);
		mapMenu.add(mn8);
		mapMenu.add(mn9);

		return mapMenu;


	}
	
	
	public List<Menu> buscaMenu(int coluna){
			List<Menu> dadosMenu = menuDAO.findByProperty(MenuDAO.MENU_COLUNA, coluna);
			return dadosMenu;
	}
	
	public int alteraMenu(Menu menu) throws Exception{
		try {
			menuDAO.attachDirty(menu);
			return 1;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}


	public List<Menu> buscaMenu() {
		List<Menu> dadosMenu = menuDAO.procuraMenusParaAtribuirUsuario();
		return dadosMenu;
	}

}
