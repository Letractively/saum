package br.com.meganet.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.meganet.util.geradorBoleto.GeraHTMLBoletoFacade;



public class GeraCodigoBarraBoleto extends HttpServlet {


	private static final long serialVersionUID = 5959902243523167342L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		java.io.OutputStream out = response.getOutputStream();
		String s = request.getParameter("cb");
		GeraHTMLBoletoFacade.gerarCodBarras(out, s);
	}
	
}