package br.com.meganet.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import br.com.meganet.facade.AdministracaoFacade;
import br.com.meganet.facade.UsuarioPortalFacade;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.Criptografia;

public class IndexView extends Controlador {
	private UsuarioPortalFacade usuarioPortalFacade;
	private AdministracaoFacade administracaoFacade;

	public void setUsuarioPortalFacade(UsuarioPortalFacade usuarioPortalFacade) {
		this.usuarioPortalFacade = usuarioPortalFacade;
	}

	public void setAdministracaoFacade(AdministracaoFacade administracaoFacade) {
		this.administracaoFacade = administracaoFacade;
	}

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("facebook", false);
		request.getSession().setAttribute("facebook", false);
		
		boolean temAdministrador = administracaoFacade.temAdministrador();
		boolean temConfig = true;
		String empresa = ConfigUtil.getInstance().getProperty("empresa", "");
		if (empresa == null || "".equalsIgnoreCase(empresa)) {
			temConfig = false;
		}
		if (temAdministrador) {
			return new ModelAndView("index");
		} else {
			if (temConfig) {
				request.getSession().setAttribute("PRIMEIRO_ACESSO", true);
				return new ModelAndView("administrativo_InsercaoUsuario");
			} else {
				request.getSession().setAttribute("PRIMEIRO_ACESSO", true);
				return new ModelAndView("administrativo_configuracaoAvancada");
			}
		}
	}

	public ModelAndView executaBrowserIphone(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String param = req.getParameter("param");
		if (param != null && !"".equalsIgnoreCase(param)) {
			param = Criptografia.decrypt(param, Constantes.CHAVE_CRIPTOGRAFIA);
			String[] nomes = param.split("&2&");
			if (nomes != null && nomes.length > 1) {
				Usuario u = new Usuario();
				u.setUsuarioEmail(nomes[0]);
				u.setUsuarioSenha(Criptografia.encrypt(nomes[1], ConfigUtil.getInstance().getProperty("chave_criptografia", Constantes.CHAVE_CRIPTOGRAFIA)));
				String nome = usuarioPortalFacade.consultaUsuarioParaLogar(u, req, res);
				if (nome != null && !"".equalsIgnoreCase(nome)) {
					return new ModelAndView("iphone/index");
				}
			}
		}
		return new ModelAndView("iphone/index");
	}

}