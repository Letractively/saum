package br.com.clarotriagem.dao;

import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Query;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.UsuarioIdentificacao;

@Repository("usuarioIdentificacaoDAO")
public class UsuarioIdentificacaoDAO extends DAO<UsuarioIdentificacao>{

	public UsuarioIdentificacaoDAO() {
		super(UsuarioIdentificacao.class);
	}

	@Transactional(readOnly = true)
	public List<UsuarioIdentificacao> efetuarLogin(UsuarioIdentificacao ui) throws Exception {
		String jpql = "from UsuarioIdentificacao c where c.email = :email and c.senha = :senha and c.ativo = :ativo";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("email", ui.getEmail());
		query.setParameter("senha", ui.getSenha());
		query.setParameter("ativo", true);
		
		@SuppressWarnings("unchecked")
		List<UsuarioIdentificacao> ret = (List<UsuarioIdentificacao>) query.getResultList();
		return ret;
	}

	@Transactional(readOnly = true)
	public List<UsuarioIdentificacao> buscaUsuariosNaoAutorizados() throws Exception {
		String jpql = "from UsuarioIdentificacao c where c.cadastroConfirmado = :cc and c.ativo = :ativo";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("cc", false);
		query.setParameter("ativo", true);
		
		@SuppressWarnings("unchecked")
		List<UsuarioIdentificacao> ret = (List<UsuarioIdentificacao>) query.getResultList();
		return ret;
	}

	@Transactional(readOnly = true)
	public SortedMap<String, Long> buscaUsuariosAutorizados() throws Exception {
		String jpql = "Select c.nome, c.email, c.id from UsuarioIdentificacao c where c.cadastroConfirmado = true and c.ativo = true order by c.nome";
		Query query = entityManager.createQuery(jpql);
		
		@SuppressWarnings("unchecked")
		List<Object> clientes = query.getResultList();
		SortedMap<String, Long> ret = new TreeMap<String, Long>();
		for (Iterator<Object> iterator = clientes.iterator(); iterator.hasNext();) {
			Object[] c = (Object[]) iterator.next();
			ret.put(new String(c[0].toString()) + " - " + new String(c[1].toString()), new Long(c[2].toString()));
		}
		return ret;
	}
	
	@Transactional(readOnly = true)
	public List<UsuarioIdentificacao> buscaTodosUsuarios(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder) throws Exception {
		String jpql = "from UsuarioIdentificacao c";
		
		if(sortField == null || "".equalsIgnoreCase(sortField)){
			jpql += " order by c.nome";
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
		query.setFirstResult(startingAt);
		query.setMaxResults(maxPerPage);
		
		@SuppressWarnings("unchecked")
		List<UsuarioIdentificacao> ret = (List<UsuarioIdentificacao>) query.getResultList();
		return ret;
	}
	
	@Transactional(readOnly = true)
	public Integer getQtdUsuarios() throws Exception{
		String jpql = "select COUNT(p) from UsuarioIdentificacao p";
		Query query = entityManager.createQuery(jpql);
		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}

	@Transactional(readOnly = true)
	public List<String> buscaUsuariosAtivos(String email) throws Exception{
		String jpql = "Select c.email from UsuarioIdentificacao c where upper(c.email) like upper(:email) and c.cadastroConfirmado = :aut order by c.email";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("email", "%" + email + "%");
		query.setParameter("aut", true);
		
		@SuppressWarnings("unchecked")
		List<String> menus = (List<String>) query.getResultList();
		return menus;
	}

	@Transactional(readOnly = true)
	public UsuarioIdentificacao buscaUsuarioAtivo(String email) throws Exception{
		String jpql = "from UsuarioIdentificacao c where c.email = :email";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("email", email);
		
		@SuppressWarnings("unchecked")
		List<UsuarioIdentificacao> menus = (List<UsuarioIdentificacao>) query.getResultList();
		if(menus != null && menus.size() >0){
			return menus.get(0);
		}else{
			return null;
		}
	}

	@Transactional(readOnly = true)
	public UsuarioIdentificacao buscaUsuarioPeloID(Long id) throws Exception{
		String jpql = "from UsuarioIdentificacao c where c.id = :id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("id", id);
		
		@SuppressWarnings("unchecked")
		List<UsuarioIdentificacao> menus = (List<UsuarioIdentificacao>) query.getResultList();
		if(menus != null & menus.size() > 0){
			return menus.get(0);
		}
		return null;
	}


}
