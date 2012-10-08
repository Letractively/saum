package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.BoletosGerados;
import br.com.meganet.infra.BaseHibernateDAO;

public class BoletosGeradosDAO extends BaseHibernateDAO<BoletosGerados> {
	// property constants
	public static final String BOLETOSGERADOS_VALOR = "boletosgeradosValor";
	public static final String BOLETOSGERADOS_VALOR_PAGO = "boletosgeradosValorPago";
	public static final String BOLETOSGERADOS_ID = "boletosgeradosId";
	public static final String BOLETOSGERADOS_ID_F2B = "boletosgeradosIdF2B";
	public static final String BOLETOSGERADOS_USUARIO = "usuario";
	public static final String BOLETOSGERADOS_DATA_PAGAMENTO = "boletosgeradosDataPagamento";
	public static final String BOLETOSGERADOS_PROCESSAMENTO = "boletosgeradosProcessamento";
	public static final String BOLETOSGERADOS_DATA_VENCIMENTO = "boletosgeradosDataVencimento";
	public static final String BOLETOSGERADOS_DATA_VENCIMENTO_PRORROGADO = "boletosgeradosDataVencimentoProrrogado";
	public static final String BOLETOSGERADOS_PAGO = "boletosgeradosPago";
	public static final String BOLETOSGERADOS_PAGOU_EM_MAO = "boletosgeradosPagouEmMao";
	public static final String BOLETOSGERADOS_BOLETO_ENTREGUE_ADM_CONTA = "boletosgeradosDinheiroEntregueAdministradorConta";
	public static final String BOLETOSGERADOS_URL_BOLETO_F2B = "boletosgeradosUrlBoletoF2b";
	public static final String BOLETOSGERADOS_TIPO_ENVIO_F2B = "boletosgeradosTipoEnvioF2B";
	public static final String BOLETOSGERADOS_BOLETO_EXTRA = "boletosgeradosBoletoExtra";
	public static final String BOLETOSGERADOS_MULTA = "boletosgeradosMulta";
	public static final String BOLETOSGERADOS_JUROS = "boletosgeradosJuros";
	public static final String BOLETOSGERADOS_DESCONTO = "boletosgeradosDesconto";
	public static final String BOLETOSGERADOS_LIMITE_DESCONTO = "boletosgeradosLimiteDesconto";

	public void save(BoletosGerados transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(BoletosGerados persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public BoletosGerados findById(java.lang.Long id) {
		try {
			BoletosGerados instance = (BoletosGerados) getSession().get(
					LOCAL_VO + "BoletosGerados", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<BoletosGerados> findByExample(BoletosGerados instance) {
		try {
			List<BoletosGerados> results = getSession().createCriteria(
					LOCAL_VO + "BoletosGerados").add(
					Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<BoletosGerados> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from BoletosGerados as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public int eliminaIdF2B(){
		try{
			String queryString = "update BoletosGerados as model set " +
					"model." + BOLETOSGERADOS_URL_BOLETO_F2B + " = null, " +
					"model." + BOLETOSGERADOS_ID_F2B + " = null";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<BoletosGerados> findAll() {
		try {
			String queryString = "from BoletosGerados";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(BoletosGerados instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void merge(BoletosGerados instance) {
		try {
			getSession().merge(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public void attachClean(BoletosGerados instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public Class<BoletosGerados> getPojoClass() {
		return BoletosGerados.class;
	}

}