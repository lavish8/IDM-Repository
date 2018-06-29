package com.identity.platform.utils;

import java.util.Map;

public class HeaderdataManager {

	public HeaderdataManager() {
		super();
	}

	private static final ThreadLocal<Map<String, String>> HEADERMAP = new ThreadLocal<>();

	public static Map<String, String> getHeader() {
		return HEADERMAP.get();
	}

	public static void unsetHeader() {
		HEADERMAP.remove();
	}

	public static void setHeader(final Map<String, String> metadata) {
		HEADERMAP.set(metadata);
	}
}
