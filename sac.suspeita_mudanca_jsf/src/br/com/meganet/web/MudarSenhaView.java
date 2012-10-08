package br.com.meganet.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;


public class MudarSenhaView extends Controlador {

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() + "/";
		request.setAttribute("facebook", false);
		request.getSession().setAttribute("facebook", false);
		request.getSession().setAttribute("path", url);
		request.setAttribute("path", url + "/");

		String param = request.getParameter("id");
		try {
			if (param == null || "".equalsIgnoreCase(param)) {
				return new ModelAndView("index2");
			} else {
				Map<String, Object> ret = new HashMap<String, Object>();
				ret.put("id", param);
				return new ModelAndView("troca_senha", ret);
			}
		} catch (Exception e) {
			return new ModelAndView("index2");
		}
	}
	
	public ModelAndView executaBrowserIphone(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		String param = request.getParameter("id");
		try {
			if (param == null || "".equalsIgnoreCase(param)) {
				return new ModelAndView("index2");
			} else {
				Map<String, Object> ret = new HashMap<String, Object>();
				ret.put("id", param);
				return new ModelAndView("iphone/troca_senha", ret);
			}
		} catch (Exception e) {
			return new ModelAndView("index2");
		}
	}


}
