package com.anish.ShoppingSite.exceptions;

public class OrderServiceException extends RuntimeException {


	private static final long serialVersionUID = 1L;
	
	public OrderServiceException(String e) {
		super(e);
	}

}
