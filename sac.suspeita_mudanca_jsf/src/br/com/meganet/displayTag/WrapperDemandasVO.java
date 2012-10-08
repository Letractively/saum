package br.com.meganet.displayTag;

import org.displaytag.decorator.TableDecorator;

import br.com.meganet.hbm.vo.Demandas;
import br.com.meganet.hbm.vo.Usuario;

public class WrapperDemandasVO extends TableDecorator{
	
	private String fechar;
	private Usuario usuarioSolicitante;

	public String getFechar() {
		try{
			Demandas demandas = (Demandas) getCurrentRowObject();
			fechar = "<img style=\"cursor:pointer\" src=\"img/check.png\" border=0 onclick=\"finalizaDemanda("+ demandas.getDemandasId() +")\"/>";
			return fechar;
		}catch (Exception e) {
			return "";
		}
	}

	public void setFechar(String fechar) {
		this.fechar = fechar;
	}
	
	public void setUsuarioSolicitante(Usuario usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public Usuario getUsuarioSolicitante() {
		try{
			Demandas demandas = (Demandas) getCurrentRowObject();
			usuarioSolicitante = new Usuario();
			
			String str = "<div style=\"cursor: pointer;\" onclick=\"detalha('"+ demandas.getDemandasId() +"')\">"+ demandas.getUsuarioSolicitante().getUsuarioNome() +"</div>";
			usuarioSolicitante.setUsuarioNome(str);
			return usuarioSolicitante;
		}catch (Exception e) {
			return new Usuario();
		}
	}
	
}
