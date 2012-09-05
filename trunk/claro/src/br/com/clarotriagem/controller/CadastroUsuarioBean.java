package br.com.clarotriagem.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.Empresa;
import br.com.clarotriagem.entitades.Perfil;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.PerfilService;
import br.com.clarotriagem.service.UsuarioService;
import br.com.clarotriagem.utils.singleton.mapas.ConfigUtil;

@ManagedBean
@Scope("view")
@Component
public class CadastroUsuarioBean extends BaseBean {

	private static final long serialVersionUID = 8568940562650141792L;

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
	
	public String cadastrar(){
		if(senha1.equals(senha2)){
			if(validaCampos()){
				UsuarioIdentificacao uTmp = getUsuarioLogado();
				
				if(uTmp == null){
					usuarioCadastro.setEmpresa(new Empresa(new Long(1)));
				}else{
					usuarioCadastro.setEmpresa(uTmp.getEmpresa());
				}
				
				if(perfil == null){
					usuarioCadastro.setPerfil(new Perfil(new Long(0)));
				}else{
					usuarioCadastro.setPerfil(perfil);
				}
				
				usuarioCadastro.setAtivo(true);
				usuarioCadastro.setCadastroConfirmado(false);
				usuarioCadastro.setQtdLoginIncorreto(0);
				usuarioCadastro.setSenha(senha1);	
				usuarioService.save(usuarioCadastro);
				usuarioService.enviaEMailCadastroUsuario(usuarioCadastro);
				usuarioCadastro = null;
				addInfoMessage(getBunde("cadastro_realizado_sucesso"));
			}
		}else{
			addWarnMessage(getBunde("senha_incorreta"));
		}
		return null;
	}
	public UsuarioIdentificacao getUsuarioCadastro() {
		if(usuarioCadastro == null){
			usuarioCadastro = new UsuarioIdentificacao();
		}
		return usuarioCadastro;
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
	public String getSenha1() {
		if(senha1 == null){
			senha1 = "";
		}
		return senha1;
	}

	public String getSenha2() {
		if(senha2 == null){
			senha2 = "";
		}
		return senha2;
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
	
	public Perfil getPerfil() {
		if(perfil == null){
			perfil = new Perfil();
		}
		return perfil;
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
	public void setSenha1(String senha1) {
		this.senha1 = senha1;
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
	private boolean validaCampos() {
		boolean ret = true;
		
		if(!validaEMAIL(usuarioCadastro.getEmail())){
			ret = false;
			addErroMessage(null, getBunde("erro_msg"), getBunde("email_invalido"));
		}
		
		if(!validaCEP(usuarioCadastro.getCep())){
			ret = false;
			addErroMessage(null, getBunde("erro_msg"), getBunde("cep_invalido"));
		}
		
		if(!validaTEL(usuarioCadastro.getTelefone1())){
			ret = false;
			addErroMessage(null, getBunde("erro_msg"), getBunde("telefone_invalido"));
		}
		
		if(!validaTEL(usuarioCadastro.getTelefone2())){
			ret = false;
			addErroMessage(null, getBunde("erro_msg"), getBunde("celular_invalido"));
		}
		
		return ret;
	}

}
