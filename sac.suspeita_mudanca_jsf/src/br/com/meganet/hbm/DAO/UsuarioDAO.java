package br.com.meganet.hbm.DAO;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;
import org.springframework.dao.DataAccessException;

import br.com.meganet.hbm.vo.Usuario;
import br.com.meganet.infra.BaseHibernateDAO;
import br.com.meganet.util.Constantes;

public class UsuarioDAO extends BaseHibernateDAO<Usuario>  {
	//property constants
	public static final String PLANOSPACOTES_ID_FK = "planospacotes";
	public static final String USUARIO_ENVIA_EMAIL_MONETARIO = "usuarioEnviaEmailMonetario";
	public static final String USUARIO_COMPLEMENTO_ENDERECO = "usuarioComplementoEndereco";
	public static final String SERVIDOR = "servidor";
	public static final String ENDERECOIP_ID_FK = "enderecoIp";
	public static final String USUARIO_ID_TROCA_SENHA = "usuarioIdTrocaSenha";
	public static final String USUARIO_NOME = "usuarioNome";
	public static final String USUARIO_SENHA = "usuarioSenha";
	public static final String USUARIO_EQP_COMODATO = "usuarioEqpComodato";
	public static final String USUARIO_ENDERECO = "usuarioEndereco";
	public static final String USUARIO_ULTIMO_ENVIO_EMAIL = "usuarioUltimoEnvioEmail";
	public static final String USUARIO_BAIRO = "usuarioBairro";
	public static final String USUARIO_CIDADE = "usuarioCidade";
	public static final String USUARIO_ESTADO = "usuarioEstado";
	public static final String USUARIO_TELEFONE1 = "usuarioTelefone1";
	public static final String USUARIO_TELEFONE2 = "usuarioTelefone2";
	public static final String USUARIO_EMAIL = "usuarioEmail";
	public static final String USUARIO_VELOCIDADE_DOWN = "usuarioVelocidadeDown";
	public static final String USUARIO_ADMINISTRATIVO = "usuarioAdministrativo";
	public static final String USUARIO_MENU = "usuarioMenu";
	public static final String USUARIO_CEP = "usuarioCep";
	public static final String USUARIO_CPF = "usuarioCpf";
	public static final String USUARIO_IMPRIME_BOLETO = "usuarioImprimeBoleto";
	public static final String USUARIO_MAC = "usuarioMac";
	public static final String USUARIO_DT_PAGAMENTO = "usuarioDtPagamento";
	public static final String USUARIO_BLOQUEADO = "usuarioBloqueado";
	public static final String USUARIO_DATA_LIMITE_DESBLOQUEIO = "usuarioDataLimiteDesbloqueio";
	public static final String USUARIO_PODE_PAGAR_MAO = "usuarioPodePagarMao";
	public static final String USUARIO_ID = "usuarioId";
	public static final String USUARIO_ALTERA_PROPRIO_PERFIL = "usuarioAlteraProprioPerfil";

