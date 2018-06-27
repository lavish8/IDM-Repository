package com.identity.platform.utils.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.identity.platform.utils.error.PlatformError;

public class AbstractResponse extends ResponseEntity<Object> implements Serializable{

	private static final long serialVersionUID = -6608769068369406029L;

	private static final String BASE_URI = "baseUri";

	private HttpStatus status;
	private List<PlatformError> errors = new ArrayList<>(0);
	private Map<String, Object> metadata = new HashMap<>();

	public AbstractResponse(HttpStatus status, List<PlatformError> errors) {
		super(status);
		this.setStatus(status);
		this.setErrors(errors);
	}

	public AbstractResponse(HttpStatus status, List<PlatformError> errors, Map<String, Object> metadata,
			String baseUri) {
		super(status);
		this.status = status;
		this.setErrors(errors);
		this.metadata = metadata;
		this.metadata.put(BASE_URI, baseUri);
	}

	public List<PlatformError> getErrors() {
		return errors;
	}

	public void setErrors(List<PlatformError> errors) {
		if (errors == null) {
			this.errors = new ArrayList<>();
		} else {
			final List<PlatformError> errorsCopy = new ArrayList<>(errors);
			this.errors = errorsCopy;
		}
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}

}
