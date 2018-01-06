package com.identity.manager.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.identity.manager.persistence.domain.Status;

@Repository
public interface StatusDao extends CrudRepository<Status, Long> {
	
	Status findByName(String name);
}
