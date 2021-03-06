/**
 * 
 */
package com.identity.manager.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.identity.platform.persistence.domain.JpaAuditable;

/**
 * @author maheshs1
 *
 */

@Entity
@Table(name = "application", catalog = "orgsec_db")
public class Application extends JpaAuditable<User, Long> {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE__ID", nullable = false)
	private EntityType entityType;
	
	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@Column(name = "DESCRIPTION", length = 250)
	private String description;
	
	public Application(){}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
