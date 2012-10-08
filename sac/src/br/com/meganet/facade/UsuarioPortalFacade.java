package br.com.meganet.facade;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.meganet.bo.LoginBO;
import br.com.meganet.bo.MenuBO;
import br.com.meganet.hbm.vo.MenuVO;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.Criptografia;
import br.com.meganet.util.Logger;

public class UsuarioPortalFacade {
	
	private Logger log;
	private LoginBO loginBO;
	private MenuBO menuBO;

	public void setLog(Logger log) {
		this.log = log;
	}
	
	public Usuario login(Usuario usuario){
		try {
			return loginBO.login(usuario);
		} catch (Exception e) {
			log.erroSemEmail("Erro na autenticação do usuario: " + usuario.getUsuarioEmail());
			return null;
		}
	}

	public Usuario loginFB(Usuario usuario){
		try {
			return loginBO.loginFB(usuario);
		} catch (Exception e) {
			log.erroSemEmail("Erro na autenticação do usuario no FB: " + usuario.getUsuarioEmail());
			return null;
		}
	}
	
	public List<MenuVO> montaMenu(int opcao, String indiceMenu) {
		List<MenuVO> mapMenu = menuBO.montaMenu(opcao, indiceMenu);
		return mapMenu;
	}

	/**
	 * 
	 * Metodos de injecao do SPring 
	 * 
	 */
	public LoginBO getLoginBO() {
		return loginBO;
	}
	public void setLoginBO(LoginBO loginBO) {
		this.loginBO = loginBO;
	}
	public MenuBO getMenuBO() {
		return menuBO;
	}
	public void setMenuBO(MenuBO menuBO) {
		this.menuBO = menuBO;
	}

	public int esqueci(Usuario usuario) {
		try {
			return loginBO.esqueci(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return 2;
		}
	}

	public Usuario trocaSenha(String senha, String param) {
		try{
			return loginBO.trocaSenha(senha, param);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	public String consultaUsuarioParaLogar(Usuario usuario, HttpServletRequest req, HttpServletResponse res) {
		try{
			log.info("Tentando realizar login: " + usuario.getUsuarioEmail());
			Usuario usr = login(usuario);
			if (usr != null) {
				req.getSession().setAttribute("usuario", usr);
				if (usuario.getPermanecer()) {
					Cookie ckUsr = new Cookie(getNomeEMAIL(), Criptografia.encrypt(usr.getUsuarioEmail(), ConfigUtil.getInstance().getProperty("chave_criptografia",Constantes.CHAVE_CRIPTOGRAFIA)));
					Cookie ckSen = new Cookie(getNomeSENHA(), usr.getUsuarioSenha());
					ckUsr.setMaxAge(60 * 60 * 24 * 50);
					ckSen.setMaxAge(60 * 60 * 24 * 50);
					res.addCookie(ckUsr);
					res.addCookie(ckSen);
				}
				return usr.getUsuarioNome();
			} else {
				Cookie ckUsr = new Cookie(getNomeEMAIL(), "");
				Cookie ckSen = new Cookie(getNomeSENHA(), "");
				res.addCookie(ckUsr);
				res.addCookie(ckSen);
				return "";
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public String loginCookie(HttpServletRequest req, HttpServletResponse res) {
		try{
			
			Usuario usuario;
			Cookie[] ck = req.getCookies();
			
			usuario = (Usuario) req.getSession().getAttribute("usuario");
			if(usuario == null){
				usuario = new Usuario();
				req.getSession().setAttribute("acoes", new HashMap<String, String>());
				if (ck != null) {
					for (int i = 0; i < ck.length; i++) {
						if (ck[i].getName().equalsIgnoreCase(getNomeEMAIL()) && ck[i].getValue() != null && !"".equalsIgnoreCase(ck[i].getValue())) {
							usuario.setUsuarioEmail(Criptografia.decrypt(ck[i].getValue(), ConfigUtil.getInstance().getProperty("chave_criptografia",Constantes.CHAVE_CRIPTOGRAFIA)));
						} else if (ck[i].getName().equalsIgnoreCase(getNomeSENHA()) && ck[i].getValue() != null && !"".equalsIgnoreCase(ck[i].getValue())) {
							usuario.setUsuarioSenha(ck[i].getValue());
						}
					}
					if (!"".equalsIgnoreCase(usuario.getUsuarioEmail()) && usuario.getUsuarioEmail() != null) {
						return consultaUsuarioParaLogar(usuario, req, res);
					} else {
						return "";
					}
				} else {
					return "";
				}
			}else{
				return usuario.getUsuarioNome();
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}

	}
	
	public String getNomeEMAIL(){
		String c = Criptografia.encrypt("asdfsdflasçdfasfãswrqwe0rjflasdfasjfjqwerdfasdf", ConfigUtil.getInstance().getProperty("chave_criptografia",Constantes.CHAVE_CRIPTOGRAFIA));
		return c;
	}
	public String getNomeSENHA(){
		String c = Criptografia.encrypt("fasfasdfjrwjr2j34jskdlfjaslçjç0a23d.sdçadldjd", ConfigUtil.getInstance().getProperty("chave_criptografia",Constantes.CHAVE_CRIPTOGRAFIA));
		return c;
	}


}
