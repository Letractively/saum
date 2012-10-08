package br.com.meganet.facade;

import java.util.List;

import br.com.meganet.bo.ContratoBO;
import br.com.meganet.hbm.vo.Contrato;
import br.com.meganet.util.Logger;

public class ContratoFacade {
	
	private Logger log;
    private ContratoBO contratoBO;

	public void setLog(Logger log) {
		this.log = log;
	}
	public void setContratoBO(ContratoBO contratoBO) {
		this.contratoBO = contratoBO;
	}

	public List<Contrato> buscaContratos() {
		return contratoBO.buscaContratos();
	}

	public Contrato buscaContrato(Long id) {
		return contratoBO.buscaContrato(id);
	}

	public boolean salvaContrato(Contrato contrato) {
		try {
			contratoBO.salvaContrato(contrato);
			return true;
		} catch (Exception e) {
			log.erro(e);
			return false;
		}
	}

	public Contrato getContratoPeloCliente(long idCliente) {
		return contratoBO.getContratoPeloCliente(idCliente);
	}

}
