package com.identity.manager.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.identity.manager.persistence.domain.Company;

@Repository
public interface CompanyDao extends CrudRepository<Company, Long> {

}
