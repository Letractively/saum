package br.com.meganet.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import br.com.meganet.util.DateUtils;
import br.com.meganet.util.ValidaMenu;
import br.com.meganet.util.geradorBoleto.GeraHTMLBoletoFacade;

public class GeraBoletoDataPagamentoView extends Controlador {

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

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");//
		Map<String, Object> ret = new HashMap<String, Object>();

		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(","))) {
				try{
					String ini = (String) request.getParameter("ini");
					String fim = (String) request.getParameter("fim");
					Usuario usrLogado = (Usuario) request.getSession().getAttribute("usuario");
					SimpleDateFormat sdff = new SimpleDateFormat("dd/MM/yyyy");
					
					Calendar dtIni = new GregorianCalendar();
					Calendar dtFim = new GregorianCalendar();
					dtIni.setTime(sdff.parse(ini));
					dtFim.setTime(sdff.parse(fim));
					
					if(DateUtils.aPrimeiraEhMaiorQueSegunda(dtIni.getTime(), dtFim.getTime())){
						ret.put("boleto", "<center>Data inicial maior que a data final.</center>");
						return new ModelAndView("geraBoleto", ret);
					}
					
					List<JBoletoBean> boletosBean = boletoFacade.getBoletoByDataPagamento(dtIni, dtFim, null, usrLogado);
	
					if (boletosBean == null || boletosBean.size() == 0) {
						ret.put("boleto", "<center>Nenhum boleto encontrado.</center>");
					} else {
						String codigoBoleto = geraHTMLBoletoFacade.processaBoleto(boletosBean);
						ret.put("boleto", codigoBoleto);
					}
					return new ModelAndView("geraBoleto", ret);
				}catch (Exception e) {
					ret.put("boleto", "<center>Exceção geral no adiamento do boleto.</center>");
					return new ModelAndView("geraBoleto", ret);
				}
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
