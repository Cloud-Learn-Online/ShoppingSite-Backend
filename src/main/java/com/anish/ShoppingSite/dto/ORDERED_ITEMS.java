package com.anish.ShoppingSite.dto;

import javax.validation.constraints.NotNull;

import com.anish.ShoppingSite.model.Item;

public class ORDERED_ITEMS {
	
	private long id;
	
	@NotNull(message="Item details needed")
	private Item items;
	
	@NotNull(message="Price Item is empty")
	private int price;
	
	private String status;
	
	@NotNull(message="Quantity Item is empty")
	private int qunatity;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Item getItems() {
		return items;
	}
	public void setItems(Item items) {
		this.items = items;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQunatity() {
		return qunatity;
	}
	public void setQunatity(int qunatity) {
		this.qunatity = qunatity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
