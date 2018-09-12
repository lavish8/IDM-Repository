package com.identity.manager.persistence.domain;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.couchbase.core.mapping.Document;

import com.identity.platform.persistence.domain.JpaAuditable;

@Document
@Table(name = "company", catalog = "orgsec_db", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
public class CompanyDoc extends JpaAuditable<User, Long> {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE__ID", nullable = false)
	private EntityType entityType;

	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@Column(name = "CODE", nullable = false, length = 45)
	private String code;

	@Column(name = "DESCRIPTION", length = 45)
	private String description;

	@Column(name = "COMPANY_TYPE", nullable = false, length = 45)
	private String companyType;

	public CompanyDoc() {

	}

	public EntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
}
