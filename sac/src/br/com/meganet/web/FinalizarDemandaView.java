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
import br.com.meganet.hbm.vo.Demandas;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ValidaMenu;

public class FinalizarDemandaView extends Controlador {

	private final int idMenu = 30;
	ValidaMenu validaMenu = new ValidaMenu();
	private AdministracaoFacade administracaoFacade;

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(","))) {

				List<Demandas> demandas = administracaoFacade.adiquirirDemandas();

				for (Iterator<Demandas> iterator = demandas.iterator(); iterator.hasNext();) {
					Demandas demandas2 = (Demandas) iterator.next();
					demandas2.setResponsavel(demandas2.getUsuarioResponsavel() != null ? demandas2.getUsuarioResponsavel().getUsuarioNome() : "");
					demandas2.setCliente(demandas2.getUsuarioSolicitante().getUsuarioNome());
					demandas2.setTecnicoAbriu(demandas2.getUsuarioAbriu().getUsuarioNome());
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Timestamp time = demandas2.getDemandasDataAbertura();
					demandas2.setDataAbertura(sdf.format(new Date(time.getTime())));
					demandas2.setFechar("Aberta");
				}

				session.setAttribute("demandas", demandas);
				return new ModelAndView("demandas_FinalizarDemanda");
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

				List<Demandas> demandas = administracaoFacade.adiquirirDemandas();

				for (Iterator<Demandas> iterator = demandas.iterator(); iterator.hasNext();) {
					Demandas demandas2 = (Demandas) iterator.next();
					demandas2.setCliente(demandas2.getUsuarioSolicitante().getUsuarioNome());
					demandas2.setResponsavel(demandas2.getUsuarioResponsavel() != null ? demandas2.getUsuarioResponsavel().getUsuarioNome() : "");
					demandas2.setTecnicoAbriu(demandas2.getUsuarioAbriu().getUsuarioNome());
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Timestamp time = demandas2.getDemandasDataAbertura();
					demandas2.setDataAbertura(sdf.format(new Date(time.getTime())));
					demandas2.setFechar("Aberta");
				}
				session.setAttribute("demandas", demandas);
				return new ModelAndView("iphone/demandas_FinalizarDemanda");
			} else {
				return new ModelAndView("index2");
			}
		} else {
			return new ModelAndView("index2");
		}
	}
}
