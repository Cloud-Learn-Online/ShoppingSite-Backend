package com.anish.ShoppingSite.exceptions;

public class AccessDeniedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AccessDeniedException(String s) {
		super(s);
	}

}
