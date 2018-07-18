package com.identity.manager.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.util.StringUtils;

import com.identity.manager.constants.UserConstants;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String uid = authentication.getName();
		String cred = (String) authentication.getCredentials();
		Map<String, Object> claims = (Map<String, Object>) authentication.getDetails();
		List<GrantedAuthority> authorities = new ArrayList<>();

		if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(cred)) {
			log.debug("CustomAuthenticationProvider | user input validation failed");
			throw new BadCredentialsException("Bad Credentials!!");
		}

		try {
			String[] roles = { "ADMIN", "SUPERUSER" };
			// isvalidOauthToken(cred);
			// authorities = assigningAuthorities(uid, claims);
			authorities = AuthorityUtils.createAuthorityList(roles);
			claims.put(UserConstants.USERID, uid);
		} catch (Exception ex) {
			log.error("CustomAuthenticationProvider | user validation failed with exception ", ex);
			throw new BadCredentialsException("Bad Credentials!!", ex);
		}
		log.info("CustomAuthenticationProvider | User validated successfully");
		return new UsernamePasswordAuthenticationToken(uid, cred, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}

}
