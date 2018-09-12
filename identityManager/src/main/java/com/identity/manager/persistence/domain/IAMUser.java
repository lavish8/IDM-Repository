package com.identity.manager.persistence.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.identity.manager.enums.DomainObjectEnum;
import com.identity.manager.enums.DomainObjectEnum.LOCKED;
import com.identity.platform.persistence.domain.BasicCouchbaseUser;

public class IAMUser extends BasicCouchbaseUser<IAMUser> implements UserDetails {

	private static final long serialVersionUID = 1134119973887914375L;
	
	private CompanyDoc company;	
	//private UserRepository userRepository;	
	//private Set<UserRole> userRoles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return getLocked().equals(LOCKED.N);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !getPassword().isEmpty();
	}

	@Override
	public boolean isEnabled() {
		return getStatus().getName().equals(DomainObjectEnum.STATUS_ACTIVE.getValue());
	}

	public CompanyDoc getCompany() {
		return company;
	}

	public void setCompany(CompanyDoc company) {
		this.company = company;
	}

	/*public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}*/
}