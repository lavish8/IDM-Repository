package com.identity.manager.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.identity.platform.persistence.domain.JpaAuditable;

@Entity
@Table(name = "user_repository", catalog = "orgsec_db", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class UserRepository extends JpaAuditable<User, Long> {

	private static final long serialVersionUID = 1L;
	
	@Column(name="NAME", unique=true, nullable=false, length=50)
	private String name;
	
	@Column(name="DESCRIPTION", length=45)
	private String description;
	
	@Column(name="CONNECTION_DETAILS", nullable=false, length=10000)
	private String connectionDetails;
	
	@Column(name="FORMAT", nullable=false, length=45)
	private String format;

	public UserRepository() {

	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getConnectionDetails() {
		return connectionDetails;
	}

	public void setConnectionDetails(String connectionDetails) {
		this.connectionDetails = connectionDetails;
	}


	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}
