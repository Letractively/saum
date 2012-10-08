package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.Servidor;
import br.com.meganet.infra.BaseHibernateDAO;

public class ServidorDAO extends BaseHibernateDAO<Servidor> {
	// property constants
	public static final String SERVIDOR_NOME = "servidorNome";
	public static final String SERVIDOR_ID = "servidorId";
	public static final String SERVIDOR_DESCRICAO = "servidorDescricao";
	public static final String SERVIDOR_LOCAL = "servidorLocal";
	public static final String SERVIDOR = "servidor";
	public static final String SERVIDOR_DESATIVADO = "servidorDesativado";

	public void save(Servidor transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Servidor persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Servidor findById(java.lang.Long id) {
		try {
			Servidor instance = (Servidor) getSession().get(LOCAL_VO + "Servidor", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Servidor> findByExample(Servidor instance) {
		try {
			List<Servidor> results = getSession().createCriteria(LOCAL_VO + "Servidor")
					.add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Servidor> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Servidor as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Servidor> findAll() {
		try {
			String queryString = "from Servidor";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(Servidor instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(Servidor instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<Servidor> getPojoClass() {
		return Servidor.class;
	}
}