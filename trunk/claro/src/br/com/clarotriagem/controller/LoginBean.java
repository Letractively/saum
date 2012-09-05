package br.com.clarotriagem.controller;

import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.MenuPerfil;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.LoginService;
import br.com.clarotriagem.service.MensagemService;
import br.com.clarotriagem.utils.BeanMensagem;
import br.com.clarotriagem.utils.Criptografia;
import br.com.clarotriagem.utils.Erros;
import br.com.clarotriagem.utils.singleton.mapas.ConfigUtil;

@ManagedBean
@Scope("session")
@Component
public class LoginBean extends BaseBean {

	private static final long serialVersionUID = 4404240516543154319L;

	@Autowired
	private LoginService loginSevice;

	@Autowired
	private MensagemService mensagemService;
	
	private MenuModel model;
	private boolean permanecerLogado;
	private boolean temMenuAdministrativo;
	private UsuarioIdentificacao usuario;
	private List<MenuPerfil> menuLateralEsquerdo;

	public LoginBean() {
		super();
		this.req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		UsuarioIdentificacao uTmp = getUsuarioLogado();
		if (uTmp != null) {
			this.usuario = uTmp;
			temMenuAdministrativo = false;
			for (Iterator<MenuPerfil> iterator = uTmp.getMenus().iterator(); iterator.hasNext();) {
				MenuPerfil mp = (MenuPerfil) iterator.next();
				if(mp.getMenu().getColuna() == 3){
					temMenuAdministrativo = true;
				}
			}
		}
		
		BeanMensagem bm = (BeanMensagem) req.getSession().getAttribute("mensagem");
		if(bm != null && bm.isTemMensagem()){
			addMessage(bm.getTitulo(), bm.getMensagem(), bm.getFacesMessage());
			req.getSession().removeAttribute("mensagem");
		}
		
	}
	
	public void loginListener() {
		this.req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.req.getSession().invalidate();
		
		List<UsuarioIdentificacao> u = loginSevice.efetuarLogin(usuario);
		if (u != null && u.size() > 0) {
			usuario = u.get(0);
			if(usuario.getIdConfirmacaoCadastro() == null || "".equalsIgnoreCase(usuario.getIdConfirmacaoCadastro())){
				List<MenuPerfil> menus = loginSevice.buscaMenuLateralEsquerda(usuario.getPerfil());
				if(menus != null){
					usuario.setMenus(menus);
					setUsuarioLogado(usuario);
					addInfoMessage(getBunde("bemvindo_msg") + " " + usuario.getNome());
				}
			}
		}
	}
	public String login() {
		this.req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.req.getSession().invalidate();
		
		List<UsuarioIdentificacao> u = loginSevice.efetuarLogin(usuario);
		if (u != null && u.size() > 0) {
			usuario = u.get(0);
			if(usuario.getIdConfirmacaoCadastro() == null || "".equalsIgnoreCase(usuario.getIdConfirmacaoCadastro())){
				List<MenuPerfil> menus = loginSevice.buscaMenuLateralEsquerda(usuario.getPerfil());
				setaCookiePermanecerLogado();
				if(menus != null){
					usuario.setMenus(menus);
					setUsuarioLogado(usuario);
					addInfoMessage(getBunde("bemvindo_msg") + " " + usuario.getNome());
					return "inicio";
				}else{
					BaseBean.getSessao().setAttribute("mensagem", new BeanMensagem(true, getTituloApp(Erros.ERRO_LOGIN_SESSAO), getBunde("erro_inesperado"), FacesMessage.SEVERITY_ERROR));
					return "index";
				}
			}else{
				BaseBean.getSessao().setAttribute("mensagem", new BeanMensagem(true, getTituloApp(Erros.ERRO_LOGIN_SESSAO), getBunde("erro_login_nao_autorizado_por_recuperacao"), FacesMessage.SEVERITY_ERROR));
				return "index";
			}
		} else {
			BaseBean.getSessao().setAttribute("mensagem", new BeanMensagem(true, getTituloApp(Erros.ERRO_LOGIN_SESSAO), getBunde("login_incorreto"), FacesMessage.SEVERITY_ERROR));
			return "index";
		}
	}

	public String esqueciMinhaSenha() {
		this.req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.req.getSession().invalidate();
		
		UsuarioIdentificacao u = loginSevice.buscaUsuario(usuario.getEmail());
		if (u != null) {
			loginSevice.iniciaRecuperacaoSenhaUsuario(u);
		}
		BaseBean.getSessao().setAttribute("mensagem", new BeanMensagem(true, getTituloApp(), getBunde("erro_login_nao_autorizado_por_recuperacao"), FacesMessage.SEVERITY_INFO));
		return "alterar_senha";
	}
	
