/**
 * 
 */
package com.identity.manager.web.domain;

import java.io.Serializable;
import java.time.LocalDate;

import io.swagger.annotations.Api;

/**
 * @author maheshs1
 *
 */
@Api
public class RolePojo implements Serializable {
	
	private static final long serialVersionUID = -8630738578395175505L;
	
	private String company;
	private String name;
	private String description;
	private boolean isContinue = false; // is update
	private transient LocalDate obsoleteDate;
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
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
	public boolean isContinue() {
		return isContinue;
	}
	public void setContinue(boolean isContinue) {
		this.isContinue = isContinue;
	}
	public LocalDate getObsoleteDate() {
		return obsoleteDate;
	}
	public void setObsoleteDate(LocalDate obsoleteDate) {
		this.obsoleteDate = obsoleteDate;
	}


}
