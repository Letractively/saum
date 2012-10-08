package br.com.meganet.dwr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import br.com.meganet.auditoria.BarraProgresso;
import br.com.meganet.facade.AvisosFacade;
import br.com.meganet.facade.UsuarioPortalFacade;
import br.com.meganet.hbm.vo.Avisos;
import br.com.meganet.hbm.vo.MenuVO;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.Criptografia;

public class UsuariosPortalJS {
	
	private Log logger = LogFactory.getLog(this.getClass());
	BarraProgresso bp = new BarraProgresso();
	WebContext context = null;
	private UsuarioPortalFacade usuarioPortalFacade;
	private AvisosFacade avisosFacade;

	public void setAvisosFacade(AvisosFacade avisosFacade) {
		this.avisosFacade = avisosFacade;
	}
	public void setUsuarioPortalFacade(UsuarioPortalFacade usuarioPortalFacade) {
		this.usuarioPortalFacade = usuarioPortalFacade;
	}

	public String login(Usuario usuario) {
		String senhaCriptografada = Criptografia.encrypt(usuario.getUsuarioSenha(), ConfigUtil.getInstance().getProperty("chave_criptografia",Constantes.CHAVE_CRIPTOGRAFIA));
		usuario.setUsuarioSenha(senhaCriptografada);

		context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		HttpServletResponse res = context.getHttpServletResponse();

		req.getSession().setAttribute("acoes", new HashMap<String, String>());
		return usuarioPortalFacade.consultaUsuarioParaLogar(usuario, req, res);
	}

	public String loginCookie() {
		context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		HttpServletResponse res = context.getHttpServletResponse();
		return usuarioPortalFacade.loginCookie(req, res);
	}
	
	public String carregaNome(){
		context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		if(usuario != null){
			return usuario.getUsuarioNome();
		}else{
			return "";
		}
	}
	public boolean logoff() {
		context = WebContextFactory.get();
		HttpServletResponse res = context.getHttpServletResponse();
		HttpServletRequest req = context.getHttpServletRequest();
		if(req.getSession().getAttribute("usuario") != null){
			logger.info("Finalizando sessão: " + ((Usuario) req.getSession().getAttribute("usuario")).getUsuarioEmail());
			req.getSession().removeAttribute("usuario");
		}
		req.getSession().invalidate();

		Cookie ckUsr = new Cookie(usuarioPortalFacade.getNomeEMAIL(), "");
		Cookie ckSen = new Cookie(usuarioPortalFacade.getNomeSENHA(), "");
		ckUsr.setMaxAge(0);
		ckSen.setMaxAge(0);
		res.addCookie(ckUsr);
		res.addCookie(ckSen);
		return true;
	}

	public List<MenuVO> carregaMenu(int opcao) {
		context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		List<MenuVO> menu = new ArrayList<MenuVO>();
		if(usuario != null){
			Usuario usr = usuarioPortalFacade.login(usuario);
			req.getSession().setAttribute("usuario", usr);
			menu = usuarioPortalFacade.montaMenu(opcao, usr.getUsuarioMenu());
		}else{
			menu = usuarioPortalFacade.montaMenu(opcao, null);
		}
		return menu;
	}
	
	public List<Avisos> carregaAvisos(){
		context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		Boolean fb = (Boolean)req.getSession().getAttribute("facebook");
		return avisosFacade.carregaAvisos(usuario, fb);
	}
	
	public List<Avisos> carregaAvisosWAP(){
		context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		return avisosFacade.carregaAvisosWAP(usuario);
	}
	
	public int esqueci(Usuario usuario){
		try{
			return usuarioPortalFacade.esqueci(usuario);
		}catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}
	
	public int trocaSenha(String senha, String param){
		try{
			Usuario usr = usuarioPortalFacade.trocaSenha(senha, param);
			if(usr != null){
				logger.info("Trocando senha: " + usr.getUsuarioEmail());
				context = WebContextFactory.get();
				HttpServletRequest req = context.getHttpServletRequest();
				req.getSession().setAttribute("usuario", usr);
				return 0;
			}else{
				return 1;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}
	public void setHistoricoMobile(int tipo, String de, String para) {
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		req.getSession().setAttribute("tipo", tipo);
		req.getSession().setAttribute("de", de);
		req.getSession().setAttribute("para", para);
	}

	public Map<String, Object> getHistoricoMobile() {
		WebContext context = WebContextFactory.get();
		HttpServletRequest req = context.getHttpServletRequest();
		Map<String, Object> ret = new HashMap<String, Object>();
 		
		if(req.getSession() != null && req.getSession().getAttribute("tipo") != null){
			ret.put("tipo", (Integer) req.getSession().getAttribute("tipo"));
			ret.put("de", (String) req.getSession().getAttribute("de"));
			ret.put("para", (String) req.getSession().getAttribute("para"));
			req.getSession().removeAttribute("tipo");
			req.getSession().removeAttribute("de");
			req.getSession().removeAttribute("para");
			return ret;
		}else{
			return null;
		}
	}
	}
