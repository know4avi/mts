package com.cg.mts.exception;

public class AdmissionNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 3333844339605991489L;

	public AdmissionNotFoundException() {
		super();
	}

	public AdmissionNotFoundException(String message) {
		super(message);
	}
}
