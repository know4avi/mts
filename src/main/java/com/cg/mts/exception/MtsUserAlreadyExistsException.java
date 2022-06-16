package com.cg.mts.exception;

public class MtsUserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -8415446252486332415L;

	public MtsUserAlreadyExistsException() {
		super();
	}

	public MtsUserAlreadyExistsException(String message) {
		super(message);
	}
}

