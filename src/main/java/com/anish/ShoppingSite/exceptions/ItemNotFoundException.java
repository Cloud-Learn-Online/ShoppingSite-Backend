package com.anish.ShoppingSite.exceptions;

public class ItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public ItemNotFoundException(String s) {
		
		super(s);
	}
}
