package br.com.meganet.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import br.com.meganet.facade.BoletoFacade;
import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.DateUtils;
import br.com.meganet.util.ValidaMenu;

public class ExtratoMensalidadeView extends Controlador {

	private final int idMenu = 14;
	ValidaMenu validaMenu = new ValidaMenu();
	private BoletoFacade boletoFacade;

	public void setBoletoFacade(BoletoFacade boletoFacade) {
		this.boletoFacade = boletoFacade;
	}

	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(","))) {
				List<BoletosGerados> listaBoletos = boletoFacade.adiquiriRelatorioBoletoUsuario(usuario);
				List<BoletosGerados> ret = new ArrayList<BoletosGerados>();
				try {
					Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));

					for (Iterator<BoletosGerados> iterator = listaBoletos.iterator(); iterator.hasNext();) {
						BoletosGerados boletosGerados = (BoletosGerados) iterator.next();

						if (boletosGerados.getBoletosgeradosPago()) {
							boletosGerados.setEstaPago("Pago");
						} else {
							if (DateUtils.aPrimeiraEhMaiorQueSegunda(dataAtual.getTime(), boletosGerados.getBoletosgeradosDataVencimento())) {
								boletosGerados.setEstaPago("Vecido");
							} else {
								boletosGerados.setEstaPago("Aberto");
							}
						}

						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						if (boletosGerados.getBoletosgeradosDataVencimento() != null) {
							Timestamp time = boletosGerados.getBoletosgeradosDataVencimento();
							boletosGerados.setDataTempVencimento(sdf.format(new Date(time.getTime())));
						}

						if (boletosGerados.getBoletosgeradosDataPagamento() != null) {
							Timestamp time = boletosGerados.getBoletosgeradosDataPagamento();
							boletosGerados.setDataTempPagamento(sdf.format(new Date(time.getTime())));
						}
						
						if(ConfigUtil.getInstance().getProperty("boleto_f2b_tipo_registro", "n").equalsIgnoreCase("b")){
							if (DateUtils.aPrimeiraEhMaiorQueSegunda(dataAtual.getTime(), boletosGerados.getBoletosgeradosDataVencimento())) {
								ret.add(boletosGerados);
							}else{
								if(boletosGerados.getBoletosgeradosIdF2B() != null){
									ret.add(boletosGerados);
								}
							}
						}else{
							ret.add(boletosGerados);
						}
					}
				} catch (Exception e) {
					
				}
				session.setAttribute("extratoBoleto", ret);
				return new ModelAndView("pagamentos_ExtratoMensalidade");
			} else {
				return new ModelAndView("index2");
			}
		} else {
			return new ModelAndView("index2");
		}
	}

	public ModelAndView executaBrowserIphone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null) {
			if (validaMenu.validaMenu(idMenu, usuario.getUsuarioMenu().split(","))) {
				List<BoletosGerados> listaBoletos = boletoFacade.adiquiriRelatorioBoletoUsuario(usuario);
				try {
					Calendar dataAtual = DateUtils.obterCalendarSemHorario(new Date(System.currentTimeMillis()));

					for (Iterator<BoletosGerados> iterator = listaBoletos.iterator(); iterator.hasNext();) {
						BoletosGerados boletosGerados = (BoletosGerados) iterator.next();
						if (boletosGerados.getBoletosgeradosPago()) {
							boletosGerados.setEstaPago("Pago");
						} else {
							if (DateUtils.aPrimeiraEhMaiorQueSegunda(dataAtual.getTime(), boletosGerados.getBoletosgeradosDataVencimento())) {
								boletosGerados.setEstaPago("Vecido");
							} else {
								boletosGerados.setEstaPago("Aberto");
							}
						}

						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						if (boletosGerados.getBoletosgeradosDataVencimento() != null) {
							Timestamp time = boletosGerados.getBoletosgeradosDataVencimento();
							boletosGerados.setDataTempVencimento(sdf.format(new Date(time.getTime())));
						}

						if (boletosGerados.getBoletosgeradosDataPagamento() != null) {
							Timestamp time = boletosGerados.getBoletosgeradosDataPagamento();
							boletosGerados.setDataTempPagamento(sdf.format(new Date(time.getTime())));
						}
					}
				} catch (Exception e) {
					return new ModelAndView("iphone/index");
				}
				session.setAttribute("extratoBoleto", listaBoletos);
				return new ModelAndView("iphone/pagamentos_ExtratoMensalidade");
			} else {
				return new ModelAndView("index2");
			}
		} else {
			return new ModelAndView("index2");
		}
	}
}
