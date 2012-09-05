package br.com.clarotriagem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.entitades.MenuPerfil;
import br.com.clarotriagem.entitades.Perfil;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.factory.ServiceFactory;
import br.com.clarotriagem.utils.Logger;
import br.com.clarotriagem.utils.mail.EnviaEmailBO;

@Service("loginService")
@Transactional
public class LoginService extends ServiceFactory<LoginService> {
	
	private final static Logger log = new Logger(LoginService.class);
	
	public LoginService(){
		super();
	}
	

	public List<UsuarioIdentificacao> efetuarLogin(UsuarioIdentificacao usuarioIdentificacao) {
		try {
			return getUsuarioIdentificacaoDAO().efetuarLogin(usuarioIdentificacao);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public List<MenuPerfil> buscaMenuLateralEsquerda(Perfil perfil) {
		try {
			return getMenuPerfilDAO().buscaMenuLateralEsquerda(perfil);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	
	public UsuarioIdentificacao buscaUsuario(String email) {
		try {
			return getUsuarioIdentificacaoDAO().buscaUsuarioAtivo(email);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	
	public void iniciaRecuperacaoSenhaUsuario(UsuarioIdentificacao u) {
		try {
			String alfa = u.getEmail().replace("@", "").replace(".", "");
			Double index = 0D;
			
			String codAlfa = "";
			index = Math.random() * (alfa.length() - 2);
			codAlfa += alfa.substring(index.intValue(), index.intValue() + 1);
			index = Math.random() * (alfa.length() - 2);
			codAlfa += alfa.substring(index.intValue(), index.intValue() + 1);
			index = Math.random() * (alfa.length() - 2);
			codAlfa += alfa.substring(index.intValue(), index.intValue() + 1);
			
			String codNum = "-";
			index = Math.random() * 9;
			codNum += index.intValue() + "";
			index = Math.random() * 9;
			codNum += index.intValue() + "";
			index = Math.random() * 9;
			codNum += index.intValue() + "";
			
			u.setIdConfirmacaoCadastro((codAlfa + codNum).toUpperCase());
			getUsuarioIdentificacaoDAO().update(u);
			EnviaEmailBO.enviaEMailRecuperacaoSenhaUsuario(u);
			
		} catch (Exception e) {
			log.erro(e);
		}
	}

	public int redefinirSenha(String email, String codigo, String senha) {
		try{
			UsuarioIdentificacao ui = getUsuarioIdentificacaoDAO().buscaUsuarioAtivo(email);
			if (ui != null) {
				if(codigo.equalsIgnoreCase(ui.getIdConfirmacaoCadastro())){
					ui.setSenha(senha);
					ui.setIdConfirmacaoCadastro(null);
					getUsuarioIdentificacaoDAO().update(ui);
					return 0;
				}else{
					ui.setIdConfirmacaoCadastro("ZZZZZZZZ");
					getUsuarioIdentificacaoDAO().update(ui);
					return 1;
				}
			}else{
				return 2;
			}
		}catch (Exception e) {
			log.erro(e);
			return 3;
		}
	}

}
