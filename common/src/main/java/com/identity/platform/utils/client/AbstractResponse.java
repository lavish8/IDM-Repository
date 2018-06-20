package com.identity.platform.utils.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.identity.platform.utils.error.PlatformError;

public class AbstractResponse implements Serializable {
	
	private static final long serialVersionUID = 3067539911118414095L;
	private static final String BASE_URI = "baseUri";
	
	private int status;
	private List<PlatformError> errors = new ArrayList<>(0);
	private Map<String, Object> metadata = new HashMap<>();

	public AbstractResponse(int status, List<PlatformError> errors) {
		this.setStatus(status);
		this.setErrors(errors);
	}

	public AbstractResponse(int status, List<PlatformError> errors, Map<String, Object> metadata, String baseUri) {
		this.status = status;
		this.setErrors(errors);
		this.metadata = metadata;
		this.metadata.put(BASE_URI, baseUri);
	}

	public AbstractResponse() {
		
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Map<String, Object> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, Object> metadata) {
		this.metadata = metadata;
	}

}
