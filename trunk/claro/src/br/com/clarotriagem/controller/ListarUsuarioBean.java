package br.com.clarotriagem.controller;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.UsuarioService;
import br.com.clarotriagem.service.lazy.UsuariosListLazy;

@ManagedBean
@Scope("view")
@Component
public class ListarUsuarioBean extends BaseBean {

	private static final long serialVersionUID = -3936282489243948922L;

	@Autowired
	private UsuarioService usuarioService;
	
	private UsuarioIdentificacao usuarioCadastro;
	private LazyDataModel<UsuarioIdentificacao> usuarios;
	
	public String desativarUsuario(){
		if(usuarioCadastro != null && usuarioCadastro.getAtivo() == true && usuarioCadastro.getId().compareTo(getUsuarioLogado().getId()) == 0){
			usuarioService.desativarUsuario(usuarioCadastro);
		}else{
			if(usuarioCadastro.getId().compareTo(getUsuarioLogado().getId()) == 0){
				addErroMessage(null, getTituloApp(), getBunde("erro_mesmo_usuario"));
			}else{
				addErroMessage(null, getTituloApp(), getBunde("erro_inesperado"));
			}
		}
		return null;
	}
	public String ativarUsuario(){
		if(usuarioCadastro != null && usuarioCadastro.getAtivo() == false && usuarioCadastro.getId().compareTo(getUsuarioLogado().getId()) == 0){
			usuarioService.ativarUsuario(usuarioCadastro);
		}else{
			if(usuarioCadastro.getId().compareTo(getUsuarioLogado().getId()) == 0){
				addErroMessage(null, getTituloApp(), getBunde("erro_mesmo_usuario"));
			}else{
				addErroMessage(null, getTituloApp(), getBunde("erro_inesperado"));
			}
		}
		return null;
	}
	public LazyDataModel<UsuarioIdentificacao> getUsuarios() {
		if(usuarios == null){
			usuarios = new UsuariosListLazy(usuarioService);
		}
		return usuarios;
	}
	public UsuarioIdentificacao getUsuarioCadastro() {
		if(usuarioCadastro == null){
			usuarioCadastro = new UsuarioIdentificacao();
		}
		return usuarioCadastro;
	}
	
	
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	public void setUsuarioCadastro(UsuarioIdentificacao usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	public void setUsuarios(LazyDataModel<UsuarioIdentificacao> usuarios) {
		this.usuarios = usuarios;
	}
}
