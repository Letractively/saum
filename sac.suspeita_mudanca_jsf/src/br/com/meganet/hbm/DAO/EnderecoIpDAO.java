package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.EnderecoIp;
import br.com.meganet.infra.BaseHibernateDAO;

public class EnderecoIpDAO extends BaseHibernateDAO<EnderecoIp> {

	// property constants
	public static final String ENDERECOIP_ENDERECO = "enderecoipEndereco";
	public static final String SERVIDOR = "servidor";
	public static final String ENDERECOIP_ID = "enderecoipId";
	public static final String ENDERECOIP_ATIVADO = "enderecoipAtivado";
	public static final String ENDERECOIP_MAC_CADASTRADO = "enderecoipMacCadastrado";
	public static final String ENDERECOIP_GRUPO = "enderecoipGrupo";
	public static final String ENDERECOIP_MASCARA_SUBREDE = "enderecoipMascaraSubrede";

	public void save(EnderecoIp transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(EnderecoIp persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public EnderecoIp findById(java.lang.Long id) {
		try {
			EnderecoIp instance = (EnderecoIp) getSession().get(LOCAL_VO + "EnderecoIp", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EnderecoIp> findByExample(EnderecoIp instance) {
		try {
			List<EnderecoIp> results = getSession().createCriteria(LOCAL_VO + "EnderecoIp").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EnderecoIp> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from EnderecoIp as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EnderecoIp> findEnderecoIpNaoUsado(Long idServ) {
		try {
			String queryString = "from EnderecoIp as i where " +
					"i." + ENDERECOIP_ID + " not in " + "(select u.enderecoIp." + ENDERECOIP_ID + " from Usuario as u where u.enderecoIp." + ENDERECOIP_ID + " is not null)" + 
					" and i." + SERVIDOR + "." + ServidorDAO.SERVIDOR_ID + " = " + idServ + 
					" and i." + ENDERECOIP_ATIVADO + 
					" = true order by i." + ENDERECOIP_ID;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<EnderecoIp> findAll() {
		try {
			String queryString = "from EnderecoIp";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(EnderecoIp instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(EnderecoIp instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<EnderecoIp> getPojoClass() {
		return EnderecoIp.class;
	}

	@SuppressWarnings("unchecked")
	public List<Object> buscaGrupos(Long combo) {
		try {
			String queryString = "from Usuario as i " + 
				" right join i." + UsuarioDAO.ENDERECOIP_ID_FK + " as u " + " where i." + UsuarioDAO.SERVIDOR +"."+ ServidorDAO.SERVIDOR_ID + "=" + combo + 
				" order by u." + ENDERECOIP_ID;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public int deletePorTorre(Long serv) {
		String queryString = "delete EnderecoIp ip " + " where ip." + SERVIDOR + "." + ServidorDAO.SERVIDOR_ID + " = :id";
		int deletedEntities = getSession().createQuery(queryString).setLong("id", serv).executeUpdate();
		return deletedEntities;
	}
}