package com.identity.platform.persistence.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.joda.time.DateTime;

import com.couchbase.client.java.repository.annotation.Field;
import com.identity.platform.auth.constant.DomainConstants.LOCKED;

public abstract class AbstractCouchbaseAuditLog<T, PK extends Serializable> extends AbstractCouchbaseAuditable<T, PK> {

	private static final long serialVersionUID = -2610116950840100515L;

	@Size(min = 1)
	@NotNull
	@Enumerated(EnumType.STRING)
	@Field(value = "LOCKED")
	private LOCKED locked = LOCKED.N;

	@Field(value = "OBSOLETE_DATE")
	private LocalDate obsoleteDate;	
	
	@PrePersist
	protected void onCreate() {
		super.setCreatedDate(DateTime.now()); 
	}

	public LOCKED getLocked() {
		return locked;
	}

	public void setLocked(LOCKED locked) {
		this.locked = locked;
	}

	public LocalDate getObsoleteDate() {
		return obsoleteDate;
	}

	public void setObsoleteDate(LocalDate obsoleteDate) {
		this.obsoleteDate = obsoleteDate;
	}

}

