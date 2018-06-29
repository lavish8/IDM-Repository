package com.identity.platform.persistence.domain;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.identity.platform.auth.constant.PlatformConstants;

@Document
public class ExceptionTrackingModel extends AbstractCouchbaseAuditable<Object, Long> {

	@Field
	private String docType = PlatformConstants.EXCEPTION;
	
	@Field
	private String stackTrace;
	
	@Field
	private String origin;

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	
	public void setId(final Long id) {
		super.setId(id);
	}
}
