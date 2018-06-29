package com.identity.platform.utils.error.exception;

import java.text.Normalizer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;

import com.identity.platform.utils.BeanUtil;

@ConfigurationProperties
@DependsOn("environment")
@Order(Ordered.LOWEST_PRECEDENCE)
@RefreshScope
public class PropertyPlaceHolderUtil {

	/**
	 * Instantiates a new property holder util.
	 */
	private PropertyPlaceHolderUtil() {
		super();
	}

	/**
	 * Get the Property Value as Integer.
	 *
	 * @param name
	 *            the name
	 * @return the integer property
	 */
	public static Integer getIntegerProperty(String name) {
		return getPropertyValue(name, Integer.class) != null ? getPropertyValue(name, Integer.class) : null;
	}

	/**
	 * Get the property value as Boolean.
	 *
	 * @param name
	 *            the name
	 * @return the boolean property
	 */
	public static Boolean getBooleanProperty(String name) {
		return getPropertyValue(name, Boolean.class) != null ? getPropertyValue(name, Boolean.class) : null;
	}

	/**
	 * Get the property value as String.
	 *
	 * @param name
	 *            the name
	 * @return the string property
	 */
	public static String getStringProperty(String name) {
		if (ObjectUtils.isEmpty(getPropertyValue(name, String.class)))
			return "";
		return Normalizer.normalize(getPropertyValue(name, String.class), Normalizer.Form.NFKC);
	}

	/**
	 * Gets the string.
	 *
	 * @param <T>
	 *            the generic type
	 * @param name
	 *            the name
	 * @param clazz
	 *            the clazz
	 * @return the string
	 */
	private static <T> T getPropertyValue(String name, Class<T> clazz) {
		return BeanUtil.getBean(Environment.class).getProperty(name, clazz);
	}

}
