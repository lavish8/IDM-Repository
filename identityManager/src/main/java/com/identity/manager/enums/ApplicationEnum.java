package com.identity.manager.enums;

public enum ApplicationEnum {

	ADMIN_ROLE("1", "ADMIN", "full access to manage everything"),
	READ_ROLE("1", "READ", "only read access wrt company"),
	MANAGE_USER_ROLE("2", "USER_ROLE", "full access to manage user profile"),
	MANAGE_COMPANY_ROLE("3", "COMPANY_ROLE", "full access to manage company related infomation");

	private final String id;
	private final String value;
	private final String description;
	
	ApplicationEnum(String id, String value, String description) {
		this.id = id;
		this.value = value;
		this.description =  description;
	}
	
	public String getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}	
}
