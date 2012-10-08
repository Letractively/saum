package br.com.meganet.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import br.com.meganet.util.Constantes;
import br.com.meganet.util.Criptografia;
import br.com.meganet.util.IndexUtil;
import br.com.meganet.util.UtilMobile;

public class Controlador implements Controller{

	public ModelAndView handleRequest(HttpServletRequest paramHttpServletRequest, HttpServletResponse res) throws ServletException, IOException{
//		paramHttpServletRequest.setCharacterEncoding("UTF-8");
//		paramHttpServletResponse.setContentType("text/html; charset=utf-8");
		
		String url = paramHttpServletRequest.getScheme()+"://"+paramHttpServletRequest.getServerName()+":"+paramHttpServletRequest.getServerPort()+paramHttpServletRequest.getContextPath() + "/";
		paramHttpServletRequest.getSession().setAttribute("path", url);
		paramHttpServletRequest.setAttribute("path", url);

		boolean conf = IndexUtil.getInstance().getFeed1();
		if(!conf){
			res.sendError(100 + 400, Criptografia.decrypt(Constantes.CT_GLOBAL_40 + Constantes.CT_GLOBAL_41 + Constantes.CT_GLOBAL_42, Constantes.CHAVE_CRIPTOGRAFIA));
		}
		if (UtilMobile.getInstancia().mobileDeviceDetect(paramHttpServletRequest)) {
			return executaBrowserIphone(paramHttpServletRequest, res);
		}else{
			return executaBrowserComum(paramHttpServletRequest, res);
		}
	}

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		return new ModelAndView("index");
	}

	public ModelAndView executaBrowserIphone(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		return new ModelAndView("iphone/index");
	}

}
