package br.com.meganet.hbm.DAO;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.InfServidor;
import br.com.meganet.infra.BaseHibernateDAO;

public class InfServidorDAO extends BaseHibernateDAO<InfServidor> {

	public static final String INFSERVIDOR_ID = "infservidorId";
	public static final String INFSERVIDOR_TORRE = "torre";
	public static final String INFSERVIDOR_DATA = "infservidorData";
	public static final String INFSERVIDOR_DOWNLOAD = "infservidorDownload";
	public static final String INFSERVIDOR_UPLOAD = "infservidorUpload";

	public void save(InfServidor transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(InfServidor persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public InfServidor findById(java.lang.Long id) {
		try {
			InfServidor instance = (InfServidor) getSession().get(LOCAL_VO + "InfServidor", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InfServidor> findByExample(InfServidor instance) {
		try {
			List<InfServidor> results = getSession().createCriteria(LOCAL_VO + "InfServidor").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InfServidor> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from InfServidor as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InfServidor> findAll() {
		try {
			String queryString = "from InfServidor";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public InfServidor mergeObjeto(InfServidor detachedInstance) {
		try {
			InfServidor result = (InfServidor) getSession().merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(InfServidor instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(InfServidor instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<InfServidor> getPojoClass() {
		return InfServidor.class;
	}

	public void deletePorData(Timestamp timestamp) {
		try {
			String queryString = "delete from InfServidor where infservidor_data < ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, timestamp);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}