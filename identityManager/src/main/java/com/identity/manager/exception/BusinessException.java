 
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