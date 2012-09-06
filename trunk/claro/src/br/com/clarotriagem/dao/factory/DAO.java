package br.com.clarotriagem.dao.factory;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.transaction.annotation.Transactional;

public abstract class DAO<T extends Serializable> {

	public DAO(){
		super();
	}
	public DAO(Class<T> generico){
		this.generico = generico;
	}
	
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	protected EntityManager entityManager;
	protected Class<T> generico = null;
	
	@Transactional
	public T save(T transiente) throws Exception{
		entityManager.persist(transiente);
		return transiente;
	}
	
	@Transactional(readOnly = true)
	public T find(Long transiente) throws Exception{
		T ret = entityManager.find(generico, transiente);
		return ret;
	}
	
	@Transactional
	public T update(T transiente) throws Exception{
		T ret = entityManager.merge(transiente);
		return ret;
	}

	public void delete(T transiente) throws Exception{
		entityManager.getReference(generico, transiente);
		entityManager.remove(transiente);
	}

	@Transactional
	public void delete(T transiente, Long id) throws Exception{
		transiente = entityManager.getReference(generico, id);
		entityManager.remove(transiente);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public T detatchObject(T persistent) throws Exception{
		entityManager.detach(persistent);
		return persistent;
	}
	public List<T> detatchObjects(List<T> persistents) throws Exception{
		for (Iterator<T> iterator = persistents.iterator(); iterator.hasNext();) {
			entityManager.detach((T) iterator.next());
		}
		return persistents;
	}

}
