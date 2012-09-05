package br.com.clarotriagem.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.Perfil;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.PerfilService;
import br.com.clarotriagem.service.UsuarioService;

@ManagedBean
@Scope("view")
@Component
public class AutorizaPreCadastroBean extends BaseBean {

	private static final long serialVersionUID = -912512360484706654L;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	private UsuarioIdentificacao usuarioCadastro;
	private List<UsuarioIdentificacao> listaUsuariosNaoAutorizados;
	
	private Perfil perfil;
	private Map<String, Long> perfis;
	
	public String apagarUsuarioNaoAutorizado(){
		if(usuarioCadastro != null){
			usuarioService.apagarUsuarioNaoAutorizado(usuarioCadastro);
			for (Iterator<UsuarioIdentificacao> iterator = listaUsuariosNaoAutorizados.iterator(); iterator.hasNext();) {
				UsuarioIdentificacao ui = (UsuarioIdentificacao) iterator.next();
				if(ui.getId() == usuarioCadastro.getId()){
					listaUsuariosNaoAutorizados.remove(ui);
					break;
				}
			}
		}
		return null;
	}
	public String autorizarUsuario(){
		if(usuarioCadastro != null){
			usuarioCadastro.setPerfil(perfil);
			usuarioService.autorizarUsuario(usuarioCadastro);
			for (Iterator<UsuarioIdentificacao> iterator = listaUsuariosNaoAutorizados.iterator(); iterator.hasNext();) {
				UsuarioIdentificacao ui = (UsuarioIdentificacao) iterator.next();
				if(ui.getId() == usuarioCadastro.getId()){
					listaUsuariosNaoAutorizados.remove(ui);
					break;
				}
			}
			
		}
		return null;
	}
	public List<UsuarioIdentificacao> getListaUsuariosNaoAutorizados() {
		if(listaUsuariosNaoAutorizados == null){
			listaUsuariosNaoAutorizados = usuarioService.buscaUsuariosNaoAutorizados();
		}
		return listaUsuariosNaoAutorizados;
	}
	public UsuarioIdentificacao getUsuarioCadastro() {
		if(usuarioCadastro == null){
			usuarioCadastro = new UsuarioIdentificacao();
		}
		return usuarioCadastro;
	}
	public Perfil getPerfil() {
		if(perfil == null){
			perfil = new Perfil(new Long(0));
		}
		return perfil;
	}
	public Map<String, Long> getPerfis() {
		if(perfis == null){
			List<Perfil> p = perfilService.findAll();
			perfis = new HashMap<String, Long>();
			for (Iterator<Perfil> iterator = p.iterator(); iterator.hasNext();) {
				Perfil perfil = (Perfil) iterator.next();
				perfis.put(perfil.getNome(), perfil.getPerfilId());
			}
		}
		return perfis;
	}
	
	
	
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	public PerfilService getPerfilService() {
		return perfilService;
	}
	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}
	public void setUsuarioCadastro(UsuarioIdentificacao usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	public void setListaUsuariosNaoAutorizados(List<UsuarioIdentificacao> listaUsuariosNaoAutorizados) {
		this.listaUsuariosNaoAutorizados = listaUsuariosNaoAutorizados;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public void setPerfis(Map<String, Long> perfis) {
		this.perfis = perfis;
	}

}
