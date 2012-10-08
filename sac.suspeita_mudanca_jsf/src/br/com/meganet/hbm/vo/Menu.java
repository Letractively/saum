package br.com.meganet.hbm.vo;

import java.io.Serializable;


public class Menu implements Serializable{

	private static final long serialVersionUID = -7361692864928944691L;
	private Long menuId;
	private String menuText;
	private String menuFuncaoJs;
	private Integer menuColuna;
	private Boolean menuChecked;
	private Boolean menuMostraIphone;
	private Boolean menuPublico;
	private Long menuAtivado;

	// Property accessors

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuText() {
		return this.menuText;
	}

	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}

	public String getMenuFuncaoJs() {
		return this.menuFuncaoJs;
	}

	public void setMenuFuncaoJs(String menuFuncaoJs) {
		this.menuFuncaoJs = menuFuncaoJs;
	}

	public Integer getMenuColuna() {
		return this.menuColuna;
	}

	public void setMenuColuna(Integer menuColuna) {
		this.menuColuna = menuColuna;
	}

	public Boolean getMenuChecked() {
		return this.menuChecked;
	}

	public void setMenuChecked(Boolean menuChecked) {
		this.menuChecked = menuChecked;
	}

	public Long getMenuAtivado() {
		return this.menuAtivado;
	}

	public void setMenuAtivado(Long menuAtivado) {
		this.menuAtivado = menuAtivado;
	}

	public Boolean getMenuMostraIphone() {
		return menuMostraIphone;
	}

	public void setMenuMostraIphone(Boolean menuMostraIphone) {
		this.menuMostraIphone = menuMostraIphone;
	}

	public Boolean getMenuPublico() {
		return menuPublico;
	}

	public void setMenuPublico(Boolean menuPublico) {
		this.menuPublico = menuPublico;
	}

}