package com.identity.manager.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.identity.manager.persistence.domain.UserRepository;

@Repository
public interface UserRepositoryDao extends CrudRepository<UserRepository, Long> {
	
	UserRepository findByName(String name);
	
}
