package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.Dominios;
import br.com.meganet.infra.BaseHibernateDAO;

public class DominiosDAO extends BaseHibernateDAO<Dominios> {

	// property constants
	public static final String CHAVE = "dominiosChave";
	public static final String VALOR = "dominiosValor";
	public static final String TIPO = "dominiosTipo";
	public static final String DESCRICAO = "dominiosDescricao";
	public static final String TRATAMENTO_ESPECIAL = "dominiosTratamentoEspecial";

	public void save(Dominios transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Dominios persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Dominios findById(java.lang.Long id) {
		try {
			Dominios instance = (Dominios) getSession().get(
					LOCAL_VO + "Dominios", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Dominios> findByExample(Dominios instance) {
		try {
			List<Dominios> results = getSession().createCriteria(
					LOCAL_VO + "Dominios")
					.add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Dominios> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Dominios as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Dominios> findAll() {
		try {
			String queryString = "from Dominios";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(Dominios instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(Dominios instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public Class<Dominios> getPojoClass() {
		return Dominios.class;
	}
}