package com.assignment.exception;

public class InvalidIdException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public String message;

	public InvalidIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
		
	
	

}