	private void setaCookiePermanecerLogado() {
		this.res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		if(permanecerLogado){
			Cookie ckUsr = new Cookie(getNomeEMAIL(), Criptografia.encrypt(usuario.getEmail(), ConfigUtil.getInstance().getProperty("chave_criptografia",CHAVE_CRIPT)));
			Cookie ckSen = new Cookie(getNomeSENHA(), Criptografia.encrypt(usuario.getSenha(), ConfigUtil.getInstance().getProperty("chave_criptografia",CHAVE_CRIPT)));
			ckUsr.setMaxAge(60 * 60 * 24 * 50);
			ckSen.setMaxAge(60 * 60 * 24 * 50);
			this.res.addCookie(ckUsr);
			this.res.addCookie(ckSen);
		}else{
			Cookie ckUsr = new Cookie(getNomeEMAIL(), "");
			Cookie ckSen = new Cookie(getNomeSENHA(), "");
			this.res.addCookie(ckUsr);
			this.res.addCookie(ckSen);
		}
	}

	public List<MenuPerfil> getMenuLateralEsquerdo() {
		if (menuLateralEsquerdo == null) {
			UsuarioIdentificacao uTmp = (UsuarioIdentificacao) req.getSession().getAttribute("usuario");
			if (uTmp.getId() == null) {
				menuLateralEsquerdo = loginSevice.buscaMenuLateralEsquerda(null);
			} else {
				menuLateralEsquerdo = uTmp.getMenus();
			}
		}
		return menuLateralEsquerdo;
	}
	
    public String encerraSessao() {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            setPermanecerLogado(false);
            setaCookiePermanecerLogado();
            ctx.getExternalContext().getSessionMap().remove("loginBean");
            setUsuarioLogado(null);
            BaseBean.getSessao().invalidate();
            BaseBean.getSessao().setAttribute("logout", true);
            BaseBean.getSessao().setAttribute("mensagem", new BeanMensagem(true, getTituloApp(), getBunde("sessao_encerrada"), FacesMessage.SEVERITY_INFO));
            return "index";
        } catch (NullPointerException e) {
        	BaseBean.getSessao().setAttribute("logout", true);
        	return "index";
        } catch (Exception e) {
        	BaseBean.getSessao().setAttribute("mensagem", new BeanMensagem(true, getTituloApp(), getBunde("erro_inesperado"), FacesMessage.SEVERITY_FATAL));
        	return "index";
        }
    }

	
	public UsuarioIdentificacao getUsuario() {
		if (usuario == null) {
			usuario = new UsuarioIdentificacao();
		}
		return usuario;
	}
	public String getNomeEMAIL(){
		String c = Criptografia.encrypt("asdfsdflasçdfasfãswrqwe0rjflasdfasjfjqwerdfasdf", ConfigUtil.getInstance().getProperty("chave_criptografia",CHAVE_CRIPT));
		return c;
	}
	public String getNomeSENHA(){
		String c = Criptografia.encrypt("fasfasdfjrwjr2j34jskdlfjaslçjç0a23d.sdçadldjd", ConfigUtil.getInstance().getProperty("chave_criptografia",CHAVE_CRIPT));
		return c;
	}
	public boolean getTemMenuAdministrativo() {
		return temMenuAdministrativo;
	}

	
	public void setUsuario(UsuarioIdentificacao usuario) {
		this.usuario = usuario;
	}

	public void setMenuLateralEsquerdo(List<MenuPerfil> menuLateralEsquerdo) {
		this.menuLateralEsquerdo = menuLateralEsquerdo;
	}

	public LoginService getLoginSevice() {
		return loginSevice;
	}

	public void setLoginSevice(LoginService loginSevice) {
		this.loginSevice = loginSevice;
	}

	public MensagemService getMensagemService() {
		return mensagemService;
	}

	public void setMensagemService(MensagemService mensagemService) {
		this.mensagemService = mensagemService;
	}

	public boolean isPermanecerLogado() {
		return permanecerLogado;
	}

	public void setPermanecerLogado(boolean permanecerLogado) {
		this.permanecerLogado = permanecerLogado;
	}

	public void setTemMenuAdministrativo(boolean temMenuAdministrativo) {
		this.temMenuAdministrativo = temMenuAdministrativo;
	}

	public MenuModel getModel() {
			model = new DefaultMenuModel();  
			
			List<MenuPerfil> menus = usuario.getMenus();
			for (Iterator<MenuPerfil> iterator = menus.iterator(); iterator.hasNext();) {
				MenuPerfil mp = (MenuPerfil) iterator.next();
				if(mp.getMenu().getColuna() == 2 || mp.getMenu().getColuna() == 3){
					MenuItem item = new MenuItem();  
					item.setValue(mp.getMenu().getNome());
					item.setIcon("../img/menu/" + mp.getMenu().getMenuId() + ".png");
					item.setUrl(mp.getMenu().getUrl() + ".html");
					item.setId("id_" + mp.getMenu().getMenuId());
					model.addMenuItem(item);
				}
			}

		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

}
