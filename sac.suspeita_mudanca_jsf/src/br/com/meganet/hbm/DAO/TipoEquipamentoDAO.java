package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.TipoEquipamento;
import br.com.meganet.infra.BaseHibernateDAO;

public class TipoEquipamentoDAO extends BaseHibernateDAO<TipoEquipamento> {
	// property constants
	public static final String TIPO_EQUIPAMENTO_DESCRICAO = "tipoEquipamentoDescricao";

	public void save(TipoEquipamento transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(TipoEquipamento persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public TipoEquipamento findById(java.lang.Long id) {
		try {
			TipoEquipamento instance = (TipoEquipamento) getSession().get(
					LOCAL_VO + "TipoEquipamento", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TipoEquipamento> findByExample(TipoEquipamento instance) {
		try {
			List<TipoEquipamento> results = getSession().createCriteria(
					LOCAL_VO + "TipoEquipamento").add(
					Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<TipoEquipamento> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from TipoEquipamento as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<TipoEquipamento> findByTipoEquipamentoDescricao(Object tipoEquipamentoDescricao) {
		return findByProperty(TIPO_EQUIPAMENTO_DESCRICAO,
				tipoEquipamentoDescricao);
	}

	@SuppressWarnings("unchecked")
	public List<TipoEquipamento> findAll() {
		try {
			String queryString = "from TipoEquipamento";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(TipoEquipamento instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(TipoEquipamento instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<TipoEquipamento> getPojoClass() {
		return TipoEquipamento.class;
	}
}