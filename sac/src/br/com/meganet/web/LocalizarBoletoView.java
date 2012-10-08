package br.com.meganet.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;


import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.Util;
import br.com.meganet.util.ValidaMenu;

public class LocalizarBoletoView extends Controlador {
	
	private final int idMenu = 39;
	ValidaMenu validaMenu = new ValidaMenu();
	
	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		String id = (String) request.getParameter("id");
		
		if(id != null){
			Util.acoesGrava("LocalizarBoletoView", id, request);
		}

		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(","))) {
				if(usuario.getUsuarioAdministrativo()){
					return new ModelAndView("boleto_LocalizarBoleto");
				}else{
					return new ModelAndView("boleto_LocalizarBoletoUsuarioLimitado");
				}
			} else {
				return new ModelAndView("index2");
			}
		} else {
			return new ModelAndView("index2");
		}
	}
	public ModelAndView executaBrowserIphone(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		String id = (String) request.getParameter("id");
		
		if(id != null){
			Util.acoesGrava("LocalizarBoletoView", id, request);
		}

		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(","))) {
				if(usuario.getUsuarioAdministrativo()){
					return new ModelAndView("iphone/boleto_LocalizarBoleto");
				}else{
					return new ModelAndView("iphone/boleto_LocalizarBoletoUsuarioLimitado");
				}
			} else {
				return new ModelAndView("index2");
			}
		} else {
			return new ModelAndView("index2");
		}
	}
}
