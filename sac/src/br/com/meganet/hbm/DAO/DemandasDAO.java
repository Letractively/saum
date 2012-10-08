package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.Demandas;
import br.com.meganet.infra.BaseHibernateDAO;

public class DemandasDAO extends BaseHibernateDAO<Demandas> {

	// property constants
	public static final String DEMANDAS_DESCRICAO = "demandasDescricao";
	public static final String DEMANDAS_DESCRICAO_ENCERRAMENTO = "demandasDescricaoEncerramento";
	public static final String USUARIO_RESPONSAVEL = "usuarioResponsavel";
	public static final String USUARIO_SOLICITANTE = "usuarioSolicitante";
	public static final String USUARIO_ABRIU = "usuarioAbriu";
	public static final String USUARIO_FECHOU = "usuarioFechou";
	public static final String DATA_ABERTURA = "demandasDataAbertura"; 
	public static final String DATA_ENCERRAMENTO = "demandasDataEncerramento";
	public static final String TIPO_ERRO = "demandasTipoErro";
	public static final String DATA_PREVISTO_ATENDIMENTO = "demandasDataPrevistaAtendimento"; 

	public void save(Demandas transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(Demandas persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Demandas findById(java.lang.Long id) {
		try {
			Demandas instance = (Demandas) getSession().get(
					LOCAL_VO + "Demandas", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Demandas> findByExample(Demandas instance) {
		try {
			List<Demandas> results = getSession().createCriteria(
					LOCAL_VO + "Demandas")
					.add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Demandas> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from Demandas as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Demandas> findAll() {
		try {
			String queryString = "from Demandas";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(Demandas instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(Demandas instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public Class<Demandas> getPojoClass() {
		return Demandas.class;
	}
}