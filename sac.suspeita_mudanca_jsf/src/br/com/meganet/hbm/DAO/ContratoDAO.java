package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.Contrato;
import br.com.meganet.infra.BaseHibernateDAO;

public class ContratoDAO extends BaseHibernateDAO<Contrato> {

	public static final String CONTRATO_CONTRATO = "contratoContrato";

	public void save(Contrato transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Contrato persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Contrato findById(java.lang.Long id) {
		try {
			Contrato instance = (Contrato) getSession().get(LOCAL_VO + "Contrato", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contrato> findByExample(Contrato instance) {
		try {
			List<Contrato> results = getSession().createCriteria(LOCAL_VO + "Contrato").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contrato> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Contrato as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<Contrato> findByContratoContrato(Object contratoContrato) {
		return findByProperty(CONTRATO_CONTRATO, contratoContrato);
	}

	@SuppressWarnings("unchecked")
	public List<Contrato> findAll() {
		try {
			String queryString = "from Contrato";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(Contrato instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(Contrato instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<Contrato> getPojoClass() {
		return Contrato.class;
	}
}