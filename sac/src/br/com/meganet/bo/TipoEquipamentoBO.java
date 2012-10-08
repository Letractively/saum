package br.com.meganet.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.meganet.hbm.DAO.TipoEquipamentoDAO;
import br.com.meganet.hbm.vo.TipoEquipamento;


public class TipoEquipamentoBO {
	
	private TipoEquipamentoDAO tipoEquipamentoDAO;

	public void setTipoEquipamentoDAO(TipoEquipamentoDAO tipoEquipamentoDAO) {
		this.tipoEquipamentoDAO = tipoEquipamentoDAO;
	}

	public List<TipoEquipamento> carregaFormularioEqp() {
		List<TipoEquipamento> tipos = tipoEquipamentoDAO.findAll();
		List<TipoEquipamento> ret = new ArrayList<TipoEquipamento>();
		for (Iterator<TipoEquipamento> iterator = tipos.iterator(); iterator.hasNext();) {
			TipoEquipamento tipoEquipamento = (TipoEquipamento) iterator.next();
			TipoEquipamento te = new TipoEquipamento();
			te.setTipoEquipamentoDescricao(tipoEquipamento.getTipoEquipamentoDescricao());
			te.setTipoEquipamentoId(tipoEquipamento.getTipoEquipamentoId());
			ret.add(te);
		}
		return ret;
	}

}
