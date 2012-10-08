package br.com.meganet.bo;

import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.ServidorDAO;
import br.com.meganet.hbm.DAO.TorreDAO;
import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.hbm.vo.Torre;

public class TorreBO {
	
	private TorreDAO torreDAO;
	private ServidorDAO servidorDAO;
	public void setTorreDAO(TorreDAO torreDAO) {
		this.torreDAO = torreDAO;
	}
	public void setServidorDAO(ServidorDAO servidorDAO) {
		this.servidorDAO = servidorDAO;
	}

	public Collection<Torre> carregaComboTorre() throws Exception{
		return torreDAO.findByCriteria(
			Expression.eq(TorreDAO.TORRE_DESTAIVADO, false));
	}

	public Collection<Torre> carregaComboTodasTorre() throws Exception{
		return torreDAO.findAll();
	}
	
	public Collection<Servidor> carregaComboTodosServidores() throws Exception{
		return servidorDAO.findAll();
	}
	
	public Collection<Servidor> carregaComboServidoresAtivos() throws Exception{
		return servidorDAO.findByCriteria(Order.asc(ServidorDAO.SERVIDOR_NOME), Expression.eq(ServidorDAO.SERVIDOR_DESATIVADO, false));
	}
	
	public Torre findByID(Long torreId) {
		return torreDAO.findById(torreId);
	}

	public Servidor findServidorByID(Long serv) {
		return servidorDAO.findById(serv);
	}
	
	public int salvaTorre(Torre t) throws Exception{
		t.setTorreInterfaceCliente(false);
		torreDAO.attachDirty(t);
		return 0;
	}

	public int salvaServidor(Servidor t) throws Exception{
		servidorDAO.attachDirty(t);
		return 0;
	}
	public List<Torre> carregaComboTorre(Long servidorID) throws GAPBDException {
		Servidor serv = servidorDAO.findById(servidorID);
		Object[] o = serv.getServidorTorres().split(",");
		Long[] ids = new Long[o.length];
		for (int i = 0; i < o.length; i++) {
			ids[i] = new Long(o[i].toString());
		}
		List<Torre> torres = torreDAO.findByCriteria(Order.asc(TorreDAO.TORRE_NOME), Expression.in(TorreDAO.TORRE_ID, ids));

		return torres;
	}
	
}
