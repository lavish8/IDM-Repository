package com.identity.platform.persistence.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.PrePersist;

import com.couchbase.client.java.repository.annotation.Field;

public class CouchbaseAuditable<T, PK extends Serializable> extends AbstractCouchbaseAuditLog<T, PK> {

	private static final long serialVersionUID = 1464661153887314777L;
	
	@Field(value = "STATUS__ID")
	private Status status;

	@Field(value = "STATUS_DATE")
	private LocalDateTime statusDate;
	
	@PrePersist
	protected void onCreateStatus() {
		this.statusDate=LocalDateTime.now(); 
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(LocalDateTime statusDate) {
		this.statusDate = statusDate;
	}


}
