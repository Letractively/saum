package br.com.clarotriagem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.service.factory.ServiceFactory;
import br.com.clarotriagem.utils.Logger;
import br.com.clarotriagem.utils.mail.EnviaEmailBO;

@Service("usuarioService")
@Transactional
public class UsuarioService extends ServiceFactory<UsuarioService>{
	
	private final static Logger log = new Logger(UsuarioService.class);
	
	public UsuarioService(){
		super();
	}
	
	public void save(UsuarioIdentificacao u){
		try {
			getUsuarioIdentificacaoDAO().save(u);
		} catch (Exception e) {
			log.erro(e);
		}
	}
	
	public void apagarUsuarioNaoAutorizado(UsuarioIdentificacao u){
		try {
			getUsuarioIdentificacaoDAO().delete(u, u.getId());
		} catch (Exception e) {
			log.erro(e);
		}
	}
	
	public void desativarUsuario(UsuarioIdentificacao u){
		try {
			u.setAtivo(false);
			getUsuarioIdentificacaoDAO().update(u);
			EnviaEmailBO.enviaEMailUsuarioDesativado(u);
		} catch (Exception e) {
			log.erro(e);
		}
	}
	
	public void ativarUsuario(UsuarioIdentificacao u){
		try {
			u.setAtivo(true);
			getUsuarioIdentificacaoDAO().update(u);
			EnviaEmailBO.enviaEMailUsuarioAtivado(u);
		} catch (Exception e) {
			log.erro(e);
		}
	}
	
	public void autorizarUsuario(UsuarioIdentificacao u){
		try {
			u.setCadastroConfirmado(true);
			getUsuarioIdentificacaoDAO().update(u);
			EnviaEmailBO.enviaEMailCadastroAutorizado(u);
		} catch (Exception e) {
			log.erro(e);
		}
	}
	
	public List<UsuarioIdentificacao> buscaUsuariosNaoAutorizados() {
		try {
			return getUsuarioIdentificacaoDAO().buscaUsuariosNaoAutorizados();
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<UsuarioIdentificacao>();
		}
		
	}

	public SortedMap<String, Long> buscaUsuariosAutorizados() {
		try {
			return getUsuarioIdentificacaoDAO().buscaUsuariosAutorizados();
		} catch (Exception e) {
			log.erro(e);
			return new TreeMap<String, Long>();
		}
		
	}
	
	public List<UsuarioIdentificacao> buscaTodosUsuarios(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder) {
		try {
			return getUsuarioIdentificacaoDAO().buscaTodosUsuarios(startingAt, maxPerPage, sortField, sortOrder);
		} catch (Exception e) {
			log.erro(e);
			return new ArrayList<UsuarioIdentificacao>();
		}
		
	}

	public UsuarioIdentificacao alterarUsuario(UsuarioIdentificacao uc) {
		try {
			return getUsuarioIdentificacaoDAO().update(uc);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}
	
	public Integer getQtdTotalUsuarios() {
		try {
			return getUsuarioIdentificacaoDAO().getQtdUsuarios();
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public List<String> buscaUsuariosAtivos(String email) {
		try {
			return getUsuarioIdentificacaoDAO().buscaUsuariosAtivos(email);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public UsuarioIdentificacao buscaUsuario(String email) {
		try {
			return getUsuarioIdentificacaoDAO().buscaUsuarioAtivo(email);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public UsuarioIdentificacao buscaUsuarioPeloID(Long id) {
		try {
			return getUsuarioIdentificacaoDAO().buscaUsuarioPeloID(id);
		} catch (Exception e) {
			log.erro(e);
			return null;
		}
	}

	public void enviaEMailCadastroUsuario(UsuarioIdentificacao usuarioCadastro) {
		try {
			EnviaEmailBO.enviaEMailCadastroUsuario(usuarioCadastro);
		} catch (Exception e) {
			log.erro(e);
		}
		
	}
	
}
