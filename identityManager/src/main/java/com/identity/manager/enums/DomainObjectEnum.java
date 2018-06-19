package com.identity.manager.enums;

public enum DomainObjectEnum {
	
	TO_BE_LOCKED("Y","LOCKED","locking"),
	NOT_TO_BE_LOCKED("N","NOTLOCKED","no locking"),
	PARENT_COMPANY_TYPE("PARENT_COMPANY", "PARENT COMPANY", "Parent company of this organization"),
	JOINT_COMPANY_TYPE("JV_COMPANY", "JV COMPANY", "Parent company of this organization"),
	USER_LOCAL_REPOSITORY("1", "LOCAL", "Local database"),
	USER_EDIRECTORY_REPOSITORY("2", "E-DIRECTORY", "standard user base"),
	USET_ACTIVE_DIRECTORY("3", "ACTIVE DIRECTORY (EXTRANET)", "standard user base for extranet"),
	ENTITY_TYPE_COMPANY("2", "COMPANY", "COMPANY"),
	ENTITY_TYPE_ROLE("10", "ROLE", "ROLE"),
	STATUS_ACTIVE("30", "ACTIVE", "ACTIVE"),
	STATUS_INACTIVE("60", "INACTIVE", "INACTIVE");
	
	public static final String UNIQUE_IDENTIFIER_KEY = "UID"; 
	
	public enum LOCKED {
		Y, N
	}
	
	public enum COMPANY_TYPE {
		FINANCE, IT, COMSUMER_GOODS, COMSUMER_SERVICES, HEALTH_CARE
	}
	
	private final String id;
	private final String value;
	private final String description;

	DomainObjectEnum(String id, String value, String description) {
		this.id = id;
		this.value = value;
		this.description = description;
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
