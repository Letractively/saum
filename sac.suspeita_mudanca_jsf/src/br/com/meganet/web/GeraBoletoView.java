package br.com.meganet.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboleto.JBoletoBean;
import org.springframework.web.servlet.ModelAndView;

import br.com.meganet.facade.BoletoFacade;
import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.util.geradorBoleto.GeraHTMLBoletoFacade;

public class GeraBoletoView extends Controlador {
	
	private Log logger = LogFactory.getLog(this.getClass());
	private BoletoFacade boletoFacade;
	private GeraHTMLBoletoFacade geraHTMLBoletoFacade;

	public void setGeraHTMLBoletoFacade(GeraHTMLBoletoFacade geraHTMLBoletoFacade) {
		this.geraHTMLBoletoFacade = geraHTMLBoletoFacade;
	}
	public void setBoletoFacade(BoletoFacade boletoFacade) {
		this.boletoFacade = boletoFacade;
	}

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = new Long(request.getParameter("id"));//
		BoletosGerados boletosGerados = new BoletosGerados();
		boletosGerados.setBoletosgeradosId(id);
		
		JBoletoBean boletoBean = boletoFacade.getBoletoById(boletosGerados);
		logger.info("Adiquirindo boleto numero: " + id);
		Map<String, Object> ret = new HashMap<String, Object>();

		if(boletoBean == null){
			ret.put("boleto", "<center>Usuário ou boleto inexistente.</center>");
		}else{
			String codigoBoleto = geraHTMLBoletoFacade.processaBoleto(boletoBean);
			ret.put("boleto", codigoBoleto);
		}
		return new ModelAndView("geraBoleto", ret);
	}

}
