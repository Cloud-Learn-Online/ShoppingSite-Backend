package com.anish.ShoppingSite.controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.anish.ShoppingSite.dto.MyResponce;
import com.anish.ShoppingSite.exceptions.AccessDeniedException;
import com.anish.ShoppingSite.exceptions.BadRequest;
import com.anish.ShoppingSite.exceptions.ItemNotFoundException;
import com.anish.ShoppingSite.exceptions.ItemServiceException;
import com.anish.ShoppingSite.exceptions.OrderNotFoundException;
import com.anish.ShoppingSite.exceptions.OrderServiceException;
import com.anish.ShoppingSite.exceptions.ShoppingCartException;
import com.anish.ShoppingSite.exceptions.UserNotFoundException;
import com.anish.ShoppingSite.exceptions.UserServiceExcpetion;


@ControllerAdvice
@RestController
public class ExceptionController extends ResponseEntityExceptionHandler{
	
		
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<MyResponce<String>> userNotFoundException(UserNotFoundException e){
		logger.error("user not found exception"+ e.getMessage());
		return new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"failed", e.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<MyResponce<String>> orderNotFoundException(OrderNotFoundException e){
		logger.error("order not found exception"+ e.getMessage());
		return new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"failed", e.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ItemNotFoundException.class)
	public ResponseEntity<MyResponce<String>> itemNotFoundException(ItemNotFoundException e){
		logger.error("item not found exception"+ e.getMessage());
		return new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"failed", e.getMessage()),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserServiceExcpetion.class)
	public ResponseEntity<MyResponce<String>> userServiceException(UserServiceExcpetion e){
		logger.error("userservice  exception"+ e.getMessage());
		return new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"failed", e.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ItemServiceException.class)
	public ResponseEntity<MyResponce<String>> itemServiceException(ItemServiceException e){
		logger.error("itemservice  exception"+ e.getMessage());
		return new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"failed", e.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OrderServiceException.class)
	public ResponseEntity<MyResponce<String>> orderServiceException(OrderServiceException e){
		logger.error("orderservice  exception"+ e.getMessage());
		return new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"failed", e.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ShoppingCartException.class)
	public ResponseEntity<MyResponce<String>> shoppingServiceException(ShoppingCartException e){
		logger.error("shoppingcart service  exception"+ e.getMessage());
		return new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"failed", e.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<MyResponce<String>> accessDeniedException(AccessDeniedException e){
		logger.error("accessDenied  exception"+ e.getMessage());
		return new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"failed", e.getMessage()),HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(BadRequest.class)
	public ResponseEntity<MyResponce<String>> badRequestException(BadRequest e){
		logger.error("bad request  exception"+ e.getMessage());
		return new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"failed", e.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyResponce<String>> exception(Exception e){
		logger.error("exception"+ e.getMessage());
		return new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"failed","Something Went Wrong"),HttpStatus.BAD_REQUEST);
	}
	
}
