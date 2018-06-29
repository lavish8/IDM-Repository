package com.identity.platform.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanUtil implements ApplicationContextAware {

	/** The application context. */
	private static ApplicationContext applicationContext;

	/**
	 * Instantiates a new bean util.
	 */
	public BeanUtil() {
		super();
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}

	/**
	 * Gets the bean.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clzz
	 *            the clzz
	 * @return the bean
	 */
	public static <T> T getBean(Class<T> clzz) {
		return BeanUtil.applicationContext.getBean(clzz);
	}

	/**
	 * Gets the bean.
	 *
	 * @param <T>
	 *            the generic type
	 * @param beanName
	 *            the bean name
	 * @param clzz
	 *            the clzz
	 * @return the bean
	 */
	public static <T> T getBean(String beanName, Class<T> clzz) {
		return BeanUtil.applicationContext.getBean(beanName, clzz);
	}
	
	public static Object getBean(String beanName) {
		return BeanUtil.applicationContext.getBean(beanName);
	}

}
