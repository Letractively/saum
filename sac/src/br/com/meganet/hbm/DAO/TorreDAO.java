package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.Torre;
import br.com.meganet.infra.BaseHibernateDAO;


public class TorreDAO extends BaseHibernateDAO<Torre> {
	// property constants
	public static final String TORRE_NOME = "torreNome";
	public static final String TORRE_ID = "torreId";
	public static final String TORRE_DESCRICAO = "torreDescricao";
	public static final String TORRE_MODELO = "torreModelo";
	public static final String TORRE_PROCESSADOR = "torreProcessador";
	public static final String TORRE_MEMORIA_RAM = "torreMemoriaRam";
	public static final String TORRE_ESPACO_DISCO = "torreEspacoDisco";
	public static final String TORRE_LOCAL = "torreLocal";
	public static final String TORRE_USUARIO = "torreUsuario";
	public static final String TORRE_SENHA = "torreSenha";
	public static final String TORRE_IP_CONEXAO = "torreIpConexao";
	public static final String TORRE_IP_CONEXAO2 = "torreIpConexao2";
	public static final String TORRE_PORTA = "torrePorta";
	public static final String TORRE_INTERFACE_CLIENTE = "torreInterfaceCliente";
	public static final String TORRE_NOME_INTERFACE = "torreNomeInterface";
	public static final String TORRE_DESTAIVADO = "torreDesativado";
	public static final String TORRE_IP_INTERMEDIADOR = "torreIpIntermediador";
	public static final String TORRE_PORTA_INTERMEDIADOR = "torrePortaIntermediador";
	public static final String TORRE_UTILIZAR_INTERMEDIADOR = "torreUtilizarIntermediador";

	public void save(Torre transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Torre persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Torre findById(java.lang.Long id) {
		try {
			Torre instance = (Torre) getSession().get(LOCAL_VO + "Torre", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Torre> findByExample(Torre instance) {
		try {
			List<Torre> results = getSession().createCriteria(LOCAL_VO + "Torre").add(
					Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Torre> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Torre as model where model."	+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Torre> findAll() {
		try {
			String queryString = "from Torre";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(Torre instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(Torre instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<Torre> getPojoClass() {
		return Torre.class;
	}
}