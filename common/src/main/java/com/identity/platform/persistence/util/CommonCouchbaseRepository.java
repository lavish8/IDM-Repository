package com.identity.platform.persistence.util;

import java.io.Serializable;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonCouchbaseRepository<T, ID extends Serializable> extends CouchbaseRepository<T, ID> {

}
