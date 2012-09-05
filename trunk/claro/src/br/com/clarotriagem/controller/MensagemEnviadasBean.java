package br.com.clarotriagem.controller;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.Mensagem;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.MensagemService;
import br.com.clarotriagem.service.lazy.MensagemEnviadasListLazy;

@ManagedBean
@Scope("view")
@Component
public class MensagemEnviadasBean extends BaseBean {

	private static final long serialVersionUID = -6109005302295398868L;

	@Autowired
	private MensagemService mensagemService;

	private Mensagem mensagem;
	private LazyDataModel<Mensagem> mensagemsTodas;
	private UsuarioIdentificacao usuario;

	public MensagemEnviadasBean() {
		super();
		usuario = getUsuarioLogado();
	}
	
	public LazyDataModel<Mensagem> getMensagemsTodas() {
		if (mensagemsTodas == null) {
			mensagemsTodas = new MensagemEnviadasListLazy(usuario, mensagemService);
		}
		return mensagemsTodas;
	}
	
	public Mensagem getMensagem() {
		if(mensagem == null){
			mensagem = new Mensagem();
		}
		return mensagem;
	}
	
	public MensagemService getMensagemService() {
		return mensagemService;
	}

	public void setMensagemService(MensagemService mensagemService) {
		this.mensagemService = mensagemService;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public void setMensagemsTodas(LazyDataModel<Mensagem> mensagemsTodas) {
		this.mensagemsTodas = mensagemsTodas;
	}

}
