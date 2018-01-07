package com.identity.manager.test.dao.util;

import java.time.LocalDate;

import com.identity.manager.persistence.domain.User;

public class UserUtil {

	/**
	 * Non instantiable.
	 */
	private UserUtil() {
		throw new AssertionError("Non instantiable");
	}

	public static User createUser(String username, String email) {
		User user = new User();
		user.setLogin(username);
		user.setPassword("fe5c3358bc09de0f6717b92dc82e5d98");
		user.setEmail(email);
		user.setUniqueIdentifierValue(username);
		user.setUniqueIdentifierKey("UID");
		user.setObsoleteDate(LocalDate.now());
		user.setFirstName(email.split("@")[0].split("\\.")[0]);
		user.setLastName(email.split("@")[0].split("\\.").length > 1 ? email.split("@")[0].split("\\.")[1] : null);
		user.setPhoneNumber(1234567890);
		user.setCountry(email.split("@")[1].split("\\.")[1]);
		user.setDepartment("ICT");
		user.setPassword("useme123");
		return user;
	}
}
