package com.identity.platform.utils.client;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.identity.platform.utils.error.PlatformError;

public class ClientResponse extends AbstractResponse {

	private static final long serialVersionUID = -5312422086536322827L;
	private Object data;

	public ClientResponse(HttpStatus status, List<PlatformError> errors, Object data) {
		super(status, errors);
		this.setData(data);
	}

	public ClientResponse(HttpStatus status, List<PlatformError> errors, Object data, Map<String, Object> metadata,
			String baseUri) {
		super(status, errors, metadata, baseUri);
		this.setData(data);
	}

	public ClientResponse(HttpStatus status, List<PlatformError> errors) {
		super(status, errors);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
