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
import br.com.meganet.hbm.vo.Usuario;


public class ReimpressaoView extends Controlador {
	
	private Log logger = LogFactory.getLog(this.getClass());
	private BoletoFacade boletoFacade;
	public void setBoletoFacade(BoletoFacade boletoFacade) {
		this.boletoFacade = boletoFacade;
	}

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mac = (String) request.getParameter("id");
		Usuario usr = new Usuario();
		usr.setUsuarioMac(mac);
		
		JBoletoBean boletoBean = boletoFacade.getBoletoByMAC(usr);
		logger.info("Adiquirindo boleto pelo mac: " + mac);
		Map<String, String> ret = new HashMap<String, String>();
		if(boletoBean == null){
			ret.put("boleto", null);
		}else{
			ret.put("boleto", boletoBean.getUrlF2B());
		}
		return new ModelAndView("reimpressao", ret);
	}
	
}
