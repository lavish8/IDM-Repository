package com.identity.manager.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.identity.manager.utils.GeneralBaseConstants;
import com.identity.platform.utils.error.PlatformError;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public PlatformError constraintViolationException(ConstraintViolationException ex) {
        return new PlatformError(500, GeneralBaseConstants.CONSTRAINT_VIOLATION_SYSEXC_CODE, ex.getMessage());
    }

    @ExceptionHandler(value = { NoHandlerFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public PlatformError noHandlerFoundException(Exception ex) {
        return new PlatformError(404, GeneralBaseConstants.NOHANDLER_FOUND_CODE, ex.getMessage());
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public PlatformError unknownException(Exception ex) {
        return new PlatformError(500, GeneralBaseConstants.GLOBAL_EXCEPTION_CODE, ex.getMessage());
    }
}
