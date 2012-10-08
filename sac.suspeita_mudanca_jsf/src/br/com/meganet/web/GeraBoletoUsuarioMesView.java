package br.com.meganet.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboleto.JBoletoBean;
import org.springframework.web.servlet.ModelAndView;

import br.com.meganet.facade.BoletoFacade;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ValidaMenu;
import br.com.meganet.util.geradorBoleto.GeraHTMLBoletoFacade;

public class GeraBoletoUsuarioMesView extends Controlador {

	private final int idMenu = 31;
	ValidaMenu validaMenu = new ValidaMenu();

	private BoletoFacade boletoFacade;
	private GeraHTMLBoletoFacade geraHTMLBoletoFacade;

	public void setGeraHTMLBoletoFacade(GeraHTMLBoletoFacade geraHTMLBoletoFacade) {
		this.geraHTMLBoletoFacade = geraHTMLBoletoFacade;
	}
	public void setBoletoFacade(BoletoFacade boletoFacade) {
		this.boletoFacade = boletoFacade;
	}
	
	
	/**
	 * vem solicitação da pagina de boleto administrativa.
	 * Impressão de boleto -> boleto por usuario. pode vir CPF ou nome e mes de pesquisa
	 */
	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");//
		Map<String, Object> ret = new HashMap<String, Object>();

		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(","))) {
				String cliente = request.getParameter("cliente");
				String tp = request.getParameter("tp");
				int mes = request.getParameter("mes") == null ? 0 : Integer.parseInt((String) request.getParameter("mes"));
				int ano = request.getParameter("ano") == null ? 0 : Integer.parseInt((String) request.getParameter("ano"));
				String dtAdia = (String) request.getParameter("dtAdia");
				String idBol = (String) request.getParameter("idBol");
				Usuario usrLogado = (Usuario) request.getSession().getAttribute("usuario");
				List<JBoletoBean> boletoBean = boletoFacade.getBoletoByUsuarioMes(cliente, tp, mes, ano, dtAdia, usrLogado, idBol);
				if (boletoBean == null || boletoBean.size() == 0) {
					ret.put("boleto", "<center>Nenhum boleto encontrado.</center>");
				} else {
					String codigoBoleto = geraHTMLBoletoFacade.processaBoleto(boletoBean);
					ret.put("boleto", codigoBoleto);
				}
				return new ModelAndView("geraBoleto", ret);
			} else {
				ret.put("boleto", "Não permitido.");
				return new ModelAndView("geraBoleto", ret);
			}
		} else {
			ret.put("boleto", "Não permitido.");
			return new ModelAndView("geraBoleto", ret);
		}
	}

}
