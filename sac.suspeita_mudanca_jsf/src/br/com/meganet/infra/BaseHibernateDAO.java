package br.com.meganet.infra;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import br.com.meganet.exception.GAPBDException;


public abstract class BaseHibernateDAO<E> extends HibernateDaoSupport implements DAO<E> {
	
	public static final String LOCAL_VO = "br.com.meganet.hbm.vo.";
	
	public void delete(E pojo) throws GAPBDException {
		try {
			getHibernateTemplate().delete(pojo);
		} catch (DataAccessException e) {
			throw new GAPBDException("errors.bd.exception", new String[] { e.getMessage() });
		}
	}

	public List<E> findByCriteria(Criterion ... criteria) throws GAPBDException {
		return findByCriteria(null, criteria);
	}
	public List<E> findByCriteria(int maxResult, Criterion ... criteria) throws GAPBDException {
		return findByCriteria(maxResult, null, criteria);
	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(Order order, Criterion ... criteria) throws GAPBDException {
		DetachedCriteria findCriteria = DetachedCriteria.forClass(getPojoClass());
		if (order != null) findCriteria.addOrder(order);
		for (Criterion criterion : criteria) findCriteria.add(criterion);
		try {
			return getHibernateTemplate().findByCriteria(findCriteria);
		} catch (DataAccessException e) {
			throw new GAPBDException("errors.bd.exception", new String[] { e.getMessage() });
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> findByCriteria(int maxResult, Order order, Criterion ... criteria) throws GAPBDException {
		DetachedCriteria findCriteria = DetachedCriteria.forClass(getPojoClass());
		if (order != null) findCriteria.addOrder(order);
		for (Criterion criterion : criteria) findCriteria.add(criterion);
		try {
			return getHibernateTemplate().findByCriteria(findCriteria, 0, maxResult);
		} catch (DataAccessException e) {
			throw new GAPBDException("errors.bd.exception", new String[] { e.getMessage() });
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findByDetachedCriteria(DetachedCriteria detachedCriteria) throws GAPBDException {
		try {
			return getHibernateTemplate().findByCriteria(detachedCriteria);
		} catch (DataAccessException e) {
			throw new GAPBDException("errors.bd.exception", new String[] { e.getMessage() });
		}
	}
	
	@SuppressWarnings("unchecked")
	public E get(Serializable id) throws GAPBDException {
		try {
			return (E) getHibernateTemplate().get(getPojoClass(), id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GAPBDException("errors.bd.exception", new String[] { e.getMessage() });
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> listAll() throws GAPBDException {
		try {
			return getHibernateTemplate().loadAll(getPojoClass());
		} catch (Exception e) {
			throw new GAPBDException("errors.bd.exception", new String[] { e.getMessage() });
		}
	}

	public List<E> listAll(String orderByProperty, boolean ascending) throws GAPBDException {
		return findByCriteria(getOrder(orderByProperty, ascending));
	}

	public void merge(E pojo) throws GAPBDException {
		try {
			getHibernateTemplate().refresh(pojo);
		} catch (DataAccessException e) {
			throw new GAPBDException("errors.bd.exception", new String[] { e.getMessage() });
		}
	}

	public void refresh(E pojo) throws GAPBDException {
		try {
			getHibernateTemplate().refresh(pojo);
		} catch (DataAccessException e) {
			throw new GAPBDException("errors.bd.exception", new String[] { e.getMessage() });
		}
	}

	public void save(E pojo) throws GAPBDException {
		try {
			getHibernateTemplate().save(pojo);
		} catch (DataAccessException e) {
			throw new GAPBDException("errors.bd.exception", new String[] { e.getMessage() });
		}
	}

	public void saveOrUpdate(E pojo) throws GAPBDException {
		try {
			getSession().setFlushMode(FlushMode.AUTO);
			getSession().connection().setReadOnly(false);
			getHibernateTemplate().saveOrUpdate(pojo);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new GAPBDException("errors.bd.exception", new String[] { e.getMessage() });
		} catch (HibernateException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(E pojo) throws GAPBDException {
		try {
			getHibernateTemplate().update(pojo);
		} catch (DataAccessException e) {
			throw new GAPBDException("errors.bd.exception", new String[] { e.getMessage() });
		}
	}

	protected Order getOrder(String orderByProperty, boolean ascending) {
		if (ascending) return Order.asc(orderByProperty);
		return Order.desc(orderByProperty);
	}

	protected abstract Class<E> getPojoClass();
}
