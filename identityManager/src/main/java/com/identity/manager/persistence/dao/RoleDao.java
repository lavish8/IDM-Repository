package com.identity.manager.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.identity.manager.persistence.domain.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> { 

	Role findByName(String name);
}
