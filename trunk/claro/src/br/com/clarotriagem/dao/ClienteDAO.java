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
import br.com.clarotriagem.entitades.Cliente;

@Repository("clienteDAO")
public class ClienteDAO extends DAO<Cliente>{

	public ClienteDAO(){
		super(Cliente.class);
	}

	@Transactional(readOnly = true)
	public List<String> getClientes(String nf) throws Exception{
		String jpql = "Select c.nomeFantasia from Cliente c where upper(sem_acentos(c.nomeFantasia)) like upper(sem_acentos(:nf)) and c.cliente is null order by c.nomeFantasia";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("nf", "%" + nf + "%");
		
		@SuppressWarnings("unchecked")
		List<String> menus = (List<String>) query.getResultList();
		return menus;
	}
	
	@Transactional(readOnly = true)
	public Cliente getCliente(String nomeCliente) throws Exception{
		String jpql = "from Cliente c where c.nomeFantasia = :nf";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("nf", nomeCliente);
		
		@SuppressWarnings("unchecked")
		List<Cliente> menus = (List<Cliente>) query.getResultList();
		if(menus != null && menus.size() > 0){
			return menus.get(0);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<String> getWarehouse(String nf) throws Exception{
		String jpql = "Select c.nomeFantasia from Cliente c where upper(sem_acentos(c.nomeFantasia)) like upper(sem_acentos(:nf)) and c.cliente is not null order by c.nomeFantasia";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("nf", "%" + nf + "%");
		
		@SuppressWarnings("unchecked")
		List<String> menus = (List<String>) query.getResultList();
		return menus;
	}

	@Transactional(readOnly = true)
	public SortedMap<String, Long> buscaClientesPai() throws Exception{
		String jpql = "Select c.nomeFantasia, c.id from Cliente c where c.cliente is null order by c.nomeFantasia";
		Query query = entityManager.createQuery(jpql);
		
		@SuppressWarnings("unchecked")
		List<Object> clientes = query.getResultList();
		SortedMap<String, Long> ret = new TreeMap<String, Long>();
		for (Iterator<Object> iterator = clientes.iterator(); iterator.hasNext();) {
			Object[] c = (Object[]) iterator.next();
			ret.put(new String(c[0].toString()), new Long(c[1].toString()));
		}
		return ret;
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscaTodosClientes(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder) throws Exception{
		String jpql = "from Cliente c where c.cliente.id is null";
		
		if(sortField == null || "".equalsIgnoreCase(sortField)){
			jpql += " order by c.nomeFantasia";
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
		List<Cliente> ret = (List<Cliente>) query.getResultList();
		return ret;
	}

	@Transactional(readOnly = true)
	public int getQtdTotalCLientes() throws Exception{
		String jpql = "select COUNT(p) from Cliente p where p.cliente.id is null";
		Query query = entityManager.createQuery(jpql);
		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}

	@Transactional(readOnly = true)
	public List<Cliente> buscaTodosWarehouse(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder) throws Exception{
		String jpql = "from Cliente c where c.cliente.id is not null";
		
		if(sortField == null || "".equalsIgnoreCase(sortField)){
			jpql += " order by c.nomeFantasia";
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
		List<Cliente> ret = (List<Cliente>) query.getResultList();
		return ret;
	}
	
	@Transactional(readOnly = true)
	public int getQtdTotalWarehouse() throws Exception{
		String jpql = "select COUNT(p) from Cliente p where p.cliente.id is not null";
		Query query = entityManager.createQuery(jpql);
		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}

	@Transactional(readOnly = true)
	public SortedMap<String, Long> carregaWarehousePeloCliente(Long id) throws Exception{
		String jpql = "Select c.nomeFantasia, c.id from Cliente c where c.cliente.id = :cliente order by c.nomeFantasia";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("cliente", id);
		
		@SuppressWarnings("unchecked")
		List<Object> clientes = query.getResultList();
		SortedMap<String, Long> ret = new TreeMap<String, Long>();
		for (Iterator<Object> iterator = clientes.iterator(); iterator.hasNext();) {
			Object[] c = (Object[]) iterator.next();
			ret.put(new String(c[0].toString()), new Long(c[1].toString()));
		}
		return ret;
	}
	
}
