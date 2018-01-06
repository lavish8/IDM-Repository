package com.identity.manager.web.domain;

import java.io.Serializable;

import com.identity.manager.enums.DomainObjectEnum.COMPANY_TYPE;

public class CompanyPojo implements Serializable {
	
	private static final long serialVersionUID = 4642741155837963227L;
	
	private String name;
	private String code;
	private String description;
	private COMPANY_TYPE companyType;
	
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
	public COMPANY_TYPE getCompanyType() {
		return companyType;
	}
	public void setCompanyType(COMPANY_TYPE companyType) {
		this.companyType = companyType;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyPojo [name=");
		builder.append(name);
		builder.append(", code=");
		builder.append(code);
		builder.append(", description=");
		builder.append(description);
		builder.append(", companyType=");
		builder.append(companyType);
		builder.append("]");
		return builder.toString();
	}
}
