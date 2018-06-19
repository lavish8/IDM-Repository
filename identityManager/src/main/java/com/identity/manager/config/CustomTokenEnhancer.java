/**
 * 
 */
package com.identity.manager.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

/**
 * @author maheshs1
 *
 */
@Component("customTokenEnhancer")
public class CustomTokenEnhancer implements TokenEnhancer {

    private List<TokenEnhancer> delegates = Collections.emptyList();

public void setTokenEnhancers(List<TokenEnhancer> delegates) {
    this.delegates = delegates;
}

@Override
public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    DefaultOAuth2AccessToken tempResult = (DefaultOAuth2AccessToken) accessToken;

    final Map<String, Object> additionalInformation = new HashMap<String, Object>();
    final String infoValue = "This is my value"; 

    additionalInformation.put("myInfo", infoValue);
    additionalInformation.put("errors", new ArrayList<>());
    additionalInformation.put("status", 200);
    additionalInformation.put("metadata", new ArrayList<>());
    additionalInformation.put("data", tempResult.getRefreshToken());
    tempResult.setAdditionalInformation(additionalInformation);

    OAuth2AccessToken result = tempResult;
    for (TokenEnhancer enhancer : delegates) {
        result = enhancer.enhance(result, authentication);
    }
    return result;
}
}
