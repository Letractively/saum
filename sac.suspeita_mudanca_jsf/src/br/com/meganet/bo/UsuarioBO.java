package br.com.meganet.bo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.PropertyValueException;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.UsuarioDAO;
import br.com.meganet.hbm.vo.Avisos;
import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.Criptografia;
import br.com.meganet.util.DateUtils;
import br.com.meganet.util.Util;

public class UsuarioBO {
	
	private UsuarioDAO usuarioDAO;
	private BoletoBO boletoBO;
	public void setBoletoBO(BoletoBO boletoBO) {
		this.boletoBO = boletoBO;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public List<Usuario> buscaUsuarios() throws GAPBDException{
		return usuarioDAO.findByCriteria(
				Expression.isNotNull(UsuarioDAO.USUARIO_MENU),
				Expression.isNotNull(UsuarioDAO.USUARIO_ENDERECO));
	}
	
	public Collection<Usuario> buscaUsuarios(String cliente) throws GAPBDException{
		boolean ehCPF = false;
		if(cliente .indexOf(".") > -1 && cliente.indexOf("-") > -1){
			ehCPF = true;
		}
		try{
			if(ehCPF){
				return usuarioDAO.findByProperty(UsuarioDAO.USUARIO_CPF, cliente);
			}else{
				return usuarioDAO.buscaUsuarios(cliente);
			}
		}catch (IndexOutOfBoundsException e) {
			return new ArrayList<Usuario>();
		}
	}
	
	public Collection<Usuario> buscaClientes() throws GAPBDException{
		return usuarioDAO.findByCriteria(
				Order.asc(UsuarioDAO.USUARIO_NOME),
				Expression.isNotNull(UsuarioDAO.USUARIO_TELEFONE1),
				Expression.isNotNull(UsuarioDAO.USUARIO_TELEFONE2),
				Expression.isNotNull(UsuarioDAO.USUARIO_ENDERECO),
				Expression.isNotNull(UsuarioDAO.USUARIO_CEP),
				Expression.isNotNull(UsuarioDAO.SERVIDOR),
				Expression.ne(UsuarioDAO.USUARIO_BLOQUEADO, Constantes.USR_DESATIVADO),
				Expression.isNotNull(UsuarioDAO.USUARIO_CPF)
				);
	}
	
	public Collection<Usuario> buscaClientesSemLocalizacao() throws GAPBDException{
		return usuarioDAO.buscaClientesSemLocalizacao();
	}
	
	public Collection<Usuario> buscaClientesComboLocalizacao() throws GAPBDException{
		return usuarioDAO.buscaClientesComboLocalizacao();
	}
	
	public Collection<Usuario> buscaClientesComboLocalizacaoGoogle() throws GAPBDException{
		return usuarioDAO.buscaClientesComboLocalizacaoGoogle();
	}
	
	public Collection<Usuario> buscaTodosUsuarios() {
		return usuarioDAO.findAll();
	}

	public void insereUsuario(Usuario usuario) throws DataIntegrityViolationException, PropertyValueException, Exception {
		Usuario usrTeste = this.buscaUsuario(usuario.getUsuarioMac());
		if(usrTeste != null && usrTeste.getUsuarioId() != null){
			usrTeste.setUsuarioMac(null);
			usuarioDAO.attachDirty(usrTeste);
		}
		usuario.setUsuarioNome(usuario.getUsuarioNome().toUpperCase());
		if(usuario.getUsuarioEmail() != null){
			usuario.setUsuarioEmail(usuario.getUsuarioEmail().toLowerCase());
		}
		usuarioDAO.attachDirty(usuario);
	}

	public void alteraUsuario(Usuario usuario) throws DataIntegrityViolationException, PropertyValueException, Exception {
		usuario.setUsuarioNome(usuario.getUsuarioNome().toUpperCase());
		usuario.setUsuarioEndereco(usuario.getUsuarioEndereco().toUpperCase());
		usuario.setUsuarioBairro(usuario.getUsuarioBairro().toUpperCase());
		usuario.setUsuarioCidade(usuario.getUsuarioCidade().toUpperCase());
		usuario.setUsuarioEstado(usuario.getUsuarioEstado().toUpperCase());
		usuario.setUsuarioComplementoEndereco(usuario.getUsuarioComplementoEndereco().toUpperCase());
		if(usuario.getUsuarioEmail() != null){
			usuario.setUsuarioEmail(usuario.getUsuarioEmail().toLowerCase());
		}
		usuarioDAO.merge(usuario);
	}

	public Usuario insereDadosUsuario(Usuario usuario) throws DataIntegrityViolationException, PropertyValueException, Exception {
			if(usuario.getUsuarioMac() != null && !"".equalsIgnoreCase(usuario.getUsuarioMac())){
				Usuario usrTransient = usuarioDAO.findByProperty(UsuarioDAO.USUARIO_MAC, usuario.getUsuarioMac()).get(0);
				usrTransient.setUsuarioCpf(usuario.getUsuarioCpf());
				usrTransient.setUsuarioNome(usuario.getUsuarioNome());
				if(usuario.getUsuarioEmail() != null){
					usrTransient.setUsuarioEmail(usuario.getUsuarioEmail().toLowerCase());
				}else{
					throw new Exception("E-mail não informado.");
				}
				usrTransient.setUsuarioTelefone1(usuario.getUsuarioTelefone1());
				usrTransient.setUsuarioPodePagarMao(false);
				usrTransient.setUsuarioTelefone2(usuario.getUsuarioTelefone2());
				usrTransient.setUsuarioEndereco(usuario.getUsuarioEndereco());
				usrTransient.setUsuarioEqpComodato(usuario.getUsuarioEqpComodato());
				usrTransient.setUsuarioBairro(usuario.getUsuarioBairro().toUpperCase());
				usrTransient.setUsuarioCidade(usuario.getUsuarioCidade().toUpperCase());
				usrTransient.setUsuarioEstado(usuario.getUsuarioEstado().toUpperCase());
				usrTransient.setUsuarioCep(usuario.getUsuarioCep());
				usrTransient.setPlanosPacotes(usuario.getPlanosPacotes());
				
				String senhaCriptografada = Criptografia.encrypt(
						ConfigUtil.getInstance().getProperty("empresa2", "Meganet"),
						ConfigUtil.getInstance().getProperty("chave_criptografia",Constantes.CHAVE_CRIPTOGRAFIA));
				
				usrTransient.setUsuarioSenha(senhaCriptografada);
				usrTransient.setUsuarioDtAtivacao(DateUtils.traduzDataStringToTimeStamp(usuario.getDataTemp()));
				usrTransient.setUsuarioDtPagamento(usuario.getUsuarioDtPagamento());
				usrTransient.setUsuarioMenu(usuario.getUsuarioMenu());
				usrTransient.setUsuarioImprimeBoleto(true);
				usrTransient.setUsuarioAlteraProprioPerfil(false);
				usrTransient.setUsuarioComplementoEndereco(usuario.getUsuarioComplementoEndereco().toUpperCase());
				long data = new Date(System.currentTimeMillis()).getTime();
				usrTransient.setUsuarioIdTrocaSenha(data + "");
				usuario.setUsuarioIdTrocaSenha(data + "");
				usuarioDAO.attachDirty(usrTransient);
				
				EnviaEmailBO.enviaEMailCadastroUsuario(usuario, ConfigUtil.getInstance().getProperty("empresa2", "Meganet"));
				
				return usrTransient;
			}else{
				usuario.setEnderecoIp(null);
				usuario.setPlanosPacotes(null);
				usuario.setContrato(null);
				usuario.setServidor(null);
				usuario.setBoletosGeradoses(null);
				usuario.setUsuarioMac(null);
				usuario.setDemandases(null);
				
				usuario.setUsuarioBairro(usuario.getUsuarioBairro().toUpperCase());
				usuario.setUsuarioCidade(usuario.getUsuarioCidade().toUpperCase());
				usuario.setUsuarioEstado(usuario.getUsuarioEstado().toUpperCase());
				usuario.setUsuarioEqpComodato(usuario.getUsuarioEqpComodato());
				usuario.setUsuarioComplementoEndereco(usuario.getUsuarioComplementoEndereco().toUpperCase());
				
				String senhaCriptografada = Criptografia.encrypt(
						ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
						ConfigUtil.getInstance().getProperty("chave_criptografia",Constantes.CHAVE_CRIPTOGRAFIA));
				
				usuario.setUsuarioSenha(senhaCriptografada);
				long data = new Date(System.currentTimeMillis()).getTime();
				usuario.setUsuarioIdTrocaSenha(data + "");
				usuario.setUsuarioNome(usuario.getUsuarioNome().toUpperCase());
				if(usuario.getUsuarioEmail() != null){
					usuario.setUsuarioEmail(usuario.getUsuarioEmail().toLowerCase());
				}else{
					throw new Exception("E-mail não informado.");
				}
				usuarioDAO.attachDirty(usuario);
				EnviaEmailBO.enviaEMailCadastroUsuario(usuario, ConfigUtil.getInstance().getProperty("empresa2", "Meganet"));
				return usuario;
			}
			
	}
	
	public Usuario buscaUsuario(Long id) {
		return usuarioDAO.findById(id);
	}

	public Usuario buscaUsuarioPeloNome(String cliente) throws Exception{
		Usuario usr = usuarioDAO.findByProperty(UsuarioDAO.USUARIO_NOME, cliente).get(0);
		List<BoletosGerados> boletos = boletoBO.buscaBoletosAtrasados(usr);
		if(boletos != null && boletos.size() > 0){
			usr.setPagamentoAtrasado(true);
		}else{
			usr.setPagamentoAtrasado(false);
		}
		return usr;

	}
	
	public Usuario buscaUsuarioPeloId(Long cliente) throws Exception{
		Usuario usr = usuarioDAO.findById(cliente);
		List<BoletosGerados> boletos = boletoBO.buscaBoletosAtrasados(usr);
		if(boletos != null && boletos.size() > 0){
			usr.setPagamentoAtrasado(true);
		}else{
			usr.setPagamentoAtrasado(false);
		}
		return usr;
		
	}
	
	public Usuario buscaUsuarioPeloCPF(String cliente) throws Exception{
		Usuario usr = usuarioDAO.findByProperty(UsuarioDAO.USUARIO_CPF, cliente).get(0);
		List<BoletosGerados> boletos = boletoBO.buscaBoletosAtrasados(usr);
		if(boletos != null && boletos.size() > 0){
			usr.setPagamentoAtrasado(true);
		}else{
			usr.setPagamentoAtrasado(false);
		}
		return usr;
	}
	
	public Collection<Usuario> carregaComboMAC() throws GAPBDException{
			Collection<Usuario> ret = usuarioDAO.findByCriteria(
					Expression.isNull(UsuarioDAO.USUARIO_TELEFONE1),
					Expression.isNull(UsuarioDAO.USUARIO_TELEFONE2),
					Expression.isNull(UsuarioDAO.USUARIO_ENDERECO),
					Expression.isNull(UsuarioDAO.USUARIO_CEP),
					Expression.isNull(UsuarioDAO.USUARIO_CPF),
					Expression.isNotNull(UsuarioDAO.ENDERECOIP_ID_FK)
					);
			
			return ret;
	}

	public Usuario findByExample(Usuario usuario) {
		try{
			Usuario ret = usuarioDAO.findByExample(usuario).get(0);
			return ret;
		}catch (IndexOutOfBoundsException e) {
			return new Usuario();
		}
	}

	public void deletaUsuario(Usuario usuario) {
		usuarioDAO.delete(usuario);
	}

	public Usuario buscaUsuario(String mac) throws GAPBDException{
		try{
			return usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_MAC, mac)).get(0);
		}catch (IndexOutOfBoundsException e) {
			return new Usuario();
		}
	}
	
	public List<Usuario> buscaSituacaoCliente(boolean asm, boolean acm, boolean blo, boolean des) throws GAPBDException {
		return usuarioDAO.buscaSituacaoCliente(asm, acm, blo, des);
	}
	
	public List<Usuario> buscaTodosClientes() {
		List<Usuario> lista = usuarioDAO.findAll();
		List<Usuario> ret = new ArrayList<Usuario>();
		for (Iterator<Usuario> iterator = lista.iterator(); iterator.hasNext();) {
			Usuario usuario = (Usuario) iterator.next();
			Usuario usr = new Usuario();
			usr.setUsuarioNome(usuario.getUsuarioNome());
			usr.setUsuarioTelefone1(usuario.getUsuarioTelefone1());
			usr.setUsuarioCidade(usuario.getUsuarioCidade());
			usr.setUsuarioDtPagamento(usuario.getUsuarioDtPagamento());
			usr.setUsuarioMac(usuario.getUsuarioMac());
			usr.setUsuarioCpf(usuario.getUsuarioCpf());
			usr.setUsuarioId(usuario.getUsuarioId());
			ret.add(usr);
		}
		return ret;
	}
	public List<Usuario> procuraUsuarioTrocaSenha(String param) throws Exception {
		return usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_ID_TROCA_SENHA, param));
	}
	public BoletosGerados buscaUsuarioPeloBoleto(String boleto) throws IndexOutOfBoundsException, Exception {
		Long id;
		if(boleto.length() > 15){
			id = Util.geraComprovateBoletoReverso(boleto).getBoletosgeradosId();
		}else{
			id = new Long(boleto);
		}
		List<BoletosGerados> bol = boletoBO.getBoletoParaComprovante(id);
		if(bol != null && bol.size() > 0){
			return bol.get(0);
		}else{
			return null;
		}
	}
	public List<Usuario> buscaUsuariosAdministrativos() throws Exception {
		return usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, new Boolean(true)));
	}
	public boolean temAdministrador() throws Exception {
		int qtdAdministrador = usuarioDAO.temAdministrador();
		if(qtdAdministrador > 0){
			return true;
		}else{
			return false;
		}
	}
	public List<Usuario> buscaUsuariosAtivos() throws Exception{
		Object[] o = {Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM, Constantes.USR_ATIVO_BLOQUEADO, Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM};
		return usuarioDAO.findByCriteria(
				Expression.in(UsuarioDAO.USUARIO_BLOQUEADO, o),
				Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, false)
			);
	}
	
	public List<Usuario> buscaUsuariosAtivosPorTorre(Long serv) throws Exception{
		Object[] o = {Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM, Constantes.USR_ATIVO_BLOQUEADO, Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM};
		return usuarioDAO.findByCriteria(
				Expression.in(UsuarioDAO.USUARIO_BLOQUEADO, o),
				Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, false),
				Expression.eq(UsuarioDAO.SERVIDOR, new Servidor(serv))
		);
	}
	public List<Usuario> buscaUsuariosAtivosPorClientes(List<String> usrs) throws Exception{
		Object[] o = {Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM, Constantes.USR_ATIVO_BLOQUEADO, Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM};
		Long[] us = new Long[usrs.size()];
		int i = 0;
		
		for (Iterator<String> iterator = usrs.iterator(); iterator.hasNext();) {
			String id = (String) iterator.next();
			us[i++] = new Long(id);
		}
		return usuarioDAO.findByCriteria(
				Expression.in(UsuarioDAO.USUARIO_BLOQUEADO, o),
				Expression.in(UsuarioDAO.USUARIO_ID, us),
				Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, false)
		);
	}

	public List<Usuario> buscaUsuariosAtivosPorDatapagamento(Object dt)  throws Exception{
		String data = (String) dt;
		Object[] o = {Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM, Constantes.USR_ATIVO_BLOQUEADO, Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM};
		return usuarioDAO.findByCriteria(
				Expression.in(UsuarioDAO.USUARIO_BLOQUEADO, o),
				Expression.eq(UsuarioDAO.USUARIO_DT_PAGAMENTO, new Long(data)),
				Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, false)
		);
	}

	public List<Usuario> buscaUsuariosAtivosPorEstado(Object uf)  throws Exception{
		Object[] o = {Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM, Constantes.USR_ATIVO_BLOQUEADO, Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM};
		return usuarioDAO.findByCriteria(
				Expression.in(UsuarioDAO.USUARIO_BLOQUEADO, o),
				Expression.eq(UsuarioDAO.USUARIO_ESTADO, (String) uf),
				Expression.eq(UsuarioDAO.USUARIO_ADMINISTRATIVO, false)
		);
	}
	public Usuario excluirUsuario(Long usuario) {
		Usuario ret = usuarioDAO.findById(usuario);
		usuarioDAO.delete(ret);
		return ret;
	}
	public List<Avisos> buscaUsuariosMensagemAdvertencia() throws GAPBDException {
		List<Avisos> avisos = new ArrayList<Avisos>();
		List<Usuario> u = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_BLOQUEADO, Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM));
		
		for (Iterator<Usuario> iterator = u.iterator(); iterator.hasNext();) {
			Usuario u2 = (Usuario) iterator.next();
			Avisos avisoTmp = new Avisos();
			avisoTmp.setAvisosAtivo(true);
			avisoTmp.setAvisosTitulo(u2.getUsuarioNome() + " - ID: " + u2.getUsuarioId());
			avisos.add(avisoTmp);
		}
		
		return avisos;
	}
	public List<Avisos> buscaUsuariosBloqueados() throws GAPBDException {
		List<Avisos> avisos = new ArrayList<Avisos>();
		List<Usuario> u = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_BLOQUEADO, Constantes.USR_ATIVO_BLOQUEADO));
		
		for (Iterator<Usuario> iterator = u.iterator(); iterator.hasNext();) {
			Usuario u2 = (Usuario) iterator.next();
			Avisos avisoTmp = new Avisos();
			avisoTmp.setAvisosAtivo(true);
			avisoTmp.setAvisosTitulo(u2.getUsuarioNome() + " - ID: " + u2.getUsuarioId());
			avisos.add(avisoTmp);
		}
		
		return avisos;
	}
}
