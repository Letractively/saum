package br.com.meganet.bo;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Expression;

import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.UsuarioDAO;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.ConfigUtil;
import br.com.meganet.util.Constantes;
import br.com.meganet.util.Criptografia;

public class LoginBO{
	
	private UsuarioDAO usuarioDAO;
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	
	public Usuario login(Usuario usuario) throws Exception{
		Usuario usr = usuarioDAO.login(usuario);
		return usr;
	}
	public int esqueci(Usuario usuario) throws Exception{
		List<Usuario> usr = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_EMAIL, usuario.getUsuarioEmail().toLowerCase()));
		if(usr != null && usr.size() > 0){
			usuario = usr.get(0);
			
			String senhaCriptografada = Criptografia.encrypt(
					ConfigUtil.getInstance().getProperty("empresa2", "Meganet"), 
					ConfigUtil.getInstance().getProperty("chave_criptografia",Constantes.CHAVE_CRIPTOGRAFIA));

			usuario.setUsuarioSenha(senhaCriptografada);
			long data = new Date(System.currentTimeMillis()).getTime();
			usuario.setUsuarioIdTrocaSenha(data + "");
			usuarioDAO.attachDirty(usuario);
			
			EnviaEmailBO.enviaEMailRecuperacaoSenhaUsuario(usuario, ConfigUtil.getInstance().getProperty("empresa2", "Meganet"));
			return 1;
		}else{
			return 0;
		}
	}
	public Usuario trocaSenha(String senha, String param) throws Exception {
		List<Usuario> usr = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_ID_TROCA_SENHA, param));
		if(usr != null && usr.size() > 0){
			Usuario usuario = usr.get(0);
			
			String senhaCriptografada = Criptografia.encrypt(senha, ConfigUtil.getInstance().getProperty("chave_criptografia",Constantes.CHAVE_CRIPTOGRAFIA));
			
			usuario.setUsuarioSenha(senhaCriptografada);
			usuario.setUsuarioIdTrocaSenha(null);
			usuarioDAO.attachDirty(usuario);
			return usuario;
		}else{
			return null;
		}
	}
	public Usuario loginFB(Usuario usuario) throws GAPBDException {
		List<Usuario> ls = usuarioDAO.findByCriteria(Expression.eq(UsuarioDAO.USUARIO_EMAIL, usuario.getUsuarioEmail()));
		if(ls != null && ls.size() > 0){
			return ls.get(0);
		}else{
			return null;
		}
	}

}
