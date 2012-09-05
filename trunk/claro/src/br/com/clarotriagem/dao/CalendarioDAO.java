package br.com.clarotriagem.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.Calendario;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;
import br.com.clarotriagem.utils.enums.TipoAtendimento;

@Repository("calendarioDAO")
public class CalendarioDAO extends DAO<Calendario>{

	public CalendarioDAO() {
		super(Calendario.class);
	}

	@Transactional(readOnly = true)
	public List<Calendario> getAgendaPorIntervaloDeDias(Date dtInicial, Date dtFinal) throws Exception{
		String jpql = "from Calendario c where c.dataInicial >= :di and c.dataFinal <= :df";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("di", new Timestamp(dtInicial.getTime()));
		query.setParameter("df", new Timestamp(dtFinal.getTime()));
		
		@SuppressWarnings("unchecked")
		List<Calendario> ret = (List<Calendario>) query.getResultList();
		return ret;
	}


	@Transactional(readOnly = true)
	public List<Calendario> getAgendaPorIntervaloDeDiasSemEnvioDeEmailAntecipado(Date dtInicial, Date dtFinal) throws Exception{
		String jpql = "from Calendario c where " +
				"c.dataInicial >= :di and " +
				"c.dataFinal <= :df and " +
				"c.envioEmailAntecipado = :envio and " +
				"c.concluido = :conc and " +
				"c.tipoAtendimento = :tpAtd";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter("di", new Timestamp(dtInicial.getTime()));
		query.setParameter("df", new Timestamp(dtFinal.getTime()));
		query.setParameter("envio", false);
		query.setParameter("conc", false);
		query.setParameter("tpAtd", TipoAtendimento.ATENDIMENTO.getCod());
		
		@SuppressWarnings("unchecked")
		List<Calendario> ret = (List<Calendario>) query.getResultList();
		return ret;
	}
	
	@Transactional(readOnly = true)
	public List<Calendario> getAgendaPorIntervaloDeDiasSemEnvioDeEmailAtrasado(Date dtInicial, Date dtFinal) throws Exception{
		String jpql = "from Calendario c where " +
				"c.dataInicial >= :di and " +
				"c.dataFinal <= :df and " +
				"c.envioEmailAtrasado = false and" +
				"c.concluido = :conc and " +
				"c.tipoAtendimento = :tpAtd";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter("di", new Timestamp(dtInicial.getTime()));
		query.setParameter("df", new Timestamp(dtFinal.getTime()));
		query.setParameter("conc", false);
		query.setParameter("tpAtd", TipoAtendimento.ATENDIMENTO.getCod());

		@SuppressWarnings("unchecked")
		List<Calendario> ret = (List<Calendario>) query.getResultList();
		return ret;
	}
	
	@Transactional(readOnly = true)
	public List<Calendario> getAgendaPorIntervaloDeDias(Date dtInicial, Date dtFinal, UsuarioIdentificacao usr) throws Exception{
		Query query;
		if(usr.getPerfil().getVisualizaTodosAgendamentos()){
			String jpql = "from Calendario c where c.dataInicial >= :di and c.dataFinal <= :df";
			query = entityManager.createQuery(jpql);
			query.setParameter("di", new Timestamp(dtInicial.getTime()));
			query.setParameter("df", new Timestamp(dtFinal.getTime()));
		}else{
			String jpql = "from Calendario c where c.dataInicial >= :di and c.dataFinal <= :df and c.usuarioResponsavel.id = :id";
			query = entityManager.createQuery(jpql);
			query.setParameter("di", new Timestamp(dtInicial.getTime()));
			query.setParameter("df", new Timestamp(dtFinal.getTime()));
			query.setParameter("id", usr.getId());
		}
		@SuppressWarnings("unchecked")
		List<Calendario> ret = (List<Calendario>) query.getResultList();
		return ret;
	}

	@Transactional
	public void atualizaCalendario(Calendario cal)  throws Exception{
		entityManager.getReference(generico, cal.getId());
		entityManager.merge(cal);
	}

