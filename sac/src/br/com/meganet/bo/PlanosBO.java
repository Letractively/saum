package br.com.meganet.bo;

import java.util.Collection;

import br.com.meganet.hbm.DAO.PlanosPacotesDAO;
import br.com.meganet.hbm.DAO.UsuarioDAO;
import br.com.meganet.hbm.vo.PlanosPacotes;
import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.util.UtilNumero;

public class PlanosBO {
	
	private PlanosPacotesDAO planosPacotesDAO;
	private UsuarioDAO usuarioDAO;

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	public void setPlanosPacotesDAO(PlanosPacotesDAO planosPacotesDAO) {
		this.planosPacotesDAO = planosPacotesDAO;
	}

	public Collection<PlanosPacotes> carregaComboPlano() {
		return planosPacotesDAO.findByProperty(PlanosPacotesDAO.PLANOSPACOTES_ATIVADO, true);
	}

	public PlanosPacotes carregaPlanoParaAlteracao(Long id) {
		return planosPacotesDAO.findById(id);
	}

	public boolean gravaPlanoPacote(PlanosPacotes planosPacotes) {
		planosPacotesDAO.attachDirty(planosPacotes);
		return true;
	}

	public Collection<PlanosPacotes> carregaComboPlanoParaAlteracao() {
		return planosPacotesDAO.findAll();
	}

	public PlanosPacotes procurarPorID(Long planospacotesId) {
		return planosPacotesDAO.findById(planospacotesId);
	}

	public PlanosPacotes verificarPlanoCliente(long idCLiente) {
		Usuario usr = usuarioDAO.findById(idCLiente);
		PlanosPacotes pp = usr.getPlanosPacotes();
		pp.setValor(UtilNumero.formataNumeroCasaDecimal(pp.getPlanospacotesValor()));
		return usr.getPlanosPacotes();
	}

}
