package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.Contato;
import br.com.meganet.infra.BaseHibernateDAO;

public class ContatoDAO extends BaseHibernateDAO<Contato> {

	// property constants
	public static final String CONTATO_NOME = "contatoNome";
	public static final String CONTATO_EMAIL = "contatoEmail";
	public static final String CONTATO_END_CR = "contatoEndCr";
	public static final String CONTATO_END_CASA = "contatoEndCasa";
	public static final String CONTATO_TELEFONE = "contatoTelefone";
	public static final String CONTATO_MENSAGEM = "contatoMensagem";
	public static final String CONTATO_TIPO = "contatoTipo";
	public static final String CONTATO_RESPOSTA = "contatoResposta";
	public static final String CONTATO_DATA_ENVIO = "contatoDataEnvio";
	public static final String CONTATO_DATA_RESPOSTA = "contatoDataResposta";

	public void save(Contato transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Contato persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Contato findById(java.lang.Long id) {
		try {
			Contato instance = (Contato) getSession().get(LOCAL_VO + "Contato", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contato> findByExample(Contato instance) {
		try {
			List<Contato> results = getSession().createCriteria(LOCAL_VO + "Contato").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contato> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Contato as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Contato> findAll() {
		try {
			String queryString = "from Contato";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void saveOrUpdate(Contato instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(Contato instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<Contato> getPojoClass() {
		return Contato.class;
	}
}