package com.identity.manager.constants;

public final class Constants {

	private Constants() {
		throw new IllegalStateException("Utility class");
	}

	public static final String NOT_AUTHORIZED = "You are not authorized to view the resource";
	public static final String FORBIDDEN = "Accessing the resource you were trying to reach is forbidden";
	public static final String NOT_FOUND = "The resource you were trying to reach is not found";
}
