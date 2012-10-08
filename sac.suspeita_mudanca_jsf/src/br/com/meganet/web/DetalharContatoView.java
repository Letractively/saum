package br.com.meganet.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import br.com.meganet.facade.AdministracaoFacade;
import br.com.meganet.hbm.vo.Contato;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.HtmlTableGenerator;

public class DetalharContatoView extends Controlador {
	
	private AdministracaoFacade administracaoFacade;
	
	public ModelAndView executaBrowserComum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> ret = new HashMap<String, Object>();

		String tp = (String) request.getParameter("tp");
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario != null) {
			if (usuario.getUsuarioAdministrativo()) {
				try {
					Contato contato = administracaoFacade.detalharContato(new Long(tp));
					String inf = "";
					
					HtmlTableGenerator htg = new HtmlTableGenerator();
					htg.setTituloTabelaSemEMpresa("Contato do site.");
					htg.getDados().put("Nome", contato.getContatoNome());
					htg.getDados().put("Telefone", contato.getContatoTelefone());
					htg.getDados().put("Endereço", contato.getContatoEndCr());
					htg.getDados().put("E-mail", contato.getContatoEmail());
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt","br"));
					SimpleDateFormat sdfH = new SimpleDateFormat("HH:MM:ss", new Locale("pt","br"));
					
					if(contato.getContatoDataEnvio() != null){
						htg.getDados().put("Data de envio", sdf.format(new Date(contato.getContatoDataEnvio().getTime())) + " às " + sdfH.format(new Date(contato.getContatoDataEnvio().getTime())));
					}
					
					if(contato.getContatoDataResposta() != null){
						htg.getDados().put("Data de resposta", sdf.format(new Date(contato.getContatoDataResposta().getTime())) + " às " + sdfH.format(new Date(contato.getContatoDataResposta().getTime())));
					}

					if(contato.getContatoMensagem() != null){
						htg.getDados().put("Mensagem", contato.getContatoMensagem());
					}
					
					if(contato.getContatoResposta() != null){
						htg.getDados().put("Resposta", contato.getContatoResposta());
					}

					htg.setRodapeTabela("&nbsp;");
					inf = htg.montaTabelaSemRodape();
					ret.put("boleto", inf);
				} catch (Exception e) {
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
