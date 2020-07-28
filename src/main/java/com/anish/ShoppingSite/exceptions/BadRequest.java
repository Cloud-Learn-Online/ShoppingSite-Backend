package com.anish.ShoppingSite.exceptions;

public class BadRequest extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public BadRequest(String s) {
		super(s);
	}
}
