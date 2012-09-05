package br.com.clarotriagem.controller;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.Mensagem;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.MensagemService;
import br.com.clarotriagem.service.lazy.MensagemListLazy;

@ManagedBean
@Scope("session")
@Component
public class MensagemBean extends BaseBean {

	private static final long serialVersionUID = 2973390361091985746L;

	private final long TEMPO_BUSCA = 12000;

	@Autowired
	private MensagemService mensagemService;
	
	private long tempoUltimaBusca = 0;

	private Mensagem mensagem;
	private Integer qtdMensagemsNaoLidas;
	private LazyDataModel<Mensagem> mensagemsTodas;
	private UsuarioIdentificacao usuario;

	public MensagemBean() {
		super();
		usuario = getUsuarioLogado();
	}
	
	public void apagarMensagem(){
		try {
			mensagemService.delete(mensagem);
			qtdMensagemsNaoLidas = mensagemService.getQtdMensagemNaoLida(usuario);
			mensagemsTodas = new MensagemListLazy(usuario, mensagemService);
			addInfoMessage(getBunde("mensagem_excluido_sucesso"));
		} catch (Exception e) {
			addErroMessage(null, getTituloApp(), getBunde("mensagem_excluido_sucesso"));
		}
	}
	
	public void lerMensagem(){
		mensagemService.marcaMensagemComoLida(mensagem);
		mensagemsTodas = new MensagemListLazy(usuario, mensagemService);
		qtdMensagemsNaoLidas = mensagemService.getQtdMensagemNaoLida(usuario);
	}
	
	public void marcarComoNaoLida(){
		mensagemService.marcaMensagemComoNaoLida(mensagem);
		mensagemsTodas = new MensagemListLazy(usuario, mensagemService);
		qtdMensagemsNaoLidas = mensagemService.getQtdMensagemNaoLida(usuario);
	}
	
	public LazyDataModel<Mensagem> getMensagemsTodas() {
		if (mensagemsTodas == null) {
			mensagemsTodas = new MensagemListLazy(usuario, mensagemService);
		}
		return mensagemsTodas;
	}
	
	public Integer getQtdMensagemsNaoLidas() {
		long tempoAtual = System.currentTimeMillis();
		if(qtdMensagemsNaoLidas == null || (tempoAtual - tempoUltimaBusca) > TEMPO_BUSCA){
			qtdMensagemsNaoLidas = mensagemService.getQtdMensagemNaoLida(usuario);
			tempoUltimaBusca = System.currentTimeMillis();
		}
		return qtdMensagemsNaoLidas;
	}

	
	public UsuarioIdentificacao getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioIdentificacao usuario) {
		this.usuario = usuario;
	}

	public MensagemService getMensagemService() {
		return mensagemService;
	}

	public void setMensagemService(MensagemService mensagemService) {
		this.mensagemService = mensagemService;
	}

	public Mensagem getMensagem() {
		if(mensagem == null){
			mensagem = new Mensagem();
		}
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public void setQtdMensagemsNaoLidas(Integer qtdMensagemsNaoLidas) {
		this.qtdMensagemsNaoLidas = qtdMensagemsNaoLidas;
	}

	public void setMensagemsTodas(LazyDataModel<Mensagem> mensagemsTodas) {
		this.mensagemsTodas = mensagemsTodas;
	}

}
