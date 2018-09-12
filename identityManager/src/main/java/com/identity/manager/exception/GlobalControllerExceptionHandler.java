package com.identity.manager.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.identity.manager.utils.GeneralBaseConstants;
import com.identity.platform.auth.constant.Constants;
import com.identity.platform.utils.I18NUtils;
import com.identity.platform.utils.error.PlatformError;
import com.identity.platform.utils.error.PlatformErrorCodes;
import com.identity.platform.utils.error.exception.PlatformExceptionTranslatorUtil;

@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private I18NUtils i18nUtils;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ObjectError error = new ObjectError("Validation Failed", ex.getBindingResult().toString());
		PlatformError errorDetails = PlatformExceptionTranslatorUtil.createPlatformError(PlatformErrorCodes.BAD_REQUEST,
				error);
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public PlatformError constraintViolationException(ConstraintViolationException ex) {
		return new PlatformError(HttpStatus.BAD_REQUEST.value(), GeneralBaseConstants.CONSTRAINT_VIOLATION_SYSEXC_CODE,
				i18nUtils.getMessage(PlatformErrorCodes.BAD_REQUEST.getErrorCode().concat(Constants.SEPERATOR_STRING)
						.concat(Constants.MESSAGE)),
				ex.getMessage(), ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public PlatformError unknownException(Exception ex) {
		return new PlatformError(HttpStatus.INTERNAL_SERVER_ERROR.value(), GeneralBaseConstants.GLOBAL_EXCEPTION_CODE,
				i18nUtils.getMessage(PlatformErrorCodes.INTERNAL_SERVER_ERROR.getErrorCode()
						.concat(Constants.SEPERATOR_STRING).concat(Constants.MESSAGE)),
				ex.getMessage(), ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { AuthenticationCredentialsNotFoundException.class })
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public PlatformError unknownException(AuthenticationCredentialsNotFoundException ex) {
		return new PlatformError(HttpStatus.UNAUTHORIZED.value(), PlatformErrorCodes.UNAUTHORIZED.getErrorCode(),
				i18nUtils.getMessage(PlatformErrorCodes.UNAUTHORIZED.getErrorCode().concat(Constants.SEPERATOR_STRING)
						.concat(Constants.MESSAGE)),
				ex.getMessage(), ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { OptimisticLockingFailureException.class })
	@ResponseStatus(HttpStatus.CONFLICT)
	public PlatformError unknownException(OptimisticLockingFailureException ex) {
		return new PlatformError(HttpStatus.CONFLICT.value(), PlatformErrorCodes.CONFLICT.getErrorCode(),
				i18nUtils.getMessage(PlatformErrorCodes.CONFLICT.getErrorCode().concat(Constants.SEPERATOR_STRING)
						.concat(Constants.MESSAGE)),
				ex.getMessage(), ex.getLocalizedMessage());
	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public PlatformError unknownException(IllegalArgumentException ex) {
		return new PlatformError(HttpStatus.BAD_REQUEST.value(), PlatformErrorCodes.BAD_REQUEST.getErrorCode(),
				i18nUtils.getMessage(PlatformErrorCodes.BAD_REQUEST.getErrorCode().concat(Constants.SEPERATOR_STRING)
						.concat(Constants.MESSAGE)),
				ex.getMessage(), ex.getLocalizedMessage());
	}

}
