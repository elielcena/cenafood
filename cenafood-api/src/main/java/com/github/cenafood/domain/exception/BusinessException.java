package com.github.cenafood.domain.exception;

/**
 * @author elielcena
 *
 */
public abstract class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	protected BusinessException() {
		super();
	}

	protected BusinessException(String message) {
		super(message);
	}

	protected BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

}
