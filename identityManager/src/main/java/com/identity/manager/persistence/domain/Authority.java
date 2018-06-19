package com.identity.manager.persistence.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by tedonema on 31/03/2016.
 */
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = -2695564457779135032L;
	private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }


    @Override
    public String getAuthority() {
        return authority;
    }
}
