package br.com.meganet.dwr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.meganet.auditoria.BarraProgresso;
import br.com.meganet.facade.ContratoFacade;
import br.com.meganet.hbm.vo.Contrato;

public class ContratoJS {
	
	private ContratoFacade contratoFacade;
	public void setContratoFacade(ContratoFacade contratoFacade) {
		this.contratoFacade = contratoFacade;
	}

	BarraProgresso bp = new BarraProgresso();
	
	public List<Contrato> buscaContratos(){
		List<Contrato> lc = contratoFacade.buscaContratos();
		List<Contrato> ret = new ArrayList<Contrato>();
		for (Iterator<Contrato> iterator = lc.iterator(); iterator.hasNext();) {
			Contrato ct = (Contrato) iterator.next();
			Contrato c = new Contrato();
			c.setContratoId(ct.getContratoId());
			c.setContratoNome(ct.getContratoNome());
			ret.add(c);
		}
		return ret;
	}
	
	public Contrato buscaContrato(Long id){
		Contrato lc = contratoFacade.buscaContrato(id);
		Contrato ret = new Contrato();
		ret.setContratoId(lc.getContratoId());
		ret.setContratoNome(lc.getContratoNome());
		ret.setContratoContrato(lc.getContratoContrato());
		return ret;

	}
	
	public boolean salvaContrato(Contrato contrato){
		return contratoFacade.salvaContrato(contrato);
	}
	
}