	@Transactional(readOnly = true)
	public Calendario getCalendarioPeloID(Long id) throws Exception{
		String jpql = "from Calendario c where c.id >= :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		List<Calendario> ret = (List<Calendario>) query.getResultList();
		if(ret != null && ret.size() > 0){
			return ret.get(0);
		}
		return null;
	}

	@Transactional(readOnly = true)
	public List<Calendario> buscaCalendarios(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, boolean buscaTodos, UsuarioIdentificacao usrLogado) throws Exception{
		Query query = null;
		String order = "";
		
		if(sortField == null || "".equalsIgnoreCase(sortField)){
			order += " order by c.dataInicial";
		}else{
			if(sortOrder != null){
				if(SortOrder.ASCENDING.equals(sortOrder)){
					order += " order by c." + sortField + " asc";
				}else{
					order += " order by c." + sortField + " desc";
				}
			}
		}
		
		if(!buscaTodos){
			String jpql = "from Calendario c where c.concluido = false and c.usuarioResponsavel.id = :usrResp" + order;
			query = entityManager.createQuery(jpql);
			query.setParameter("usrResp", usrLogado.getId());
		}else{
			String jpql = "from Calendario c where c.concluido = false" + order;
			query = entityManager.createQuery(jpql);
		}
		
		query.setFirstResult(startingAt);
		query.setMaxResults(maxPerPage);

		@SuppressWarnings("unchecked")
		List<Calendario> ret = (List<Calendario>) query.getResultList();

		return ret;
	}

	@Transactional(readOnly = true)
	public int getQtdTotalCalendario(boolean buscaTodos, UsuarioIdentificacao usrLogado) throws Exception{
		Query query = null;

		if(!buscaTodos){
			String jpql = "select COUNT(c) from Calendario c where c.concluido = false and c.usuarioResponsavel.id = :usrResp";
			query = entityManager.createQuery(jpql);
			query.setParameter("usrResp", usrLogado.getId());
		}else{
			String jpql = "select COUNT(c) from Calendario c where c.concluido = false";
			query = entityManager.createQuery(jpql);
		}

		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}

	public List<Calendario> buscaCalendariosAbertos(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder) throws Exception{
		Query query = null;
		String order = "";
		
		if(sortField == null || "".equalsIgnoreCase(sortField)){
			order += " order by c.dataInicial";
		}else{
			if(sortOrder != null){
				if(SortOrder.ASCENDING.equals(sortOrder)){
					order += " order by c." + sortField + " asc";
				}else{
					order += " order by c." + sortField + " desc";
				}
			}
		}
		
		String jpql = "from Calendario c where c.concluido = :conc" + order;
		query = entityManager.createQuery(jpql);
		query.setParameter("conc", false);
		
		query.setFirstResult(startingAt);
		query.setMaxResults(maxPerPage);

		@SuppressWarnings("unchecked")
		List<Calendario> ret = (List<Calendario>) query.getResultList();
		return ret;
	}

	public List<Calendario> buscaCalendariosPorUsuarioAbertos(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, UsuarioIdentificacao usrLogado) throws Exception{
		Query query = null;
		String order = "";
		
		if(sortField == null || "".equalsIgnoreCase(sortField)){
			order += " order by c.dataInicial";
		}else{
			if(sortOrder != null){
				if(SortOrder.ASCENDING.equals(sortOrder)){
					order += " order by c." + sortField + " asc";
				}else{
					order += " order by c." + sortField + " desc";
				}
			}
		}
		
		String jpql = "from Calendario c where c.concluido = :conc and c.usuarioResponsavel.id = :usr" + order;
		query = entityManager.createQuery(jpql);
		query.setParameter("conc", false);
		query.setParameter("usr", usrLogado.getId());
		
		query.setFirstResult(startingAt);
		query.setMaxResults(maxPerPage);
		
		@SuppressWarnings("unchecked")
		List<Calendario> ret = (List<Calendario>) query.getResultList();
		return ret;
	}

}
