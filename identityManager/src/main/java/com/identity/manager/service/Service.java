package com.identity.manager.service;

import java.io.Serializable;

/**
 * Central service marker interface. Captures the POJO/DTO/VO type. General
 * purpose is to hold type information as well as being able to discover interfaces that extend this one during
 * classpath scanning for easy Spring bean creation.
 * <p>
 * Domain services extending this interface can selectively expose CRUD methods by simply declaring methods of the
 * same signature as those declared in {@link CrudService}.
 * 
 * @see CrudService
 * @param <T> the POJO/DTO/VO type the service manages
 * @author maheshs1
 */
public interface Service<T, ID extends Serializable> {

}
