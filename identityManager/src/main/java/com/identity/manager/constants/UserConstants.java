package com.identity.manager.constants;

import com.identity.platform.utils.error.exception.PropertyPlaceHolderUtil;

public final class UserConstants {
	
	private UserConstants() {
		throw new IllegalStateException("Utility class");
	}
	
	public static final String V1_USER = "/v1/user";
	public static final String V2_USER = "/v2/user";

	public static final String USER = "user";	
	public static final String USERID = "login";
	
	public enum DESCMessage {
		USER_CREATED("user.created.text");

		private final String key;

		private DESCMessage(String key) {
			this.key = PropertyPlaceHolderUtil.getStringProperty(key);
		}

		public String key() {
			return key;
		}
	}
}
