package com.identity.platform.utils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;

import com.identity.platform.auth.constant.PlatformConstants;

// TODO: Auto-generated Javadoc
/**
 * The Class I18NUtils.
 */
@Service
public class I18NUtils {

	/** The message source. */
	@Autowired
	private MessageSource messageSource;
	
	/** Spring Environment bean */
	@Autowired
	private Environment env;
	
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(I18NUtils.class);


	/**
	 * Returns a message for the given message id and the default locale in the
	 * session context.
	 *
	 * @param messageId    The key to the messages resource file
	 * @return the message
	 */
	public String getMessage(String messageId) {
		log.info("Returning i18n text for messageId {}", messageId);
		Locale locale = LocaleContextHolder.getLocale();
		return getMessage(messageId, locale);
	}

	/**
	 * Returns a messsage for the given message id and locale.
	 *
	 * @param messageId            The key to the messages resource file
	 * @param locale            The Locale
	 * @return the message
	 */
	public String getMessage(String messageId, Locale locale) {
		return messageSource.getMessage(messageId, null, locale);
	}
	
	 /**
     * Gets the message.
     *
     * @param code
     *            the code
     * @param args
     *            the args
     * @param defaultMessage
     *            the default message
     * @return the message
     */
    public String getMessage(String code, Object[] args,
            String defaultMessage) {
        return this.getMessage(code, args, defaultMessage, getLocale());
    }
    
    /**
     * Gets the message.
     *
     * @param code
     *            the code
     * @param args
     *            the args
     * @return the message
     */
    public String getMessage(String code, Object[] args) {
        return this.getMessage(code, args, null, getLocale());
    }
    
    /**
     * Gets the message.
     *
     * @param code
     *            the code
     * @param args
     *            the args
     * @param defaultMessage
     *            the default message
     * @param locale
     *            the locale
     * @return the message
     */
    public String getMessage(String code, Object[] args, String defaultMessage,
            Locale locale) {
        Locale localParam = locale;
        if (localParam == null) {
            localParam = Locale.getDefault();
        }
        
        String localizedMessage = this.env.getProperty(localParam + "." + code);
        return getGeneralMessage(localizedMessage, args);        
    }
    
    /**
     * Gets the locale from the request.
     * 
     * @return Locale
     */
    private Locale getLocale() {
      return LocaleContextHolder.getLocale();
    }
	
	 /**
     * Creates locale independent developer message.
     * 
     * @param code
     * @param args
     * @return Developer message.
     */
    public String getDeveloperMessage(String code, Object[] args) {
      String localizedMessage = this.env.getProperty(code);
      return getGeneralMessage(localizedMessage, args);
    }
    
    /**
     * Creates general message with the given pattern and uses it to format the given arguments.
     * 
     * @param localizedMessage
     * @param args
     * @return General message.
     */
    private String getGeneralMessage(String localizedMessage, Object[] args) {
      if (StringUtils.isEmpty(localizedMessage)){
        return PlatformConstants.MSG_NOT_FOUND;
      }
      return MessageFormat.format(localizedMessage, args);
    }
    
	
	public String getMessage(ObjectError error) {
	    try {
	      for (String code : Arrays.asList(error.getCodes())) {
	        if (env.getProperty(code) != null)
	          return env.getProperty(code);
	      }
	      return error.getDefaultMessage();
	    } catch (NoSuchMessageException ex) {
	      log.error("Exception while getting error message.", ex);
	      return PlatformConstants.MSG_NOT_FOUND;
	    }
	  }

}
