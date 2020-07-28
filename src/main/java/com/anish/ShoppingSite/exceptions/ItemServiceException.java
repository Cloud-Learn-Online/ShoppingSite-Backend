package com.anish.ShoppingSite.exceptions;

public class ItemServiceException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public ItemServiceException(String e) {
		super(e);
	}	
}
