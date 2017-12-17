package com.identity.manager.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "entity_type", catalog = "orgsec_db")
public class EntityType extends Auditable {

	private static final long serialVersionUID = 1L;
	
    private String name;
    private String description;
    private String tableName;
    
    public EntityType(){
    	
    }
    
    @Column(name="NAME", nullable=false, length=50)
	public String getName() {
		return name;
	}
    
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="DESCRIPTION", length=250)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="TABLE_NAME", nullable=false, length=45)
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
