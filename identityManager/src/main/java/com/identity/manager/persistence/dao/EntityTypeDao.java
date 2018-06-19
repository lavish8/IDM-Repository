package com.identity.manager.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.identity.manager.persistence.domain.EntityType;

@Repository
public interface EntityTypeDao extends CrudRepository<EntityType, Long> {

	EntityType findByName(String name);

}
