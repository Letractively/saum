package br.com.clarotriagem.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.Mensagem;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.MensagemService;
import br.com.clarotriagem.service.UsuarioService;

@ManagedBean
@Scope("view")
@Component
public class MensagemEnviarBean extends BaseBean {

	private static final long serialVersionUID = 169503597801353827L;
	@Autowired
	private MensagemService mensagemService;
	@Autowired
	private UsuarioService usuarioService;

	private String termoBusca;
	private Mensagem mensagem;
	private UsuarioIdentificacao destinatario;
	private UsuarioIdentificacao usuario;

	public MensagemEnviarBean() {
		super();
		usuario = getUsuarioLogado();
	}
	
	public List<String> buscaNomes(String query){
		List<String> ret = usuarioService.buscaUsuariosAtivos(query);
		return ret;
	}
	
	public void usuarioSelecionado(SelectEvent event){
		destinatario = usuarioService.buscaUsuario(termoBusca);
	}
	

	public UsuarioIdentificacao getUsuario() {
		if(usuario == null){
			usuario = new UsuarioIdentificacao();
		}
		return usuario;
	}
	
	
	public String enviarMensagem(){
		mensagem.setData(new Timestamp(System.currentTimeMillis()));
		mensagem.setLida(false);
		mensagem.setExcluido(false);
		mensagem.setRemetente(usuario);
		mensagem.setDestinatario(destinatario);
		boolean ret = mensagemService.enviarMensagem(mensagem);
		mensagem = new Mensagem();
		destinatario = new UsuarioIdentificacao();
		if(ret){
			addInfoMessage(getBunde("mensagem_enviada_sucesso"));
		}else{
			addErroMessage(null, getTituloApp(), getBunde("erro_inesperado"));
		}
		return null;
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

	public void setUsuario(UsuarioIdentificacao usuario) {
		this.usuario = usuario;
	}
	
	public MensagemService getMensagemService() {
		return mensagemService;
	}

	public void setMensagemService(MensagemService mensagemService) {
		this.mensagemService = mensagemService;
	}

	public String getTermoBusca() {
		return termoBusca;
	}

	public void setTermoBusca(String termoBusca) {
		this.termoBusca = termoBusca;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public UsuarioIdentificacao getDestinatario() {
		if(destinatario == null){
			destinatario = new UsuarioIdentificacao();
		}
		return destinatario;
	}

	public void setDestinatario(UsuarioIdentificacao destinatario) {
		this.destinatario = destinatario;
	}

}
