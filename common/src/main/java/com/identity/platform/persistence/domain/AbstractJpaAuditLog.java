package com.identity.platform.persistence.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractAuditable;

import com.identity.platform.auth.constant.DomainConstants.LOCKED;

@MappedSuperclass
@AttributeOverrides({ @AttributeOverride(name = "id", column = @Column(name = "ID")),
	@AttributeOverride(name = "createdDate", column = @Column(name = "CREATION_DATE", updatable = false)),
	@AttributeOverride(name = "lastModifiedDate", column = @Column(name = "MODIFICATION_DATE", insertable=false, updatable=false  /*source should be db*/ 	)) })
@AssociationOverrides({ @AssociationOverride(name = "createdBy", joinColumns = @JoinColumn(name = "CREATED_BY", updatable = false)),
	@AssociationOverride(name = "lastModifiedBy", joinColumns = @JoinColumn(name = "MODIFIED_BY")) })
public abstract class AbstractJpaAuditLog<T, PK extends Serializable> extends AbstractAuditable<T, PK> {

	private static final long serialVersionUID = 3822718026990095365L;

	@Column(name = "LOCKED", nullable = false, length = 1)
	@Enumerated(EnumType.STRING)
	private LOCKED locked = LOCKED.N;

	@Column(name = "OBSOLETE_DATE", length = 19)
	private LocalDate obsoleteDate;
	
	@Version
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	
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
