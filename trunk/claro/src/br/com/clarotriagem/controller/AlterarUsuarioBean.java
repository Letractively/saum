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
import br.com.clarotriagem.utils.singleton.mapas.ConfigUtil;

@ManagedBean
@Scope("view")
@Component
public class AlterarUsuarioBean extends BaseBean {

	private static final long serialVersionUID = 5642284576349552416L;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	private UsuarioIdentificacao usuarioCadastro;
	private String senha1;
	private String senha2;
	
	private Perfil perfil;
	private Map<String, String> estados;
	private Map<String, Long> perfis;
	
	public AlterarUsuarioBean(){
		super();
		UsuarioIdentificacao uTmp = getUsuarioLogado();
		if (uTmp != null) {
			this.usuarioCadastro = uTmp;
		}
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
	public String alterar(){
		if(usuarioCadastro != null){
			UsuarioIdentificacao ret = usuarioService.alterarUsuario(usuarioCadastro);
			if(ret != null){
				setUsuarioLogado(usuarioCadastro);
				addInfoMessage(getBunde("alterado_sucesso"));
			}
		}
		return null;
	}
	public Perfil getPerfil() {
		if(perfil == null){
			perfil = usuarioCadastro.getPerfil();
		}
		return perfil;
	}
	public Map<String, String> getEstados() {
		String est[] = ConfigUtil.getInstance().getProperty("estados", "DF-Distrito Federal").split(";");
		estados = new HashMap<String, String>();
		for (int i = 0; i < est.length; i++) {
			String t[] = est[i].split("-");
			estados.put(t[1], t[0]);
		}
		return estados;
	}

	
	public UsuarioIdentificacao getUsuarioCadastro() {
		return usuarioCadastro;
	}
	public void setUsuarioCadastro(UsuarioIdentificacao usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
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
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public void setEstados(Map<String, String> estados) {
		this.estados = estados;
	}
	public void setPerfis(Map<String, Long> perfis) {
		this.perfis = perfis;
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
}
