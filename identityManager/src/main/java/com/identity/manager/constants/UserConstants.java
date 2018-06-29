package com.identity.manager.constants;

import com.identity.platform.utils.error.exception.PropertyPlaceHolderUtil;

public final class UserConstants {
	
	private UserConstants() {
		throw new IllegalStateException("Utility class");
	}
	
	public static final String V1_USER = "/v1/user";
	public static final String V2_USER = "/v2/user";

	public static final String USER = "user";
	
	public static final String USER_CREATED = "Successfully user information has added";
	public static final String USER_SEARCH_BY_ID = "Successfully fetched user information wrt id";
	public static final String USER_SEARCH = "Successfully fetched user information";
	public static final String USER_DELETED = "Successfully user has deleted";
	public static final String USER_DELETED_BY_ID = "Successfully user has deleted wrt id";
	
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
