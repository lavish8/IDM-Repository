/*
 * Author: Saurabh Kumar
 * Creation Date: 24-Aug-2012
 * Copyright: Nucleus Software Exports Ltd.
 * Description: This is business exception class which will be raised in case of a business exception.
 *
 * ------------------------------------------------------------------------------------------------------------------------------------
 * Revision:  Version         Last Revision Date                   Name                Function / Module affected  Modifications Done
 * ------------------------------------------------------------------------------------------------------------------------------------
 *                1.0             24/08/2012                    Saurabh Kumar             Initial Version created 
 
 */
 
package com.identity.manager.exception;

/**
 * @author maheshs1
 */
public class BusinessException extends BaseException 
{

	private static final long serialVersionUID = 1085111613828723879L;
	

	public BusinessException() {
		super();
	}

	public BusinessException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public BusinessException(final String message) {
		super(message);
	}

	public BusinessException(final Throwable cause) {
		super(cause);
	}

}