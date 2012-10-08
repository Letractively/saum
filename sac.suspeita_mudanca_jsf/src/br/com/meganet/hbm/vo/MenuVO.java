package br.com.meganet.hbm.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class MenuVO implements Serializable{
	
	private static final long serialVersionUID = 5895598535480079777L;
	private String id = "";
	private Collection<SubMenuVO> itemdata = new ArrayList<SubMenuVO>();
	private boolean possuiItem = false;
	private int seq = 0;
	private int menuId;
	private String menuText;
	private String menuFuncaoJS;
	private int menuColuna;
	private boolean checked;

	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getMenuText() {
		return menuText;
	}
	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}
	public String getMenuFuncaoJS() {
		return menuFuncaoJS;
	}
	public void setMenuFuncaoJS(String menuFuncaoJS) {
		this.menuFuncaoJS = menuFuncaoJS;
	}
	public int getMenuColuna() {
		return menuColuna;
	}
	public void setMenuColuna(int menuColuna) {
		this.menuColuna = menuColuna;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public boolean isPossuiItem() {
		return possuiItem;
	}
	public void setPossuiItem(boolean possuiItem) {
		this.possuiItem = possuiItem;
	}
	public Collection<SubMenuVO> getItemdata() {
		return itemdata;
	}
	public void setItemdata(Collection<SubMenuVO> itemdata) {
		this.itemdata = itemdata;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}
