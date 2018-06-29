/**
 * 
 */
package com.identity.manager.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.identity.manager.persistence.domain.Application;

/**
 * @author maheshs1
 *
 */
@Repository
public interface ApplicationDao extends CrudRepository<Application, Long> {

	Application findByName(String name);

}
