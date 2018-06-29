package com.identity.manager.service;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;

/**
 * Interface for generic CRUD operations on a service for a specific type.
 * 
 * @author maheshs1
 */
@NoRepositoryBean
public interface CrudService<T, ID extends Serializable> extends Service<T, ID> {

	/**
	 * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
	 * entity instance completely.
	 * 
	 * @param entity
	 * @return the saved entity
	 */
	<S extends T> S save(S entity);

	/**
	 * Saves all given entities.
	 * 
	 * @param entities
	 * @return the saved entities
	 * @throws IllegalArgumentException in case the given entity is {@literal null}.
	 */
	<S extends T> Iterable<S> save(Iterable<S> entities);	
	
	/**
	 * Returns all instances of the type with the given criteria as T.
	 * 
	 * @param ids
	 * @return
	 */
	Iterable<T> findAll(Iterable<T> ids);
	
	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException if {@code id} is {@literal null}
	 */
	T findOne(ID id);
	
	T find(T criteria);

	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id must not be {@literal null}.
	 * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
	 */
	void delete(ID id);

	/**
	 * Deletes a given entity.
	 * 
	 * @param entity
	 * @throws IllegalArgumentException in case the given entity is {@literal null}.
	 */
	void delete(T entity);

	/**
	 * Deletes the given entities.
	 * 
	 * @param entities
	 * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
	 */
	void delete(Iterable<? extends T> entities);

	/**
	 * Deletes all entities managed by the service.
	 */
	void deleteAll();
}
