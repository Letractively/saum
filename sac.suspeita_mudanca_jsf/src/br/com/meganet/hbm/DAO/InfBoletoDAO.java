package br.com.meganet.hbm.DAO;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import br.com.meganet.hbm.vo.InfBoleto;
import br.com.meganet.infra.BaseHibernateDAO;

public class InfBoletoDAO extends BaseHibernateDAO<InfBoleto> {

	// property constants
	public static final String INFBOLETO_LOCAL_PAGAMENTO_LN1 = "infboletoLocalPagamentoLn1";
	public static final String INFBOLETO_INSTRUCAO1 = "infboletoInstrucao1";
	public static final String INFBOLETO_INSTRUCAO2 = "infboletoInstrucao2";
	public static final String INFBOLETO_INSTRUCAO3 = "infboletoInstrucao3";
	public static final String INFBOLETO_AGENCIA = "infboletoAgencia";
	public static final String INFBOLETO_CONTA = "infboletoConta";
	public static final String INFBOLETO_NOME = "infboletoNome";
	public static final String INFBOLETO_CNPJ = "infboletoCnpj";
	public static final String INFBOLETO_CARTEIRA = "infboletoCarteira";
	public static final String INFBOLETO_BANCO = "infboletoBanco";
	public static final String INFBOLETO_SEN = "infboletoSen";
	public static final String INFBOLETO_USR = "infboletoUsr";
	public static final String INFBOLETO_NUMERO_CONVENIO = "infboletoNumeroConvenio";
	
	public static final String INFBOLETO_NUMERO_BOLETO_POS_INI = "infboletoNumeroboletoPosIni";
	public static final String INFBOLETO_NUMERO_BOLETO_POS_FIM = "infboletoNumeroboletoPosFim";
	public static final String INFBOLETO_VALORPAGOAOBANCO_POS_INI = "infboletoValorpagoaobancoPosIni";
	public static final String INFBOLETO_VALORPAGOAOBANCO_POS_FIM = "infboletoValorpagoaobancoPosFim";
	public static final String INFBOLETO_VALORCREDITADO_POS_INI = "infboletoValorcreditadoPosIni";
	public static final String INFBOLETO_VALORCREDITADO_POS_FIM = "infboletoValorcreditadoPosFim";
	public static final String INFBOLETO_DATAPAGAMENTO_POS_INI = "infboletoDatapagamentoPosIni";
	public static final String INFBOLETO_DATAPAGAMENTO_POS_FIM = "infboletoDatapagamentoPosFim";
	public static final String INFBOLETO_DATAPAGAMENTO_MASCARA = "infboletoDatapagamentoMascara";

	public static final String INFBOLETO_CODIGO_CLIENTE = "infboletoCodigoCliente";
	public static final String INFBOLETO_CODIGO_FORNECIDO_AGENCIA_DV = "infboletoCodigoFornecidoAgenciaDV";
	public static final String INFBOLETO_CODIGO_FORNECIDO_AGENCIA = "infboletoCodigoFornecidoAgencia";
	public static final String INFBOLETO_CODIGO_OPERACAO = "infboletoCodigoOperacao";

	public void save(InfBoleto transientInstance) {
		try {
			getSession().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void delete(InfBoleto persistentInstance) {
		try {
			getSession().delete(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public InfBoleto findById(java.lang.Long id) {
		try {
			InfBoleto instance = (InfBoleto) getSession().get(LOCAL_VO + "InfBoleto", id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InfBoleto> findByExample(InfBoleto instance) {
		try {
			List<InfBoleto> results = getSession().createCriteria(LOCAL_VO + "InfBoleto").add(Example.create(instance)).list();
			return results;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InfBoleto> findByProperty(String propertyName, Object value) {
		try {
			String queryString = "from InfBoleto as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<InfBoleto> findAll() {
		try {
			String queryString = "from InfBoleto";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachDirty(InfBoleto instance) {
		try {
			getSession().saveOrUpdate(instance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void attachClean(InfBoleto instance) {
		try {
			getSession().lock(instance, LockMode.NONE);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	protected Class<InfBoleto> getPojoClass() {
		return InfBoleto.class;
	}
}