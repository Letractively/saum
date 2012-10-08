package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.Gasto;
import br.com.meganet.infra.BaseHibernateDAO;

public class GastoDAO extends BaseHibernateDAO<Gasto> {

	public static final String GASTO_ID = "gastoId";
	public static final String GASTO_VALOR = "gastoValor";
	public static final String GASTO_DATA = "gastoData";
	public static final String USUARIO = "usuario";
	public static final String GASTO_MOTIVO = "gastoMotivo";
	public static final String GASTO_DESCRICAO_MOTIVO = "gastoDescricaoMotivo";

	public void save(Gasto transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Gasto persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Gasto findById(java.lang.Long id) {
		try {
			Gasto instance = (Gasto) getSession().get(LOCAL_VO + "Gasto", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Gasto> findByExample(Gasto instance) {
		try {
			List<Gasto> results = getSession().createCriteria(LOCAL_VO + "Gasto").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Gasto> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Gasto as model where model." + propertyName + " = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			List<Gasto> ret = queryObject.list();
			return ret;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Gasto> findAll() {
		try {
			String queryString = "from Usuario";
			Query queryObject = getSession().createQuery(queryString);
			List<Gasto> ret = queryObject.list();
			return ret;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(Gasto instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void merge(Gasto instance) {
		try {
			getSession().merge(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(Gasto instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<Gasto> getPojoClass() {
		return Gasto.class;
	}

}