package br.com.meganet.facebookAPI.facebook.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.meganet.facebookAPI.facebook.FacebookException;
import br.com.meganet.facebookAPI.facebook.OAuthException;
import br.com.meganet.facebookAPI.facebook.bean.oauth.SignedRequest;
import br.com.meganet.facebookAPI.facebook.bean.profile.Profile;
import br.com.meganet.facebookAPI.infra.jee.AbstractController;
import br.com.meganet.facebookAPI.infra.jee.UserLogged;

public class AuthController extends AbstractController {

	private String signed_request;
	private String code;

	private UserLogged userLogged;

	public AuthController(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	// Autenticação do usuário
	// Aprovação das permissoes
	private boolean userAuth() throws IOException {
		if (signed_request == null) {
			return true;
		}
		try {
			SignedRequest signedRequest = facebook.getOauth().parseSignedRequest(signed_request);
			userLogged.setSignedRequest(signedRequest);
			return (signedRequest.getUserId() != null);
		} catch (OAuthException e) {
			e.printStackTrace(System.err);
		}
		return false;
	}

	// Autenticacao da aplicacao
	private boolean appAuth() {
		try {
			String accessToken = facebook.getOauth().getAccessToken(code);
			userLogged.setAccessToken(accessToken);
			return true;
		} catch (FacebookException e) {
			// Recpera o profile reduzido
			Profile profile = facebook.getService().getProfileByUserId(userLogged.getSignedRequest().getUserId());
			userLogged.setProfile(profile);

			e.printStackTrace(System.err);
			
			return false;
		}
	}

	public void execute() throws Exception {
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		userLogged = (UserLogged) request.getSession().getAttribute("userLogged");
		if(userLogged == null){
			request.getSession(true);
			userLogged = new UserLogged();
			request.getSession().setAttribute("userLogged", userLogged);
			System.out.println("USERLOGGED NULO " + request.getSession());
		}
		
		if (signed_request == null && code == null) {
			System.out.println("DEBUG " + this.getClass() + " - Parametros 'signed_request' e 'code' estão nulos");
			redirectTo(facebook.getOauth().getLoginUrl());
			return;
		}
		
		if (!userAuth()) {
			System.out.println("DEBUG " + this.getClass() + " - Usuário não autenticado");
			redirectTo(facebook.getOauth().getLoginUrl());
			return;
		}
		
		System.out.println("DEBUG " + this.getClass() + " - signed_request = " + signed_request);
		System.out.println("DEBUG " + this.getClass() + " -           code = " + code);

		if (code == null) {
			System.out.println("DEBUG " + this.getClass() + " - Solicitando authorization code");
			redirectTo(facebook.getOauth().getLoginUrl());
		} else if (appAuth()) {
			Profile profile = facebook.getService().getProfile(userLogged.getAccessToken());
			userLogged.setProfile(profile);

			forward("/auth/index.do");
		} else {
			System.out.println("DEBUG " + this.getClass() + " - Não foi possível realiza a autenticação da aplicação");
			redirectTo(facebook.getAppCanvas() + "error_auth.jsp");
		}
		
		request.getSession().setAttribute("userLogged", userLogged);
		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	}
	
	
	public String getSigned_request() {
		return signed_request;
	}

	public void setSigned_request(String signed_request) {
		this.signed_request = signed_request;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
