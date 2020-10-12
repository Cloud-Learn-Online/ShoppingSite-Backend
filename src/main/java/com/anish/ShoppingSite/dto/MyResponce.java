package com.anish.ShoppingSite.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class MyResponce<T> {
	
	public Date timeStmamp;
	public T data;
	public String message;
	public HttpStatus status;
	
	public MyResponce(Date timeStmamp, String message,T data,HttpStatus status) {
		//super();
		this.timeStmamp = timeStmamp;
		this.status = status;
		this.data = data;
		this.message=message;
	}



	@Override
	public String toString() {
		return "Responce [timeStmamp=" + timeStmamp + ", status=" + status + ", data=" + data + "]";
	}
	
	

}
