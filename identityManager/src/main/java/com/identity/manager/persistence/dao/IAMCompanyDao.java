package com.identity.manager.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.identity.manager.persistence.domain.CompanyDoc;

@Repository
public interface IAMCompanyDao extends CrudRepository<CompanyDoc, Long> {

	CompanyDoc findByCode(String name);
}