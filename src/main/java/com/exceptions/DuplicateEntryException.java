package com.exceptions;

public class DuplicateEntryException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer statusCode;

	public DuplicateEntryException(String message) {
		super(message);
	}

	public DuplicateEntryException(String message, Integer statusCode) {
		super(message);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
