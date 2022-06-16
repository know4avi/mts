package com.cg.mts.exception;

public class CourseNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 8186727808756565077L;

	public CourseNotFoundException() {
		super();
	}

	public CourseNotFoundException(String message) {
		super(message);
	}
}
