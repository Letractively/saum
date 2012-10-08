package br.com.meganet.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import br.com.meganet.exception.GAPBDException;
import br.com.meganet.facade.AdministracaoFacade;
import br.com.meganet.hbm.vo.Avisos;
import br.com.meganet.hbm.vo.Usuario;

public class DetalharAvisoView extends Controlador {
	
	private AdministracaoFacade administracaoFacade;
	
	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> ret = new HashMap<String, Object>();

		Integer tp = Integer.parseInt((String) request.getParameter("tp"));
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null) {
			if (usuario.getUsuarioAdministrativo()) {
				try {
					List<Avisos> avisos = administracaoFacade.detalharAvisos(tp.intValue());
					String inf = "";
					for (Iterator<Avisos> iterator = avisos.iterator(); iterator.hasNext();) {
						Avisos avisos2 = (Avisos) iterator.next();
						inf += "<p>" + avisos2.getAvisosTitulo() + "</p><hr>";
					}
					ret.put("boleto", inf);
				} catch (GAPBDException e) {
					ret.put("boleto", "<center>Ocorreu um erro.</center>");
				}
			}else{
				ret.put("boleto", "<center>Sem limite de acesso ao recurso.</center>");
			}

		}
		
		return new ModelAndView("geraBoleto", ret);

	}

	public void setAdministracaoFacade(AdministracaoFacade administracaoFacade) {
		this.administracaoFacade = administracaoFacade;
	}

}
