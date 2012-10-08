package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.Avisos;
import br.com.meganet.infra.BaseHibernateDAO;

public class AvisosDAO extends BaseHibernateDAO<Avisos> {
	// property constants
	public static final String AVISOS_AVISO = "avisosAviso";
	public static final String AVISOS_ATIVO = "avisosAtivo";
	public static final String AVISOS_DATA_EXPIRACAO = "avisosDataExpiracao";
	public static final String AVISOS_TITULO = "avisosTitulo";

	public void save(Avisos transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Avisos persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Avisos findById(java.lang.Long id) {
		try {
			Avisos instance = (Avisos) getSession().get(LOCAL_VO + "Avisos", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Avisos> findByExample(Avisos instance) {
		try {
			List<Avisos> results = getSession().createCriteria(LOCAL_VO + "Avisos").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Avisos> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Avisos as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Avisos> findAll() {
		try {
			String queryString = "from Avisos";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(Avisos instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(Avisos instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<Avisos> getPojoClass() {
		return Avisos.class;
	}
}