package com.identity.platform.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "user_mapping", catalog = "orgsec_db", uniqueConstraints = @UniqueConstraint(columnNames = { "LOGIN" }))
public abstract class BasicJpaUser extends JpaAuditable<BasicJpaUser, Long>{

	private static final long serialVersionUID = -4533956173135753274L;
	
	@Column(name = "LOGIN", nullable = false, length = 50)
	protected String login;
	
	@Column(name = "PASSWORD", nullable = false, length = 50)
	private String password;
	
	@Column(name = "EMAIL", length = 50)
	private String email;
	
	@Column(name = "UNIQUE_IDENTIFIER_VALUE", nullable = false, length = 50)
	private String uniqueIdentifierValue;
	
	@Column(name = "UNIQUE_IDENTIFIER_KEY", nullable = false, length = 50)
	private String uniqueIdentifierKey;
	
	@Column(name = "FIRST_NAME", length = 50)
	private String firstName;
	
	@Column(name = "LAST_NAME", length = 50)
	private String lastName;
	
	@Column(name = "DEPARTMENT", length = 50)
	private String department;
	
	@Column(name = "LDAP_STED_UID", length = 50)
	private String ldapStedUid;
	
	@Column(name = "COUNTRY", length = 50)
	private String country;
	
	@Column(name = "PHONE_NUMBER", length = 10)
	private long phoneNumber;
	
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getUniqueIdentifierValue() {
		return this.uniqueIdentifierValue;
	}

	public void setUniqueIdentifierValue(String uniqueIdentifierValue) {
		this.uniqueIdentifierValue = uniqueIdentifierValue;
	}


	public String getUniqueIdentifierKey() {
		return this.uniqueIdentifierKey;
	}

	public void setUniqueIdentifierKey(String uniqueIdentifierKey) {
		this.uniqueIdentifierKey = uniqueIdentifierKey;
	}


	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	public String getLdapStedUid() {
		return this.ldapStedUid;
	}

	public void setLdapStedUid(String ldapStedUid) {
		this.ldapStedUid = ldapStedUid;
	}


	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
