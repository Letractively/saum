package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.PlanosPacotes;
import br.com.meganet.infra.BaseHibernateDAO;

public class PlanosPacotesDAO extends BaseHibernateDAO<PlanosPacotes> {

	// property constants
	public static final String PLANOSPACOTES_VALOR = "planospacotesValor";
	public static final String PLANOSPACOTES_NOME = "planospacotesNome";
	public static final String PLANOSPACOTES_DESCRICAO = "planospacotesDescricao";
	public static final String PLANOSPACOTES_ATIVADO = "planospacotesAtivado";
	public static final String PLANOSPACOTES_DESCONTO = "planospacotesDesconto";
	public static final String PLANOSPACOTES_LIMITE_DESCONTO = "planospacotesLimiteDesconto";
	public static final String PLANOSPACOTES_MULTA = "planospacotesMulta";
	public static final String PLANOSPACOTES_JUROMES = "planospacotesJuroMes";

	public void save(PlanosPacotes transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(PlanosPacotes persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public PlanosPacotes findById(java.lang.Long id) {
		try {
			PlanosPacotes instance = (PlanosPacotes) getSession().get(
					LOCAL_VO + "PlanosPacotes", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlanosPacotes> findByExample(PlanosPacotes instance) {
		try {
			List<PlanosPacotes> results = getSession().createCriteria(
					LOCAL_VO + "PlanosPacotes").add(
					Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlanosPacotes> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from PlanosPacotes as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlanosPacotes> findAll() {
		try {
			String queryString = "from PlanosPacotes";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(PlanosPacotes instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(PlanosPacotes instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<PlanosPacotes> getPojoClass() {
		return PlanosPacotes.class;
	}
}