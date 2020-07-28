package com.anish.ShoppingSite.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	
	public UserNotFoundException(String e) {
		super(e);
	}
}
