package br.com.meganet.displayTag;

import org.displaytag.decorator.TableDecorator;

import br.com.meganet.hbm.vo.StatusCliente;
import br.com.meganet.hbm.vo.Usuario;

public class WrapperStatusClienteVO extends TableDecorator{
	private Usuario usuario;

	public Usuario getUsuario() {
		try{
			StatusCliente statusCliente = (StatusCliente) getCurrentRowObject();
			
			usuario = new Usuario();
			usuario.setUsuarioNome(statusCliente.getUsuario().getUsuarioNome());
			
			String str = "";
			
			str += "<span style=\"cursor:pointer\" ";
			str += "onClick=\"preencheCampoBuscaRelatorioCliente('"+usuario.getUsuarioNome()+"');\">";
			str += usuario.getUsuarioNome();
			str += "</span>";
			
			usuario.setUsuarioNome(str);
			return usuario;
			
		}catch (Exception e) {
			StatusCliente statusCliente = (StatusCliente) getCurrentRowObject();
			return statusCliente.getUsuario();
		}
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
}
