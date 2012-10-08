package br.com.meganet.bo;

import java.util.List;

import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.ContratoDAO;
import br.com.meganet.hbm.DAO.UsuarioDAO;
import br.com.meganet.hbm.vo.Contrato;
import br.com.meganet.hbm.vo.Usuario;

public class ContratoBO {
	
	private ContratoDAO contratoDAO;
	private UsuarioDAO usuarioDAO;
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}


	public void setContratoDAO(ContratoDAO contratoDAO) {
		this.contratoDAO = contratoDAO;
	}


	public List<Contrato> buscaContratos() {
		return contratoDAO.findAll();
	}


	public Contrato buscaContrato(Long id) {
		return contratoDAO.findById(id);
	}


	public void salvaContrato(Contrato contrato) throws GAPBDException{
		contratoDAO.attachDirty(contrato);
	}


	public Contrato getContratoPeloCliente(long idCliente) {
		Usuario usr = usuarioDAO.findById(idCliente);
		Contrato ret = contratoDAO.findById(usr.getContrato().getContratoId());
		return ret;
	}

}
