package br.com.clarotriagem.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.Query;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.clarotriagem.dao.factory.DAO;
import br.com.clarotriagem.entitades.AparelhoMarca;

@Repository("aparelhoMarcaDAO")
public class AparelhoMarcaDAO extends DAO<AparelhoMarca>{

	public AparelhoMarcaDAO() {
		super(AparelhoMarca.class);
	}

	@Transactional(readOnly = true)
	public List<AparelhoMarca> findMarcas() throws Exception{
		String jpql = "from AparelhoMarca a order by a.nome";
		Query query = entityManager.createQuery(jpql);
		
		@SuppressWarnings("unchecked")
		List<AparelhoMarca> ret = (List<AparelhoMarca>) query.getResultList();
		return ret;
	}

	@Transactional(readOnly = true)
	public List<AparelhoMarca> findMarcasAtivas() throws Exception{
		String jpql = "from AparelhoMarca a where a.ativo = :at order by a.nome";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("at", true);
		
		@SuppressWarnings("unchecked")
		List<AparelhoMarca> ret = (List<AparelhoMarca>) query.getResultList();
		return ret;
	}

	@Transactional(readOnly = true)
	public List<AparelhoMarca> getMarcas(int primeiro, int qtd, String sortField, SortOrder sortOrder, Map<String, String> filters) throws Exception{
		String jpql = "from AparelhoMarca a";
		int qtdFiltro = 0;
		for(Map.Entry<String, String> en : filters.entrySet()){
			if(qtdFiltro == 0){
				jpql += " where ";
			}
			jpql += " upper(a." + en.getKey() + ") like :" + en.getKey();
		}

		
		if(sortField == null || "".equalsIgnoreCase(sortField)){
			jpql += " order by a.nome";
		}else{
			if(sortOrder != null){
				if(SortOrder.ASCENDING.equals(sortOrder)){
					jpql += " order by a." + sortField + " asc";
				}else{
					jpql += " order by a." + sortField + " desc";
				}
			}
		}
		
		Query query = entityManager.createQuery(jpql);
		for(Map.Entry<String, String> en : filters.entrySet()){
			query.setParameter(en.getKey(), "%" + en.getValue().toUpperCase() + "%");
		}
		
		if (primeiro > 0 || qtd > 0) {
			query.setFirstResult(primeiro);
			query.setMaxResults(qtd);
		}

		@SuppressWarnings("unchecked")
		List<AparelhoMarca> ret = (List<AparelhoMarca>) query.getResultList();
		return detatchObjects(ret);
	}

	@Transactional(readOnly = true)
	public int getQtdTotalMarcas() {
		String jpql = "select COUNT(a) from AparelhoMarca a";
		Query query = entityManager.createQuery(jpql);

		Number result = (Number) query.getSingleResult();
		return result.intValue();
	}
	
	@Transactional(readOnly = true)
	public SortedMap<String, Long> getMarcasParaCombo() throws Exception{
		String jpql = "Select c.nome, c.id from AparelhoMarca c where c.ativo = true";
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


}
