package com.identity.manager.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.util.ObjectUtils;

import com.identity.platform.auth.config.CustomOauthException;
import com.identity.platform.auth.constant.Constants;
import com.identity.platform.utils.I18NUtils;
import com.identity.platform.utils.error.PlatformErrorCodes;

public class OauthExceptionTranslator implements WebResponseExceptionTranslator {
	
	/** The default WebResponseExceptionTranslator. */
    private WebResponseExceptionTranslator defaultTranslator = new DefaultWebResponseExceptionTranslator();
    
    @Autowired
    private I18NUtils i18nUtils;

	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
		ResponseEntity<OAuth2Exception> defaultResponse = this.defaultTranslator
                .translate(e);		

        String devMessage = Constants.UNAUTHORIZED_ACCESS;
      
        Map<String, String> errorAttrs = new LinkedHashMap<>();
        if (!ObjectUtils.isEmpty(defaultResponse.getBody())) {
          devMessage = defaultResponse.getBody().getMessage();
			errorAttrs.put(Constants.MESSAGE, i18nUtils.getMessage(PlatformErrorCodes.UNAUTHORIZED.getErrorCode()
					.concat(Constants.SEPERATOR_STRING).concat(Constants.MESSAGE)));
            errorAttrs.put(Constants.ERROR,
                    defaultResponse.getBody().getOAuth2ErrorCode());
        }
        CustomOauthException customOauthException = new CustomOauthException(
            devMessage, errorAttrs);
        
        return new ResponseEntity<>(customOauthException,
                HttpStatus.UNAUTHORIZED);
		
	}

}
