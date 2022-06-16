package com.cg.mts.exception;

public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = -1065320537706658760L;

	public NotAuthorizedException() {
		super();
	}

	public NotAuthorizedException(String message) {
		super(message);
	}
}
