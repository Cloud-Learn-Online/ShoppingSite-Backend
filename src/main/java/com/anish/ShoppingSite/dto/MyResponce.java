package com.anish.ShoppingSite.dto;

import java.util.Date;

public class MyResponce<T> {
	
	public Date timeStmamp;
	public String status;
	public T data;
	
	public MyResponce(Date timeStmamp, String status,T message) {
		//super();
		this.timeStmamp = timeStmamp;
		this.status = status;
		this.data = message;
	}



	@Override
	public String toString() {
		return "Responce [timeStmamp=" + timeStmamp + ", status=" + status + ", data=" + data + "]";
	}
	
	

}
