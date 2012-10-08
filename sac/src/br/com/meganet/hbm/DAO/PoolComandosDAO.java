package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.PoolComandos;
import br.com.meganet.infra.BaseHibernateDAO;

public class PoolComandosDAO extends BaseHibernateDAO<PoolComandos> {

	public static final String POOLCOMANDOS_COMANDO = "poolcomandosComando";
	public static final String POOLCOMANDOS_IDENTIFICADOR = "poolcomandosIdentificador";
	public static final String TORRE = "torre";
	public static final String POOLCOMANDOS_TIPO = "poolcomandosTipo";
	public static final String POOLCOMANDOS_ORDEM = "poolcomandosOrdem";
	public static final String SERVIDOR = "servidor";

	public void save(PoolComandos transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(PoolComandos persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public PoolComandos findById(java.lang.Long id) {
		try {
			PoolComandos instance = (PoolComandos) getSession().get(LOCAL_VO + "PoolComandos", id);
			return instance;
		} catch (RuntimeException re) {
			System.out.println(re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PoolComandos> findByExample(PoolComandos instance) {
		try {
			List<PoolComandos> results = getSession().createCriteria(LOCAL_VO + "PoolComandos")
			.add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PoolComandos> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from PoolComandos as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PoolComandos> findAll() {
		try {
			String queryString = "from PoolComandos";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(PoolComandos instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(PoolComandos instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<PoolComandos> getPojoClass() {
		return PoolComandos.class;
	}

	public void deletaComandosPorTipoTorre(List<PoolComandos> comandos) throws Exception {
		try{
			if(comandos != null && comandos.size() > 0){
				PoolComandos pc = comandos.get(0);
				String queryString = "delete PoolComandos pc where pc.servidor.servidorId = "+ pc.getServidor().getServidorId() +
				" and pc." + POOLCOMANDOS_TIPO + " = " + pc.getPoolcomandosTipo();
				getSession().createQuery(queryString)
				.executeUpdate();
			}
		}catch (Exception e) {
			throw e;
		}
	}
	public void deletaComandosPorTipoIdentificadorTorre(PoolComandos comandos) throws Exception {
		try{
				String queryString = "delete PoolComandos pc where pc.torre.torreId = "+ comandos.getTorre().getTorreId() +
				" and pc." + POOLCOMANDOS_TIPO + " = " + comandos.getPoolcomandosTipo() + 
				" and pc." + POOLCOMANDOS_IDENTIFICADOR + " = " + comandos.getPoolcomandosIdentificador();
				getSession().createQuery(queryString)
				.executeUpdate();
		}catch (Exception e) {
			throw e;
		}
	}
}