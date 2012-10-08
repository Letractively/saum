package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.LogMeganet;
import br.com.meganet.infra.BaseHibernateDAO;

public class LogMeganetDAO extends BaseHibernateDAO<LogMeganet> {

	// property constants
	public static final String USUARIO_ID_FK = "usuarioIdFk";
	public static final String LOG_TIPO = "logTipo";
	public static final String LOG_DESCRICAO = "logDescricao";
	public static final String LOG_ID = "logId";
	public static final String LOG_DATA = "logData";
	public static final String LOG_ACAO = "logAcao";

	public void save(LogMeganet transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(LogMeganet persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public LogMeganet findById(java.lang.Long id) {
		try {
			LogMeganet instance = (LogMeganet) getSession().get(LOCAL_VO + "LogMeganet", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<LogMeganet> findByExample(LogMeganet instance) {
		try {
			List<LogMeganet> results = getSession().createCriteria(LOCAL_VO + "LogMeganet").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<LogMeganet> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from LogMeganet as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<LogMeganet> findAll() {
		try {
			String queryString = "from LogMeganet";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void merge(LogMeganet instance) {
		try {
			getSession().merge(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(LogMeganet instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(LogMeganet instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<LogMeganet> getPojoClass() {
		return LogMeganet.class;
	}
}