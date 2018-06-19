/**
 * 
 */

package com.identity.manager.service;
import java.io.Serializable;

/**
 * @author maheshs1
 *
 */
public interface RoleService<T, R extends Serializable> extends CrudService<T, Serializable> {

	T findByName(String name);
}
