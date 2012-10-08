package br.com.meganet.infra;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import br.com.meganet.exception.GAPBDException;

/**
 * DAO - IF comum para demais DAOs
 * 
 * @version 1.0
 * 
 */
public interface DAO<E> {
	/**
	 * Operacao Delete
	 * 
	 * @param pojo
	 * @throws GAPBDException
	 */
	public void delete(E pojo) throws GAPBDException;

	/**
	 * Operacao FindByCriteria
	 * 
	 * @param order
	 * @param criteria
	 * @return Lista de pojos
	 * @throws GAPBDException
	 */
	public List<E> findByCriteria(Order order, Criterion ... criteria) throws GAPBDException;

	/**
	 * Operacao Get
	 * 
	 * @param id
	 * @return Pojos
	 * @throws GAPBDException
	 */
	public E get(Serializable id) throws GAPBDException;

	/**
	 * Operacao ListAll
	 * 
	 * @return Lista de pojos
	 * @throws GAPBDException
	 */
	public List<E> listAll() throws GAPBDException;

	/**
	 * Operacao Merge
	 * 
	 * @param pojo
	 * @throws GAPBDException
	 */
	public void merge(E pojo) throws GAPBDException;

	/**
	 * Operacao Refresh
	 * 
	 * @param pojo
	 * @throws GAPBDException
	 */
	public void refresh(E pojo) throws GAPBDException;

	/**
	 * Operacao Save
	 * 
	 * @param pojo
	 * @throws GAPBDException
	 */
	public void save(E pojo) throws GAPBDException;

	/**
	 * operacao Save or Update
	 * 
	 * @param pojo
	 * @throws GAPBDException
	 */
	public void saveOrUpdate(E pojo) throws GAPBDException;

	/**
	 * Operacao Update
	 * 
	 * @param pojo
	 * @throws GAPBDException
	 */
	public void update(E pojo) throws GAPBDException;
}
