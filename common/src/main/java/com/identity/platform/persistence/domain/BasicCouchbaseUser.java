package com.identity.platform.persistence.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;

@Document
public class BasicCouchbaseUser extends CouchbaseAuditable<BasicCouchbaseUser, Long> {

	private static final long serialVersionUID = 8020043421437735910L;

	@NotNull
	@Size(min = 50)
	@Field(value = "LOGIN")
	protected String login;
	
	@NotNull
	@Size(min = 50)
	@Field(value = "PASSWORD")
	private String password;
	
	@Size(min = 50)
	@Field(value = "EMAIL")
	private String email;
	
	@NotNull
	@Size(min = 50)
	@Field(value = "UNIQUE_IDENTIFIER_VALUE")
	private String uniqueIdentifierValue;

	@NotNull
	@Size(min = 50)
	@Field(value = "UNIQUE_IDENTIFIER_KEY")
	private String uniqueIdentifierKey;
	
	@Size(min = 50)
	@Field(value = "FIRST_NAME")
	private String firstName;
	
	@Size(min = 50)
	@Field(value = "LAST_NAME")
	private String lastName;
	
	@Field(value = "DEPARTMENT")
	private String department;
	
	@Field(value = "LDAP_STED_UID")
	private String ldapStedUid;
	
	@Field(value = "COUNTRY")
	private String country;
	
	@Size(min = 50)
	@Field(value = "PHONE_NUMBER")
	private long phoneNumber;

	public String getLogin() {
		return login;
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
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUniqueIdentifierValue() {
		return uniqueIdentifierValue;
	}

	public void setUniqueIdentifierValue(String uniqueIdentifierValue) {
		this.uniqueIdentifierValue = uniqueIdentifierValue;
	}

	public String getUniqueIdentifierKey() {
		return uniqueIdentifierKey;
	}

	public void setUniqueIdentifierKey(String uniqueIdentifierKey) {
		this.uniqueIdentifierKey = uniqueIdentifierKey;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLdapStedUid() {
		return ldapStedUid;
	}

	public void setLdapStedUid(String ldapStedUid) {
		this.ldapStedUid = ldapStedUid;
	}

	public String getCountry() {
		return country;
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
