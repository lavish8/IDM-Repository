package com.identity.platform.persistence.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

@MappedSuperclass
public abstract class JpaAuditable<T, PK extends Serializable> extends AbstractJpaAuditLog<T, PK> {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATUS__ID", nullable = false)
	private Status status;

	@Column(name = "STATUS_DATE", length = 19)
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
