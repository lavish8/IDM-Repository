package com.identity.platform.auth.config;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.identity.platform.utils.error.PlatformErrorCodes;

public class CustomOauthException extends OAuth2Exception {
	
	private static final long serialVersionUID = 4952827843383690420L;

	/**
     * Instantiates a new custom oauth exception.
     *
     * @param message
     *            the message
     */
    public CustomOauthException(String message) {
        super(message);
    }

    /**
     * Instantiates a new custom oauth exception.
     *
     * @param message
     *            the message
     * @param errorAttrs
     *            the error attrs
     */
    public CustomOauthException(String message,
            Map<String, String> errorAttrs) {
        super(message);
        for (Map.Entry<String, String> errorAttr : errorAttrs.entrySet()) {
            this.addAdditionalInformation(errorAttr.getKey(),
                    errorAttr.getValue());
        }
    }

    /**
     * Instantiates a new custom oauth exception.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public CustomOauthException(String message, Throwable cause) {
        super(message, cause);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.oauth2.common.exceptions.OAuth2Exception#
     * getOAuth2ErrorCode()
     */
    @Override
    public String getOAuth2ErrorCode() {
        return PlatformErrorCodes.UNAUTHORIZED.getReasonPhrase();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.oauth2.common.exceptions.OAuth2Exception#
     * getHttpErrorCode()
     */
    @Override
    public int getHttpErrorCode() {
        return HttpStatus.UNAUTHORIZED.value();
    }

}
