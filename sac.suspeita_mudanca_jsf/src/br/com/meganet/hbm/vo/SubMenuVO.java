package br.com.meganet.hbm.vo;

import java.io.Serializable;


public class SubMenuVO implements Serializable{
	
	private static final long serialVersionUID = 1172040050861256260L;
	private String text = "";
	private String url = "";
	private Long menuAtivado;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return "javascript:" + url + "()";
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getMenuAtivado() {
		return menuAtivado;
	}
	public void setMenuAtivado(Long menuAtivado) {
		this.menuAtivado = menuAtivado;
	}

}
