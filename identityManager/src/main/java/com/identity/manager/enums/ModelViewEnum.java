package com.identity.manager.enums;

public enum ModelViewEnum {

	CONTACT_US_VIEW_NAME("contact/contact","Contact Page","Name of contact page"),
	ABOUT_US_VIEW_NAME("about","About Page","Name of about page"); 
	
	public static final String FEEDBACK_MODEL = "feedback";
	public static final String ABOUT_MODEL = "about";
	
	private final String id;
	private final String value;
	private final String description;
	
	 ModelViewEnum(String id, String value, String description) {
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
