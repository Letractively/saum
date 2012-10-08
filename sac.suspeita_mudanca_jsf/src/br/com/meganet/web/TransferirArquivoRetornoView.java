package br.com.meganet.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;


import br.com.meganet.facade.AdministracaoFacade;
import br.com.meganet.hbm.vo.LogMeganet;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ValidaMenu;

public class TransferirArquivoRetornoView extends Controlador {
	
	private final int idMenu = 40;
	ValidaMenu validaMenu = new ValidaMenu();
	private AdministracaoFacade administracaoFacade;

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(","))) {
				List<LogMeganet> ul = administracaoFacade.buscaUltimosLog();
				request.getSession().setAttribute("ultimosLog", ul);
				return new ModelAndView("boleto_arquivoRetorno");
			} else {
				return new ModelAndView("index2");
			}
		} else {
			return new ModelAndView("index2");
		}
	}

	public void setAdministracaoFacade(AdministracaoFacade administracaoFacade) {
		this.administracaoFacade = administracaoFacade;
	}


}
