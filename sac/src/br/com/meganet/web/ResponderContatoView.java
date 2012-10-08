package br.com.meganet.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import br.com.meganet.facade.AdministracaoFacade;
import br.com.meganet.hbm.vo.Contato;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ValidaMenu;

public class ResponderContatoView extends Controlador {

	private final int idMenu = 49;
	ValidaMenu validaMenu = new ValidaMenu();
	private AdministracaoFacade administracaoFacade;

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(","))) {
				setListaContatosAbertos(session);
				return new ModelAndView("administrativo_ResponderContato");
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

	public ModelAndView executaBrowserIphone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(","))) {
				setListaContatosAbertos(session);
				return new ModelAndView("iphone/administrativo_ResponderContato");
			} else {
				return new ModelAndView("index2");
			}
		} else {
			return new ModelAndView("index2");
		}
	}
	
	private void setListaContatosAbertos(HttpSession session){
		List<Contato> contatos = administracaoFacade.adiquirirContatosAbertos();

		for (Iterator<Contato> iterator = contatos.iterator(); iterator.hasNext();) {
			Contato contato = (Contato) iterator.next();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:MM:SS");
			Timestamp time = contato.getContatoDataEnvio();
			contato.setDataEnvio(sdf.format(new Date(time.getTime())) + " às " + sdf2.format(new Date(time.getTime())));
			contato.setFechar("Aberta");
		}
		session.setAttribute("contato", contatos);
	}
}
