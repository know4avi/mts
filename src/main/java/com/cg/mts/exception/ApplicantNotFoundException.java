package com.cg.mts.exception;

public class ApplicantNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 248115636003259251L;

	public ApplicantNotFoundException() {
		super();
	}

	public ApplicantNotFoundException(String message) {
		super(message);
	}
}
