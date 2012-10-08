package br.com.meganet.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ValidaMenu;

public class RelatorioBoletoPagoEmMaoView extends Controlador {

	private final int idMenu = 36;
	ValidaMenu validaMenu = new ValidaMenu();

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(",")) && usuario.getUsuarioId() == 1) {
				return new ModelAndView("relatorios_BoletoPagoEmMao");
			} else {
				return new ModelAndView("index2");
			}
		} else {
			return new ModelAndView("index2");
		}
	}

	public ModelAndView executaBrowserIphone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(",")) && usuario.getUsuarioId() == 1) {
				return new ModelAndView("iphone/relatorios_BoletoPagoEmMao");
			} else {
				return new ModelAndView("iphone/index");
			}
		} else {
			return new ModelAndView("iphone/index");
		}
	}
}
