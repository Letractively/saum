package br.com.clarotriagem.dao;

import java.util.List;

import javax.persistence.Query;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.Mensagem;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;

@Repository("mensagemDAO")
public class MensagemDAO extends DAO<Mensagem> {

	public MensagemDAO() {
		super(Mensagem.class);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Mensagem> buscaMensagemNaoLida(UsuarioIdentificacao ui) throws Exception {
		String jpql = "from Mensagem c where c.destinatario.id = :dst and c.lida = :lida and c.excluido = :exc order by c.data";

		Query query = entityManager.createQuery(jpql);
		query.setParameter("dst", ui.getId());
		query.setParameter("lida", false);
		query.setParameter("exc", false);
		
		List<Mensagem> menus = (List<Mensagem>) query.getResultList();
		return detatchObjects(menus);
	}

	@Transactional(readOnly = true)
	public List<Mensagem> buscaMensagemTodas(UsuarioIdentificacao ui) throws Exception {
		return buscaMensagemTodas(ui, 0, 0, null, null);
	}

	@Transactional(readOnly = true)
	public List<Mensagem> buscaMensagemTodas(UsuarioIdentificacao ui, int primeiro, int qtd, String sortField, SortOrder sortOrder) throws Exception {
		
		String jpql = "from Mensagem c where c.destinatario.id = :id and c.excluido = :exc";
		
		if(sortField == null || "".equalsIgnoreCase(sortField)){
			jpql += " order by c.data desc";
		}else{
			if(sortOrder != null){
				if(SortOrder.ASCENDING.equals(sortOrder)){
					jpql += " order by c." + sortField + " asc";
				}else{
					jpql += " order by c." + sortField + " desc";
				}
			}
		}
		
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", ui.getId());
		query.setParameter("exc", false);
		
		if (primeiro > 0 || qtd > 0) {
			query.setFirstResult(primeiro);
			query.setMaxResults(qtd);
		}

		@SuppressWarnings("unchecked")
		List<Mensagem> ret = (List<Mensagem>) query.getResultList();
		return detatchObjects(ret);
	}
	
	@Transactional(readOnly = true)
	public List<Mensagem> buscaMensagemEnviadasTodas(UsuarioIdentificacao ui, int primeiro, int qtd, String sortField, SortOrder sortOrder) throws Exception {
		
		String jpql = "from Mensagem c where c.remetente.id = :id and c.excluido = :exc";
		
		if(sortField == null || "".equalsIgnoreCase(sortField)){
			jpql += " order by c.data desc";
		}else{
			if(sortOrder != null){
				if(SortOrder.ASCENDING.equals(sortOrder)){
					jpql += " order by c." + sortField + " asc";
				}else{
					jpql += " order by c." + sortField + " desc";
				}
			}
		}
		
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", ui.getId());
		query.setParameter("exc", false);
		
		if (primeiro > 0 || qtd > 0) {
			query.setFirstResult(primeiro);
			query.setMaxResults(qtd);
		}
		
		@SuppressWarnings("unchecked")
		List<Mensagem> ret = (List<Mensagem>) query.getResultList();
		return detatchObjects(ret);
	}

	@Transactional(readOnly = true)
	public Integer getQtdMensagensNaoLidas(UsuarioIdentificacao usuarioIdentificacao) throws Exception{
		String jpql = "select COUNT(p) from Mensagem p where p.lida = :lida and " +
				"p.destinatario = :dest and p.excluido = false";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("lida", false);
		query.setParameter("dest", usuarioIdentificacao);

		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}

	@Transactional(readOnly = true)
	public Integer getQtdTotalMensagens(UsuarioIdentificacao usuarioIdentificacao) throws Exception{
		String jpql = "select COUNT(p) from Mensagem p where p.destinatario = :dest and p.excluido = :excl";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("dest", usuarioIdentificacao);
		query.setParameter("excl", false);
		
		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}

	@Transactional(readOnly = true)
	public Integer getQtdTotalMensagemEnviadas(UsuarioIdentificacao usuarioIdentificacao) throws Exception{
		String jpql = "select COUNT(p) from Mensagem p where p.remetente = :dest and p.excluido = :excl";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("dest", usuarioIdentificacao);
		query.setParameter("excl", false);
		
		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}
	
}
