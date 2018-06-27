package com.identity.platform.service.handler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;

import com.identity.platform.auth.constant.PlatformConstants;
import com.identity.platform.auth.constant.PlatformConstants.PaginationParameters;
import com.identity.platform.event.PlarformEventProducer;
import com.identity.platform.utils.BeanUtil;
import com.identity.platform.utils.client.AbstractResponse;
import com.identity.platform.utils.client.ClientResponse;
import com.identity.platform.utils.error.PlatformError;
import com.identity.platform.utils.error.PlatformErrorCodes;
import com.identity.platform.utils.error.exception.PlatformException;
import com.identity.platform.utils.error.exception.PlatformExceptionTranslatorUtil;

public abstract class AbstractServiceHandler implements ServiceHandler {

	private static final String DELETE = "DELETE";
	private static final String PUT = "PUT";
	private static final String POST = "POST";
	private static final String GET = "GET";
	private static final String POST_PARAM = "POST_PARAM";
	private static final String QUERY_PARAM = "QUERY_PARAM";
	private static final String PATHPARAM = "PATH_PARAM";
	private static final String METHOD = "METHOD";

	@Autowired
	private PlarformEventProducer eventProducer;

	@Autowired
	protected MessageSource messageSource;

	public Map<String, Object> processPaginationParameters(final Map<String, Object> recordsCriteria) {

		if (!recordsCriteria.containsKey(PaginationParameters.curPage.name())) {
			recordsCriteria.put(PaginationParameters.curPage.name(), PlatformConstants.FIRST_PAGE);
		}

		if (!recordsCriteria.containsKey(PaginationParameters.pageSize.name())) {
			recordsCriteria.put(PaginationParameters.pageSize.name(), PlatformConstants.PAGE_SIZE);
		}
		return recordsCriteria;
	}

	public void produceEvent(final Object payload, final Class<?> clazz) {
		this.eventProducer.publishEvent(payload, clazz);
	}

	public AbstractResponse handleResponse(final Object payload) {
		return new ClientResponse(HttpStatus.OK, null, payload);
	}

	public AbstractResponse handleResponse(Object payload, HttpStatus status) {
		return new ClientResponse(status, null, payload);
	}

	public AbstractResponse handleResponseWithError(Object payload, List<PlatformError> errors, HttpStatus status) {
		return new ClientResponse(status, errors, payload);
	}

	public Map<String, Object> getQueryParam(final Map<String, Object> input) {
		if (input.get(QUERY_PARAM) != null) {
			return (Map<String, Object>) input.get(QUERY_PARAM);
		}
		return null;
	}

	public String getPostParameter(final Map<String, Object> input) {
		if (input.get(POST_PARAM) != null) {
			return (String) input.get(POST_PARAM);
		}
		return null;
	}

	public Map<String, Object> getPathParam(final Map<String, Object> input) {
		if (input.get(PATHPARAM) != null) {
			return (Map<String, Object>) input.get(PATHPARAM);
		}
		return null;
	}

	@Override
	public AbstractResponse handle(final Map<String, Object> input) throws PlatformException {

		AbstractResponse abstractResponse = BeanUtil.getBean(this.getClass()).defaultHandler(input);

		if (abstractResponse != null) {
			return abstractResponse;
		}
		throw this.raiseNotImplementedException(this.getMethodName(input));
	}

	public AbstractResponse defaultHandler(final Map<String, Object> input) throws PlatformException {
		throw this.raiseNotImplementedException(this.getMethodName(input));
	}

	protected String getMethodName(final Map<String, Object> input) {
		return input.get(METHOD).toString();
	}

	protected boolean isGetMethod(final Map<String, Object> input) {
		return GET.equals(input.get(METHOD).toString());
	}

	protected boolean isPostMethod(final Map<String, Object> input) {
		return POST.equals(input.get(METHOD).toString());
	}

	protected boolean isPutMethod(final Map<String, Object> input) {
		return PUT.equals(input.get(METHOD).toString());
	}

	protected boolean isDeleteMethod(final Map<String, Object> input) {
		return DELETE.equals(input.get(METHOD).toString());
	}

	public PlatformException raiseNotImplementedException(final String methodName) {
		final PlatformError platformError = PlatformExceptionTranslatorUtil
				.createPlatformError(PlatformErrorCodes.NOT_IMPLEMENTED, new Object[] { "Not Supported" });
		return PlatformExceptionTranslatorUtil.wrapException(PlatformErrorCodes.NOT_IMPLEMENTED.value(),
				Arrays.asList(platformError));
	}

}
