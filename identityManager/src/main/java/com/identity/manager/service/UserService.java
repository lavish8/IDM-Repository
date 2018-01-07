package com.identity.manager.service;

import java.io.Serializable;

/**
 * @author maheshs1
 *
 */
public interface UserService<T, R extends Serializable> extends CrudService<T, Serializable> {
	
	T findByLogin(String login);
	T findByEmail(String email);
	void updateUserPassword(String login, String password);
}
