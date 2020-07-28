package com.anish.ShoppingSite.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

public class ORDER {

	private long id;
	
	@NotNull(message="Cannot be blank")
	private List<ORDERED_ITEMS> orderedItems;
	
	@NotNull(message="Price Cannot be empty")
	private int price;
	
	private Date requested_date;
	
	private Date requested_time;
	
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<ORDERED_ITEMS> getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(List<ORDERED_ITEMS> orderedItems) {
		this.orderedItems = orderedItems;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRequested_date() {
		return requested_date;
	}

	public void setRequested_date(Date requested_date) {
		this.requested_date = requested_date;
	}

	public Date getRequested_time() {
		return requested_time;
	}

	public void setRequested_time(Date requested_time) {
		this.requested_time = requested_time;
	}
	
	

}
