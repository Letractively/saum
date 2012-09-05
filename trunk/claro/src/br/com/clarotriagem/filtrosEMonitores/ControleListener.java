package br.com.clarotriagem.filtrosEMonitores;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.controller.BaseBean;
import br.com.clarotriagem.controller.LoginBean;
import br.com.clarotriagem.entitades.Menu;
import br.com.clarotriagem.entitades.MenuPerfil;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.LoginService;
import br.com.clarotriagem.utils.BeanMensagem;
import br.com.clarotriagem.utils.Criptografia;
import br.com.clarotriagem.utils.Erros;
import br.com.clarotriagem.utils.singleton.mapas.ConfigUtil;
import br.com.clarotriagem.utils.singleton.mapas.MenuCache;

@ManagedBean
@Scope("session")
@Component
public class ControleListener implements PhaseListener {
	
	private static final long serialVersionUID = 1L;
	private final String CHAVE_CRIPT = "sP81h6rB1wr2qoieqwrl5zW3";
	
	public ControleListener() {
	}

	@Autowired
	private LoginService loginSevice;
	
	@Override
	public void afterPhase(PhaseEvent event) {
		List<Menu> menus = MenuCache.getInstance().getMenus();
		// Obtém o contexto atual
		FacesContext contexto = event.getFacesContext();
		// Obtém a página que atualmente está interagindo com o ciclo
		String paginaAtual = contexto.getViewRoot().getViewId();
		HttpServletRequest req = (HttpServletRequest) contexto.getExternalContext().getRequest();
		UsuarioIdentificacao usuario = (UsuarioIdentificacao) BaseBean.getSessao().getAttribute("usuario");
		Application app = contexto.getApplication();
		NavigationHandler nh = contexto.getApplication().getNavigationHandler();

		// Verifica se o usuário está logado e se não está na página de login
		boolean isMenuPublic = false;
		boolean usuarioPodeAcessar = false;
		boolean isLoginPage = paginaAtual.lastIndexOf("index.xhtml") > -1;
		boolean isInicialPage = paginaAtual.lastIndexOf("inicio.xhtml") > -1;
		boolean isMensagemPage = paginaAtual.indexOf("mensagens.xhtml") > -1;
		Boolean logout = (Boolean) BaseBean.getSessao().getAttribute("logout");
		
		if(logout != null && logout.booleanValue()){
			BaseBean.getSessao().setAttribute("logout", false);
			nh.handleNavigation(contexto, null, "index");
		}
		
		Cookie[] ck = req.getCookies();
		if (ck != null && usuario == null) {
			String senha = "";
			String email = "";
			for (int i = 0; i < ck.length; i++) {
				if (ck[i].getName().equalsIgnoreCase(getNomeEMAIL()) && ck[i].getValue() != null && !"".equalsIgnoreCase(ck[i].getValue())) {
					email = Criptografia.decrypt(ck[i].getValue(), ConfigUtil.getInstance().getProperty("chave_criptografia",CHAVE_CRIPT));
				} else if (ck[i].getName().equalsIgnoreCase(getNomeSENHA()) && ck[i].getValue() != null && !"".equalsIgnoreCase(ck[i].getValue())) {
					senha = Criptografia.decrypt(ck[i].getValue(), ConfigUtil.getInstance().getProperty("chave_criptografia",CHAVE_CRIPT));
				}
			}
			if (email != null && !"".equalsIgnoreCase(email)) {
				logout = (Boolean) BaseBean.getSessao().getAttribute("logout");
				if(logout == null){
					usuario = new UsuarioIdentificacao();
					usuario.setSenha(senha);
					usuario.setEmail(email);
					LoginBean lb = (LoginBean) app.evaluateExpressionGet(contexto, "#{loginBean}", LoginBean.class);
					lb.setUsuario(usuario);
					lb.loginListener();
					
					usuario = (UsuarioIdentificacao) BaseBean.getSessao().getAttribute("usuario");
				}
			}
		}
		
		for (Iterator<Menu> iterator = menus.iterator(); iterator.hasNext();) {
			Menu menu = (Menu) iterator.next();
			if(paginaAtual.lastIndexOf(menu.getUrl() + ".xhtml") > -1){
				if(menu.getPublico().booleanValue()){
					isMenuPublic = true;
					break;
				}
				
				if(usuario != null){
					for (Iterator<MenuPerfil> i2 = usuario.getMenus().iterator(); i2.hasNext();) {
						MenuPerfil mp = (MenuPerfil) i2.next();
						if(mp.getMenu().getMenuId() == menu.getMenuId()){
							usuarioPodeAcessar = true;
							break;
						}
					}
				}
				break;
			}
		}
		
		BaseBean bb = new BaseBean();
		
		if(usuario == null){
			if(isLoginPage || isMenuPublic){
				return;
			}else{
				BaseBean.getSessao().invalidate();
				BaseBean.getSessao().setAttribute("mensagem", new BeanMensagem(true, bb.getTituloApp(Erros.ERRO_SEM_ACESSO_RECURSO), bb.getBunde("erro_acesso_pagina"), FacesMessage.SEVERITY_ERROR));
				nh.handleNavigation(contexto, null, "index");
			}
		}else{
			if(isLoginPage){
				// Se o usuário logado tentar acessar a página de login ele é redirecionado para a página inicial
				
				nh.handleNavigation(contexto, null, "inicio");
			}else if(isInicialPage || isMenuPublic || isMensagemPage){
				return;
			}else if(!isInicialPage && usuarioPodeAcessar){
				return;
			}else{
				BaseBean.getSessao().invalidate();
				BaseBean.getSessao().setAttribute("mensagem", new BeanMensagem(true, bb.getTituloApp(Erros.ERRO_SEM_ACESSO_RECURSO), bb.getBunde("erro_acesso_pagina"), FacesMessage.SEVERITY_ERROR));
				nh.handleNavigation(contexto, null, "index");
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
	
	public String getNomeEMAIL(){
		String c = Criptografia.encrypt("asdfsdflasçdfasfãswrqwe0rjflasdfasjfjqwerdfasdf", ConfigUtil.getInstance().getProperty("chave_criptografia",CHAVE_CRIPT));
		return c;
	}
	public String getNomeSENHA(){
		String c = Criptografia.encrypt("fasfasdfjrwjr2j34jskdlfjaslçjç0a23d.sdçadldjd", ConfigUtil.getInstance().getProperty("chave_criptografia",CHAVE_CRIPT));
		return c;
	}

	public LoginService getLoginSevice() {
		return loginSevice;
	}

	public void setLoginSevice(LoginService loginSevice) {
		this.loginSevice = loginSevice;
	}

}