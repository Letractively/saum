package br.com.clarotriagem.service.factory;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.clarotriagem.dao.AparelhoMarcaDAO;
import br.com.clarotriagem.dao.AparelhoModeloDAO;
import br.com.clarotriagem.dao.CalendarioDAO;
import br.com.clarotriagem.dao.ClienteDAO;
import br.com.clarotriagem.dao.DominiosDAO;
import br.com.clarotriagem.dao.EmpresaDAO;
import br.com.clarotriagem.dao.MensagemDAO;
import br.com.clarotriagem.dao.MenuDAO;
import br.com.clarotriagem.dao.MenuPerfilDAO;
import br.com.clarotriagem.dao.PerfilDAO;
import br.com.clarotriagem.dao.TriagemLoteDAO;
import br.com.clarotriagem.dao.UsuarioIdentificacaoDAO;

public class ServiceFactory<T> {
	
	@Autowired
	private DominiosDAO dominiosDAO;

	@Autowired
	private AparelhoMarcaDAO aparelhoMarcaDAO;
	
	@Autowired
	private AparelhoModeloDAO aparelhoModeloDAO;
	
	@Autowired
	private TriagemLoteDAO triagemLoteDAO;
	
	@Autowired
	private CalendarioDAO calendarioDAO;
	
	@Autowired
	private ClienteDAO clienteDAO;

	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Autowired
	private MensagemDAO mensagemDAO;
	
	@Autowired
	private MenuDAO menuDAO;
	
	@Autowired
	private MenuPerfilDAO menuPerfilDAO;
	
	@Autowired
	private PerfilDAO perfilDAO;
	
	@Autowired
	private UsuarioIdentificacaoDAO usuarioIdentificacaoDAO;

	public DominiosDAO getDominiosDAO() {
		return dominiosDAO;
	}

	public void setDominiosDAO(DominiosDAO dominiosDAO) {
		this.dominiosDAO = dominiosDAO;
	}

	public CalendarioDAO getCalendarioDAO() {
		return calendarioDAO;
	}

	public void setCalendarioDAO(CalendarioDAO calendarioDAO) {
		this.calendarioDAO = calendarioDAO;
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public EmpresaDAO getEmpresaDAO() {
		return empresaDAO;
	}

	public void setEmpresaDAO(EmpresaDAO empresaDAO) {
		this.empresaDAO = empresaDAO;
	}

	public MensagemDAO getMensagemDAO() {
		return mensagemDAO;
	}

	public void setMensagemDAO(MensagemDAO mensagemDAO) {
		this.mensagemDAO = mensagemDAO;
	}

	public MenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public MenuPerfilDAO getMenuPerfilDAO() {
		return menuPerfilDAO;
	}

	public void setMenuPerfilDAO(MenuPerfilDAO menuPerfilDAO) {
		this.menuPerfilDAO = menuPerfilDAO;
	}

	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}

	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

	public UsuarioIdentificacaoDAO getUsuarioIdentificacaoDAO() {
		return usuarioIdentificacaoDAO;
	}

	public void setUsuarioIdentificacaoDAO(UsuarioIdentificacaoDAO usuarioIdentificacaoDAO) {
		this.usuarioIdentificacaoDAO = usuarioIdentificacaoDAO;
	}

	public AparelhoMarcaDAO getAparelhoMarcaDAO() {
		return aparelhoMarcaDAO;
	}

	public void setAparelhoMarcaDAO(AparelhoMarcaDAO aparelhoMarcaDAO) {
		this.aparelhoMarcaDAO = aparelhoMarcaDAO;
	}

	public AparelhoModeloDAO getAparelhoModeloDAO() {
		return aparelhoModeloDAO;
	}

	public void setAparelhoModeloDAO(AparelhoModeloDAO aparelhoModeloDAO) {
		this.aparelhoModeloDAO = aparelhoModeloDAO;
	}

	public TriagemLoteDAO getTriagemLoteDAO() {
		return triagemLoteDAO;
	}

	public void setTriagemLoteDAO(TriagemLoteDAO triagemLoteDAO) {
		this.triagemLoteDAO = triagemLoteDAO;
	}
	
}
