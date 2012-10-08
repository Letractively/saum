package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.VelocidadeMedia;
import br.com.meganet.infra.BaseHibernateDAO;

public class VelocidadeMediaDAO extends BaseHibernateDAO<VelocidadeMedia> {
	public static final String VELOCIDADEMEDIA_VELOCIDADE = "velocidademediaVelocidade";

	public void save(VelocidadeMedia transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(VelocidadeMedia persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public VelocidadeMedia findById(java.lang.Long id) {
		try {
			VelocidadeMedia instance = (VelocidadeMedia) getSession().get(LOCAL_VO + "VelocidadeMedia", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

    @SuppressWarnings("unchecked")
	public List<VelocidadeMedia> findByExample(VelocidadeMedia instance) {
		try {
			List<VelocidadeMedia> results = getSession().createCriteria(LOCAL_VO + "VelocidadeMedia").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

    @SuppressWarnings("unchecked")
	public List<VelocidadeMedia> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from VelocidadeMedia as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

    @SuppressWarnings("unchecked")
	public List<VelocidadeMedia> findAll() {
		try {
			String queryString = "from VelocidadeMedia";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(VelocidadeMedia instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(VelocidadeMedia instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<VelocidadeMedia> getPojoClass() {
		return VelocidadeMedia.class;
	}
}