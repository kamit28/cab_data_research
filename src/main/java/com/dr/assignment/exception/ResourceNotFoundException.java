package com.dr.assignment.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1421151063562791616L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}
}
