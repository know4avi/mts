package com.cg.mts.exception;

public class MtsUserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3718961055345683284L;

	public MtsUserNotFoundException() {
		super();
	}

	public MtsUserNotFoundException(String message) {
		super(message);
	}
}
