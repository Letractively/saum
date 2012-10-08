package br.com.meganet.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;

import br.com.meganet.facade.UsuarioPortalFacade;
import br.com.meganet.facebookAPI.infra.jee.UserLogged;
import br.com.meganet.hbm.vo.Usuario;

public class IndexFBView extends Controlador {
	private Log logger = LogFactory.getLog(this.getClass());
	private UsuarioPortalFacade usuarioPortalFacade;

	public void setUsuarioPortalFacade(UsuarioPortalFacade usuarioPortalFacade) {
		this.usuarioPortalFacade = usuarioPortalFacade;
	}

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("facebook", true);
		request.getSession().setAttribute("facebook", true);
		
		UserLogged userLogged = (UserLogged) request.getSession().getAttribute("userLogged");
		Usuario usr = new Usuario();
		usr.setUsuarioEmail(userLogged.getProfile().getEmail());
		consultaUsuarioParaLogar(usr, request, response);
		return new ModelAndView("index");
	}


	public String consultaUsuarioParaLogar(Usuario usuario, HttpServletRequest req, HttpServletResponse res) {
		try {
			logger.info("Tentando realizar login: " + usuario.getUsuarioEmail());
			Usuario usr = usuarioPortalFacade.loginFB(usuario);
			req.getSession().setAttribute("usuario", usr);
			return usr.getUsuarioNome();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}