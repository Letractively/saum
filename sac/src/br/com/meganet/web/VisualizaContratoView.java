package br.com.meganet.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;


import br.com.meganet.facade.ContratoFacade;
import br.com.meganet.hbm.vo.Contrato;

public class VisualizaContratoView extends Controlador {
	
	private ContratoFacade contratoFacade;
	
	public void setContratoFacade(ContratoFacade contratoFacade) {
		this.contratoFacade = contratoFacade;
	}

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> ret = new HashMap<String, Object>();

		long id = Long.parseLong((String) request.getParameter("id"));
		Contrato contrato = contratoFacade.getContratoPeloCliente(id);
		if (contrato == null) {
			ret.put("boleto", "<center>Usuario ou contrato inexistente.</center>");
		} else {
			ret.put("boleto", contrato.getContratoContrato());
		}
		return new ModelAndView("geraBoleto", ret);

	}

}
