package br.com.meganet.hbm.DAO;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.StatusCliente;
import br.com.meganet.infra.BaseHibernateDAO;

public class StatusClienteDAO extends BaseHibernateDAO<StatusCliente> {

	public static final String STATUSCLIENTE_TXRATE = "statusclienteTxrate";
	public static final String STATUSCLIENTE_RXRATE = "statusclienteRxrate";
	public static final String STATUSCLIENTE_TXBYTES = "statusclienteTxbytes";
	public static final String STATUSCLIENTE_RXBYTES = "statusclienteRxbytes";
	public static final String STATUSCLIENTE_SIGNALSTRENGTH = "statusclienteSignalstrength";
	public static final String STATUSCLIENTE_TXCCQ = "statusclienteTxccq";
	public static final String STATUSCLIENTE_THROUGHPUT = "statusclienteThroughput";
	public static final String STATUSCLIENTE_DATAMEDICAO = "statusclienteDatamedicao";
	public static final String USUARIO = "usuario";

	public void save(StatusCliente transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(StatusCliente persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public StatusCliente findById(java.lang.Long id) {
		try {
			StatusCliente instance = (StatusCliente) getSession().get(LOCAL_VO + "StatusCliente", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public void deletePorData(Timestamp timestamp) {
		try {
			String queryString = "delete from StatusCliente where statuscliente_datamedicao < ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, timestamp);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<StatusCliente> findByExample(StatusCliente instance) {
		try {
			List<StatusCliente> results = getSession().createCriteria(LOCAL_VO + "StatusCliente").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<StatusCliente> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from StatusCliente as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<StatusCliente> findAll() {
		try {
			String queryString = "from StatusCliente";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(StatusCliente instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(StatusCliente instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public Class<StatusCliente> getPojoClass() {
		return StatusCliente.class;
	}

}