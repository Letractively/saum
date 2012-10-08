package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.springframework.dao.DataAccessException;

import br.com.meganet.hbm.vo.Menu;
import br.com.meganet.infra.BaseHibernateDAO;

public class MenuDAO extends BaseHibernateDAO<Menu> {

	// property constants
	public static final String MENU_TEXT = "menuText";
	public static final String MENU_FUNCAO_JS = "menuFuncaoJs";
	public static final String MENU_COLUNA = "menuColuna";
	public static final String MENU_CHECKED = "menuChecked";
	public static final String MENU_ATIVADO = "menuAtivado";
	public static final String MENU_ID = "menuId";

	public void save(Menu transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Menu persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Menu findById(java.lang.Long id) {
		try {
			Menu instance = (Menu) getSession().get(LOCAL_VO + "Menu", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Menu> findByExample(Menu instance) {
		try {
			List<Menu> results = getSession().createCriteria(LOCAL_VO + "Menu").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Menu> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Menu as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Menu> findAll() {
		try {
			String queryString = "from Menu";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(Menu instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(Menu instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Menu> procuraMenusUsuario(String indiceMenu) {
		try {
			String queryString = "from Menu as model where (model.id in (" + indiceMenu + ")" + " or model.menuPublico = true) and model.menuAtivado = 1 order by model.menuText";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Menu> procuraMenusUsuarioSemLogin() {
		try {
			String queryString = "from Menu as model where (model.menuPublico = true) and model.menuAtivado = 1 order by model.menuText";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Menu> procuraMenusUsuarioIphone(String indiceMenu) {
		try {
			String queryString = "from Menu as model where (model.id in (" + indiceMenu + ")" + " and model.menuMostraIphone = true and model.menuAtivado = 1) or (model.menuPublico = true and model.menuMostraIphone = true and model.menuAtivado = 1) order by model.menuText";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Menu> procuraMenusParaAtribuirUsuario() {
		try {
			String queryString = "from Menu as model where model.menuAtivado = 1 and model.menuPublico = false order by model.menuText";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	protected Class<Menu> getPojoClass() {
		return Menu.class;
	}
}