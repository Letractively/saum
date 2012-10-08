package br.com.meganet.hbm.DAO;

import java.util.Collection;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.Mapa;
import br.com.meganet.infra.BaseHibernateDAO;

public class MapaDAO extends BaseHibernateDAO<Mapa> {

	// property constants
	public static final String MAPA_TOP = "mapaTop";
	public static final String MAPA_LEFT = "mapaLeft";
	public static final String USUARIO = "usuario";
	public static final String MAPA_ID = "mapaId";

	public void save(Mapa transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Mapa persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Mapa findById(java.lang.Long id) {
		try {
			Mapa instance = (Mapa) getSession().get(LOCAL_VO + "Mapa", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Mapa> findByExample(Mapa instance) {
		try {
			List results = getSession().createCriteria(LOCAL_VO + "Mapa").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mapa> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Mapa as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mapa> findAll() {
		try {
			String queryString = "from Mapa";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(Mapa instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(Mapa instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<Mapa> getPojoClass() {
		return Mapa.class;
	}

	@SuppressWarnings("unchecked")
	public Collection<Mapa> buscarMapaPeloServidor(Long idServ) throws Exception{
		String queryString = "from Mapa as m "
			+ "where m." + MapaDAO.USUARIO + "." + UsuarioDAO.SERVIDOR + "." + ServidorDAO.SERVIDOR_ID + " = " + idServ;

		Query queryObject = getSession().createQuery(queryString);
		return queryObject.list();

	}
}