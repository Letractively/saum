package br.com.meganet.facade;

import java.util.List;

import br.com.meganet.bo.ContatoBO;
import br.com.meganet.bo.EnviaEmailBO;
import br.com.meganet.bo.UsuarioBO;
import br.com.meganet.hbm.vo.Contato;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.Logger;

public class ContatoFacade {
	private Logger log;
	private ContatoBO contatoBO;
	private UsuarioBO usuarioBO;
	public void setContatoBO(ContatoBO contatoBO) {
		this.contatoBO = contatoBO;
	}
	public void setUsuarioBO(UsuarioBO usuarioBO) {
		this.usuarioBO = usuarioBO;
	}
	public void setLog(Logger log) {
		this.log = log;
	}

	public int insereContato(Contato contato) {
		try{
			List<Usuario> usrAdm = usuarioBO.buscaUsuariosAdministrativos();
			
			EnviaEmailBO.enviaContato(contato, usrAdm);
			return contatoBO.insereContato(contato);
		}catch (Exception e) {
			log.erro("Erro ao adiquirir usuarios", e);
			return 0;
		}

		
	}


}
