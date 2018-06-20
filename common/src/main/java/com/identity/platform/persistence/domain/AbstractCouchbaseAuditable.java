package com.identity.platform.persistence.domain;

import java.io.Serializable;
import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.Auditable;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.core.context.SecurityContextHolder;

import com.couchbase.client.java.repository.annotation.Field;

public abstract class AbstractCouchbaseAuditable<U, PK extends Serializable> extends AbstractPersistable<PK>
		implements Auditable<U, PK>, AuditorAware<U> { //For getting the audit time interface DateTimeProvider implementation CurrentDateTimeProvider

	private static final long serialVersionUID = 3571310672292683827L;

	@CreatedBy
	@Field(value = "CREATED_BY")
	public U createdBy;

	@LastModifiedBy
	@Field(value = "MODIFIED_BY")
	private U lastModifiedBy;

	@Version
	@LastModifiedDate
	@Field(value = "MODIFICATION_DATE")
	public Date lastModifiedDate;

	@CreatedDate
	@Field(value = "CREATION_DATE")
	public Date createdDate;

	public U getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}

	public U getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	public DateTime getCreatedDate() {
		return null == createdDate ? null : new DateTime(createdDate);
	}

	public void setCreatedDate(final DateTime createdDate) {
		this.createdDate = null == createdDate ? null : createdDate.toDate();
	}

	@Override
	public DateTime getLastModifiedDate() {
		return null == lastModifiedDate ? null : new DateTime(lastModifiedDate);
	}

	@Override
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate.toDate();
	}
	
	@Override
	 public U getCurrentAuditor() {
	  return  (U) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //TODO
	 }

	
}
