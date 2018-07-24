package com.identity.platform.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.identity.platform.utils.error.PlatformErrorCodes;

import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ResponseMessage;

@Component
public class SwaggerMessageBuilder {
	
	private static final String MESSAGE = "message";
	
	private static final String DOT = ".";
	
	@Autowired
	private I18NUtils il8nUtils;
	
	public SwaggerMessageBuilder() {
	}

    /**
     * Builds the messages for HTTP GET method.
     *
     * @return the list
     */
    public List<ResponseMessage> buildHttpErrorResponseMessages() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        
        /** Getting message for Success 200 */
        responseMessages.add(getSuccessRequestMessage());
        
        /** Getting message for Error 400 */
        responseMessages.add(getBadRequestMessage());
        
        /** Getting message for Error 401 */
        responseMessages.add(getUnAuthorizedMessage());
        
        /** Getting message for Error 404 */
        responseMessages.add(getNotFoundMessage());
        
        /** Getting message for Error 405 */
        responseMessages.add(getMethodNotImplementedMessage());
        
        /** Getting message for Error 409 */
        responseMessages.add(getResourseAlreadyExistMessage());
        
        /** Getting message for Error 424 */
        responseMessages.add(getFailedDependencyMessage());
        
        /** Getting message for Error 500 */
        responseMessages.add(getInternalServerErrorMessage());
        
        /** Getting message for Error 501 */
        responseMessages.add(getNotImplementedMessage());

        return responseMessages;
    }

    private ResponseMessage getSuccessRequestMessage() {
		return new ResponseMessageBuilder()
	            .code(200)
	            .message("OK")
	            .build();
	}

	/**
     * Builds the messages for BAD_REQUEST.
     * Handling Error 400.
     * @return the ResponseMessage.
     */
    public ResponseMessage getBadRequestMessage() {
      return new ResponseMessageBuilder()
          .code(PlatformErrorCodes.BAD_REQUEST.value())
          .message(getMessage(PlatformErrorCodes.BAD_REQUEST)).build();
    }
    
    /**
     * Builds the messages for UNAUTHORIZED.
     * Handling Error 401.
     * @return the ResponseMessage.
     */
    public ResponseMessage getUnAuthorizedMessage() {
      return new ResponseMessageBuilder()
          .code(PlatformErrorCodes.UNAUTHORIZED.value())
          .message(getMessage(PlatformErrorCodes.UNAUTHORIZED)).build();
    }
    
    /**
     * Builds the messages for NOT_FOUND.
     * Handling Error 404.
     * @return the ResponseMessage.
     */
    public ResponseMessage getNotFoundMessage() {
      return new ResponseMessageBuilder()
          .code(PlatformErrorCodes.NOT_FOUND.value())
          .message(getMessage(PlatformErrorCodes.NOT_FOUND)).build();
    }
    
    /**
     * Builds the messages for METHOD_NOT_IMPLEMENTED.
     * Handling Error 405.
     * @return the ResponseMessage.
     */
    public ResponseMessage getMethodNotImplementedMessage() {
      return new ResponseMessageBuilder()
          .code(PlatformErrorCodes.METHOD_NOT_IMPLEMENTED.value())
          .message(getMessage(PlatformErrorCodes.METHOD_NOT_IMPLEMENTED)).build();
    }
    
    /**
     * Builds the messages for RESOURCE_ALREADY_EXIST.
     * Handling Error 409.
     * @return the ResponseMessage.
     */
    public ResponseMessage getResourseAlreadyExistMessage() {
      return new ResponseMessageBuilder()
          .code(PlatformErrorCodes.RESOURCE_ALREADY_EXIST.value())
          .message(getMessage(PlatformErrorCodes.RESOURCE_ALREADY_EXIST)).build();
    }
    
    /**
     * Builds the messages for FAILED_DEPENDENCY.
     * Handling Error 424.
     * @return the ResponseMessage.
     */
    public ResponseMessage getFailedDependencyMessage() {
      return new ResponseMessageBuilder()
          .code(PlatformErrorCodes.FAILED_DEPENDENCY.value())
          .message(getMessage(PlatformErrorCodes.FAILED_DEPENDENCY)).build();
    }
    
    /**
     * Builds the messages for INTERNAL_SERVER_ERROR.
     * Handling Error 500.
     * @return the ResponseMessage.
     */
    public ResponseMessage getInternalServerErrorMessage() {
      return new ResponseMessageBuilder()
          .code(PlatformErrorCodes.INTERNAL_SERVER_ERROR.value())
          .message(getMessage(PlatformErrorCodes.INTERNAL_SERVER_ERROR)).build();
    }
    
    /**
     * Builds the messages for NOT_IMPLEMENTED.
     * Handling Error 501.
     * @return the ResponseMessage.
     */
    public ResponseMessage getNotImplementedMessage() {
      return new ResponseMessageBuilder()
          .code(PlatformErrorCodes.NOT_IMPLEMENTED.value())
          .message(getMessage(PlatformErrorCodes.NOT_IMPLEMENTED)).build();
    }
    
    /**
     * returns the error message from bundle (messages.properties) based on
     * error code.
     *
     * @param errorCode the error code
     * @return the message
     */
    private String getMessage(PlatformErrorCodes errorCode) {
        return il8nUtils.getMessage(errorCode.getErrorCode() + DOT + MESSAGE);
    }
}
