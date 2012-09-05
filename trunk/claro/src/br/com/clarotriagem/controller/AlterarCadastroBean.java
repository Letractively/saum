package br.com.clarotriagem.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.clarotriagem.entitades.Cliente;
import br.com.clarotriagem.entitades.Perfil;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.PerfilService;
import br.com.clarotriagem.service.UsuarioService;
import br.com.clarotriagem.utils.singleton.mapas.ConfigUtil;

@ManagedBean
@Scope("view")
@Component
public class AlterarCadastroBean extends BaseBean {

	private static final long serialVersionUID = 1276733221041842031L;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	private UsuarioIdentificacao usuarioCadastro;
	private String termoBusca;
	
	private List<Cliente> clientesPai;
	private SortedMap<String, String> estados;
	private SortedMap<String, Long> perfis;
	
	public AlterarCadastroBean(){
		super();
	}
	public List<String> buscaNomes(String query){
		List<String> ret = usuarioService.buscaUsuariosAtivos(query);
		return ret;
	}
	
	public void usuarioBuscaManual(){
		usuarioCadastro = usuarioService.buscaUsuario(termoBusca);
	}
	public void usuarioSelecionado(SelectEvent event){
		usuarioCadastro = usuarioService.buscaUsuario(termoBusca);
	}
	
	public SortedMap<String, Long> getPerfis() {
		if(perfis == null){
			List<Perfil> p = perfilService.buscaPerfisAtivados();
			perfis = new TreeMap<String, Long>();
			for (Iterator<Perfil> iterator = p.iterator(); iterator.hasNext();) {
				Perfil perfil = (Perfil) iterator.next();
				perfis.put(perfil.getNome(), perfil.getPerfilId());
			}
		}
		return perfis;
	}
	public String alterar(){
		if(usuarioCadastro != null && usuarioCadastro.getId() != null && usuarioCadastro.getId() >0){
			UsuarioIdentificacao ret = usuarioService.alterarUsuario(usuarioCadastro);
			if(ret != null){
				addInfoMessage(getBunde("alterado_sucesso"));
				usuarioCadastro = null;
				termoBusca = null;
			}
		}else{
			addErroMessage(null, getBunde("erro_msg"), getBunde("usuario_nao_pode_ser_alterad"));
		}
		return null;
	}
	public Map<String, String> getEstados() {
		if(estados == null){
			String est[] = ConfigUtil.getInstance().getProperty("estados", "DF-Distrito Federal").split(";");
			estados = new TreeMap<String, String>();
			for (int i = 0; i < est.length; i++) {
				String t[] = est[i].split("-");
				estados.put(t[1], t[0]);
			}
		}
		return estados;
	}

	public List<Cliente> getClientesPai() {
		if(clientesPai == null){
			
		}
		return clientesPai;
	}

	public UsuarioIdentificacao getUsuarioCadastro() {
		if(usuarioCadastro == null){
			usuarioCadastro = new UsuarioIdentificacao();
		}
		return usuarioCadastro;
	}
	
	public void setUsuarioCadastro(UsuarioIdentificacao usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}
	public void setEstados(SortedMap<String, String> estados) {
		this.estados = estados;
	}
	public void setPerfis(SortedMap<String, Long> perfis) {
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
	public String getTermoBusca() {
		return termoBusca;
	}
	public void setTermoBusca(String termoBusca) {
		this.termoBusca = termoBusca;
	}
	public void setClientesPai(List<Cliente> clientesPai) {
		this.clientesPai = clientesPai;
	}
}
