package br.com.clarotriagem.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.service.LoginService;
import br.com.clarotriagem.utils.BeanMensagem;

@ManagedBean
@Scope("view")
@Component
public class AlterarSenhaBean extends BaseBean {

	private static final long serialVersionUID = 3917750016325552611L;

	@Autowired
	private LoginService loginService;
	
	private String email;
	private String codigo;
	private String senha1;
	private String senha2;
	
	public String alterarSenha(){
		if(senha1 != null && senha2 != null){
			if(senha2.equals(senha1)){
				int redefiniu = loginService.redefinirSenha(email, codigo, senha1);
				switch (redefiniu) {
				case 0:
					BaseBean.getSessao().setAttribute("messages_geral", new BeanMensagem(true, getTituloApp(), getBunde("senha_alterada_sucesso"), FacesMessage.SEVERITY_INFO));
					addErroMessage(null, getTituloApp(), getBunde("codigo_recuperacao_invalido"));
					return "index";
				case 1:
					addErroMessage(null, getTituloApp(), getBunde("codigo_recuperacao_invalido"));
					return null;
				case 2:
					addErroMessage(null, getTituloApp(), getBunde("usuario_nao_encontrado"));
					return null;
				case 3:
					addErroMessage(null, getTituloApp(), getBunde("erro_inesperado"));
					return null;
				}
			}else{
				addErroMessage(null, getTituloApp(), getBunde("senha_incorreta"));
				return "index";
			}
		}
		return null;
		
		
	}
	
	public LoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getSenha1() {
		return senha1;
	}
	public void setSenha1(String senha1) {
		this.senha1 = senha1;
	}
	public String getSenha2() {
		return senha2;
	}
	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}
	

}