    public void save(Usuario transientInstance) {
        try {
            getSession().save(transientInstance);
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
	public Usuario login(Usuario usuario) throws Exception {
		try {
			Usuario usr = (Usuario) findByCriteria(
					Expression.eq(USUARIO_EMAIL, usuario.getUsuarioEmail().toLowerCase().trim()),
					Expression.eq(USUARIO_SENHA, usuario.getUsuarioSenha())).get(0);
			return usr;
		} catch (DataAccessException e) {
			throw new Exception(e);
		} catch (RuntimeException re) {
			throw new RuntimeException(re);
		}
	}

	public void delete(Usuario persistentInstance) {
        try {
            getSession().delete(persistentInstance);
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public Usuario findById( java.lang.Long id) {
        try {
            Usuario instance = (Usuario) getSession().get(LOCAL_VO + "Usuario", id);
            return instance;
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    
    @SuppressWarnings("unchecked")
	public List<Usuario> findByExample(Usuario instance) {
        try {
            List<Usuario> results = getSession().createCriteria(LOCAL_VO + "Usuario")
            		.add(Example.create(instance)).list();
            return results;
        } catch (RuntimeException re) {
            throw re;
        }
    }    
    
    @SuppressWarnings("unchecked")
	public List<Usuario> findByProperty(String propertyName, Object value) {
      try {
         String queryString = "from Usuario as model where model." + propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 List<Usuario> ret = queryObject.list();
		 return ret;
      } catch (RuntimeException re) {
         throw re;
      }
	}

    @SuppressWarnings("unchecked")
	public List<Usuario> buscaUsuarios(String cliente) {
    	try {
    		String queryString = "from Usuario as model where upper(sem_acentos(model." + USUARIO_NOME + ")) like upper(sem_acentos('%"+cliente+"%'))";
    		Query queryObject = getSession().createQuery(queryString);
    		List<Usuario> ret = queryObject.list();
    		return ret;
    	} catch (RuntimeException re) {
    		throw re;
    	}
    }
    
	@SuppressWarnings("unchecked")
	public List<Usuario> findAll() {
		try {
			String queryString = "from Usuario";
	         Query queryObject = getSession().createQuery(queryString);
	         List<Usuario> ret = queryObject.list();
			 return ret;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
    public void attachDirty(Usuario instance) {
        try {
            getSession().saveOrUpdate(instance);
        } catch (RuntimeException re) {
            throw re;
        }
    }
    
    public void merge(Usuario instance) {
    	try {
    		getSession().merge(instance);
    	} catch (RuntimeException re) {
    		throw re;
    	}
    }
    
    public void attachClean(Usuario instance) {
        try {
            getSession().lock(instance, LockMode.NONE);
        } catch (RuntimeException re) {
            throw re;
        }
    }

	@Override
	public Class<Usuario> getPojoClass() {
		return Usuario.class;
	}

	@SuppressWarnings("unchecked")
	public Collection<Usuario> buscaClientesSemLocalizacao() {
		try {
			String queryString = "from Usuario as i where i.usuarioAdministrativo = false and i.usuarioId not in " + 
			"(select m.usuario.usuarioId from Mapa as m) order by i.usuarioNome";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscaSituacaoCliente(boolean asm, boolean acm, boolean blo, boolean des) {
		try {
			String queryString = "from Usuario as i where i.usuarioAdministrativo = false";
			boolean jahTeve = false;
			if(asm){
				if(jahTeve){
					queryString += " or i." + UsuarioDAO.USUARIO_BLOQUEADO + " = " + Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM;
				}else{
					jahTeve = true;
					queryString += " and i." + UsuarioDAO.USUARIO_BLOQUEADO + " = " + Constantes.USR_ATIVO_DESBLOQUEADO_SEMMENSAGEM;
				}
			}
			if(acm){
				if(jahTeve){
					queryString += " or i." + UsuarioDAO.USUARIO_BLOQUEADO + " = " + Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM;
				}else{
					jahTeve = true;
					queryString += " and i." + UsuarioDAO.USUARIO_BLOQUEADO + " = " + Constantes.USR_ATIVO_DESBLOQUEADO_MENSAGEM;
				}
			}
			if(blo){
				if(jahTeve){
					queryString += " or i." + UsuarioDAO.USUARIO_BLOQUEADO + " = " + Constantes.USR_ATIVO_BLOQUEADO;
				}else{
					jahTeve = true;
					queryString += " and i." + UsuarioDAO.USUARIO_BLOQUEADO + " = " + Constantes.USR_ATIVO_BLOQUEADO;
				}
			}
			if(des){
				if(jahTeve){
					queryString += " or i." + UsuarioDAO.USUARIO_BLOQUEADO + " = " + Constantes.USR_DESATIVADO;
				}else{
					jahTeve = true;
					queryString += " and i." + UsuarioDAO.USUARIO_BLOQUEADO + " = " + Constantes.USR_DESATIVADO;
				}
			}
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Usuario> buscaClientesComboLocalizacao() {
		try {
			String queryString = "from Usuario as i where i.usuarioAdministrativo = false and i.usuarioId in " + 
			"(select m.usuario.usuarioId from Mapa as m) order by i.usuarioNome";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Usuario> buscaClientesComboLocalizacaoGoogle() {
		try {
			String queryString = "from Usuario as i where i.usuarioAdministrativo = false and i.usuarioId in " + 
			"(select m.usuario.usuarioId from Mapa as m) order by i.usuarioNome";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public int temAdministrador() {
		try {
			String queryString = "select count(*) as n from Usuario u where u."+ UsuarioDAO.USUARIO_ADMINISTRATIVO +" = true";
			Query queryObject = getSession().createQuery(queryString);
			Long ret = (Long) queryObject.list().get(0);
			return ret.intValue();
		} catch (RuntimeException re) {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Long> procuraUsuariosComBoletosNaoPagosPorIntervaloDeTempo(Date dtLOW, Date dtHI) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String queryString = "select u.usuarioId from Usuario u, BoletosGerados bog " +
					"where bog.usuario.usuarioId = u.usuarioId and " + 
					"bog.boletosgeradosDataVencimento > '"+ sdf.format(dtHI) +"' and " +
					"bog.boletosgeradosDataVencimento < '"+ sdf.format(dtLOW) +"' and " +
					"bog.boletosgeradosPago = false and " +
					"u.usuarioBloqueado <> 3 " +
					" group by u.usuarioId";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			return null;
		}
	}
}