package br.com.meganet.bo;

import java.util.Iterator;
import java.util.List;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

import br.com.meganet.exception.GAPBDException;
import br.com.meganet.hbm.DAO.PoolComandosDAO;
import br.com.meganet.hbm.DAO.TorreDAO;
import br.com.meganet.hbm.vo.PoolComandos;
import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.hbm.vo.Torre;
import br.com.meganet.util.Constantes;


public class PoolComandosBO {
	
	private PoolComandosDAO poolComandosDAO;
	private TorreDAO torreDAO;
	
	public void setPoolComandosDAO(PoolComandosDAO poolComandosDAO) {
		this.poolComandosDAO = poolComandosDAO;
	}
	public void setTorreDAO(TorreDAO torreDAO) {
		this.torreDAO = torreDAO;
	}

	
	public List<PoolComandos> buscaComando(Long identificador, Servidor serv, Long tipo) throws Exception{
		if(serv != null){
			List<PoolComandos> pool = poolComandosDAO.findByCriteria(
					Order.asc(PoolComandosDAO.POOLCOMANDOS_ORDEM),
					Expression.eq(PoolComandosDAO.POOLCOMANDOS_IDENTIFICADOR, identificador),
					Expression.eq(PoolComandosDAO.SERVIDOR, serv),
					Expression.eq(PoolComandosDAO.POOLCOMANDOS_TIPO, tipo));
			if(pool != null && pool.size() > 0){
				return pool;
			}else{
				return null;
			}
			
		}else{
			List<PoolComandos> pool = poolComandosDAO.findByCriteria(
					Order.asc(PoolComandosDAO.POOLCOMANDOS_ORDEM),
					Expression.eq(PoolComandosDAO.POOLCOMANDOS_IDENTIFICADOR, identificador),
					Expression.eq(PoolComandosDAO.POOLCOMANDOS_TIPO, tipo));
			if(pool != null && pool.size() > 0){
				return pool;
			}else{
				return null;
			}
		}
	}

	public PoolComandos buscarComando(Long identificador, Servidor serv, Long tipo) throws Exception{
		List<PoolComandos> pool = poolComandosDAO.findByCriteria(
				Expression.eq(PoolComandosDAO.POOLCOMANDOS_IDENTIFICADOR, identificador),
				Expression.eq(PoolComandosDAO.SERVIDOR, serv),
				Expression.eq(PoolComandosDAO.POOLCOMANDOS_TIPO, tipo));
		if(pool != null && pool.size() > 0){
			return pool.get(0);
		}else{
			return null;
		}
			
	}
	
	public PoolComandos buscaComandoMonitoramentoTorre(Long identificador, Servidor serv, Long tipo) throws Exception{
		List<PoolComandos> pool = poolComandosDAO.findByCriteria(
				Expression.eq(PoolComandosDAO.POOLCOMANDOS_IDENTIFICADOR, identificador),
				Expression.eq(PoolComandosDAO.SERVIDOR, serv),
				Expression.eq(PoolComandosDAO.POOLCOMANDOS_TIPO, tipo));
		if(pool != null && pool.size() > 0){
			return pool.get(0);
		}else{
			return null;
		}
	}
	
	public int gravaAlteracaoComando(List<PoolComandos> comandos) throws Exception {
		poolComandosDAO.deletaComandosPorTipoTorre(comandos);
		for (Iterator<PoolComandos> iterator = comandos.iterator(); iterator.hasNext();) {
			PoolComandos poolComandos = (PoolComandos) iterator.next();
			if(poolComandos.getPoolcomandosComando() != null && !"".equalsIgnoreCase(poolComandos.getPoolcomandosComando())){
				poolComandos.setPoolcomandosIdentificador(0L);
				poolComandosDAO.attachDirty(poolComandos);
			}
		}
		return 0;
	}

	public int gravaAlteracaoComandoMonitoramento(PoolComandos comando) throws Exception {
		comando.setPoolcomandosTipo(Constantes.CMD_GERAL);
		comando.setPoolcomandosOrdem(1);
		poolComandosDAO.deletaComandosPorTipoIdentificadorTorre(comando);
		if(comando.getPoolcomandosComando() != null && !"".equalsIgnoreCase(comando.getPoolcomandosComando())){
			poolComandosDAO.attachDirty(comando);
		}
		return 0;
	}

	public Torre buscarTorrePeloServidorEComando(Long servidorId, String[] torresVinculadas) throws GAPBDException {
		
		Long[] ids = new Long[torresVinculadas.length];
		for (int i = 0; i < torresVinculadas.length; i++) {
			ids[i] = new Long(torresVinculadas[i]);
		}
		List<Torre> torres = torreDAO.findByCriteria(Order.asc(TorreDAO.TORRE_NOME),
				Expression.and(Expression.in(TorreDAO.TORRE_ID, ids), Expression.eq(TorreDAO.TORRE_ID, servidorId)));
		
		if(torres != null && torres.size() > 0){
			return torres.get(0);
		}else{
			return null;
		}
	}

	
}
