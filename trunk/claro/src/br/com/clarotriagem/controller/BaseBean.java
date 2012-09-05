package br.com.clarotriagem.controller;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.clarotriagem.controller.factory.ValidadorGeral;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;

public class BaseBean extends ValidadorGeral implements Serializable {

	private static final long serialVersionUID = 4253772506192061734L;

	private static String locale = "português";
//	private static String locale = Locale.getDefault().getDisplayLanguage();
	private static String localeAbr = "pt";
	public final String CHAVE_CRIPT = "sP81h6rB1wr2qoieqwrl5zW3";
	protected HttpServletRequest req;
	protected HttpServletResponse res;
	private UsuarioIdentificacao usuarioLogado;
	
	public static HttpSession getSessao(){
		FacesContext contexto = FacesContext.getCurrentInstance();
		HttpSession sessao = (HttpSession) contexto.getExternalContext().getSession(true);
		return sessao;
	}
	
	public UsuarioIdentificacao getUsuarioLogado() {
		if(usuarioLogado == null){
			this.req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
			usuarioLogado = (UsuarioIdentificacao) req.getSession().getAttribute("usuario");
		}
		return usuarioLogado;
	}
	
	public void setUsuarioLogado(UsuarioIdentificacao usuarioLogado) {
		this.req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		this.req.getSession().setAttribute("usuario", usuarioLogado);
		this.usuarioLogado = usuarioLogado;
	}

	public String getTituloApp(int codErro){
		return getTituloApp() + " - " + codErro;
	}
	public String getTituloApp(){
		return ResourceBundle.getBundle("/resources", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString("nome_aplicativo_resumido");
	}

	public static String getBundeExterno(String resource){
		return ResourceBundle.getBundle("/resources", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString(resource);
	}
	public String getBunde(String resource){
		return ResourceBundle.getBundle("/resources", FacesContext.getCurrentInstance().getViewRoot().getLocale()).getString(resource);
	}

	public void addInfoMessage(String msg) {
		addInfoMessage(null, getBunde("nome_aplicativo_resumido"), msg);
	}
	public void addInfoMessage(String componentId, String msg) {
		addInfoMessage(componentId, getBunde("nome_aplicativo_resumido"), msg);
	}
	public void addInfoMessage(String componentId, String titulo, String msg) {
		FacesMessage message = new FacesMessage(titulo, msg);
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		addMessage(componentId, message);
	}
	
	
	public void addErroMessage(String componentId, String titulo, String msg) {
		FacesMessage message = new FacesMessage(titulo, msg);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		addMessage(componentId, message);
	}
	
	
	public void addFatalMessage(String componentId, String titulo, String msg) {
		FacesMessage message = new FacesMessage(titulo, msg);
		message.setSeverity(FacesMessage.SEVERITY_FATAL);
		addMessage(componentId, message);
	}
	
	
	//warns
	public void addWarnMessage(String msg) {
		addWarnMessage(null, getBunde("nome_aplicativo_resumido"), msg);
	}
	public void addWarnMessage(String componentId, String msg) {
		addWarnMessage(componentId, getBunde("nome_aplicativo_resumido"), msg);
	}
	public void addWarnMessage(String componentId, String titulo, String msg) {
		FacesMessage message = new FacesMessage(titulo, msg);
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		addMessage(componentId, message);
	}
	//fim warns
	
	private void addMessage(String componentId, FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(componentId, message);
	}
	
	public void addMessage(String titulo, String mensagem, Severity facesMessage) {
		FacesMessage message = new FacesMessage(titulo, mensagem);
		message.setSeverity(facesMessage);
		addMessage(null, message);
	}


	public void setLocale(String locale1) {
		BaseBean.locale = locale1;
	}

	public synchronized String getLocale() {
		return BaseBean.locale;
	}

	public void changeLanguagePortugues() {
		setLocale("português");
		setLocaleAbr("pt");
	}
	public void changeLanguageEnglish() {
		setLocale("english");
		setLocaleAbr("en");
	}

	public String getLocaleAbr() {
		return BaseBean.localeAbr;
	}

	public void setLocaleAbr(String localeAbr) {
		BaseBean.localeAbr = localeAbr;
	}

}
