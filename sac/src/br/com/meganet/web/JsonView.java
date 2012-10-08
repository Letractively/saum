package br.com.meganet.web;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import br.com.meganet.facade.AdministracaoFacade;
import br.com.meganet.facade.UsuarioPortalFacade;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.Criptografia;

public class JsonView implements Controller {

	private UsuarioPortalFacade usuarioPortalFacade;
	private AdministracaoFacade administracaoFacade;
	
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setContentType("application/json");  
		res.setHeader("Pragma", "public");  
		res.setHeader("Cache-control", "must-revalidate");  
		res.setDateHeader("Expires", 0);
		
		Integer trans = new Integer(req.getParameter("idTrans"));
		String respHtml = "";
		
		switch (trans.intValue()) {
		case 1:
			respHtml = trataLogin(req);
			break;

		default:
			respHtml = "{\"idSessao\":1000,\"usuarioNome\":\"Eu mesmo\",\"usuarioDtAtivacao\":\"Apr 28, 2011 2:04:17 PM\",\"usuarioEnviaEmailMonetario\":false,\"permanecer\":false,\"vaiInserir\":false,\"pagamentoAtrasado\":false,\"demandases\":[],\"boletosGeradoses\":[],\"boleto\":{\"boletosgeradosPago\":false,\"boletosgeradosDinheiroEntregueAdministradorConta\":false,\"boletosgeradosPagouEmMao\":false,\"vencido\":false}}";
			break;
		}
		
		
		ByteArrayInputStream in = null;
		try{
			byte[] conteudo = respHtml.getBytes("ISO-8859-1");
			
			res.setContentLength(conteudo.length);

			in = new ByteArrayInputStream(conteudo);
			byte[] buf = new byte[10 * 1024];
			int read;
	        while ((read = in.read(buf)) != -1) {
				res.getOutputStream().write(buf, 0, read);
	        }
			res.getOutputStream().flush();
			return null;
		}catch (Exception e) {
			return null;
		}
	}

	private String trataLogin(HttpServletRequest req) {
		Usuario usuario = new Usuario();
		usuario.setUsuarioEmail(req.getParameter("usr"));
		String senhaTMP = Criptografia.decrypt(req.getParameter("sen"), Constantes.CHAVE_CRIPTOGRAFIA);
		usuario.setUsuarioSenha(Criptografia.encrypt(senhaTMP, ConfigUtil.getInstance().getProperty("chave_criptografia",Constantes.CHAVE_CRIPTOGRAFIA)));
		usuario = usuarioPortalFacade.login(usuario);
		String respHtml = "";
		if(usuario != null){
			respHtml = "{idSessao:"+ usuario.getUsuarioId() +", adm:"+ usuario.getUsuarioAdministrativo() +", mon:"+ usuario.getUsuarioEnviaEmailMonetario() +"}";
		}else{
			respHtml = "{idSessao:0}";
		}
		return respHtml;
	}

	public void setUsuarioPortalFacade(UsuarioPortalFacade usuarioPortalFacade) {
		this.usuarioPortalFacade = usuarioPortalFacade;
	}

	public void setAdministracaoFacade(AdministracaoFacade administracaoFacade) {
		this.administracaoFacade = administracaoFacade;
	}
	
}
