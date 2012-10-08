package br.com.meganet.displayTag;

import org.displaytag.decorator.TableDecorator;

import br.com.meganet.hbm.vo.Contato;

public class WrapperContatoVO extends TableDecorator{
	
	private String fechar;

	public String getFechar() {
		try{
			Contato contato = (Contato) getCurrentRowObject();
			fechar = "<img style=\"cursor:pointer\" src=\"img/check.png\" border=0 onclick=\"buscaMensagem("+ contato.getContatoId() +")\"/>";
			return fechar;
		}catch (Exception e) {
			return "";
		}
	}

	public void setFechar(String fechar) {
		this.fechar = fechar;
	}

}
