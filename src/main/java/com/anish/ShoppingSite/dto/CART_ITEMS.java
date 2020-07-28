package com.anish.ShoppingSite.dto;

import javax.validation.constraints.NotNull;

import com.anish.ShoppingSite.model.Item;

public class CART_ITEMS {

	private long id=0;
	
	@NotNull
	private int quantity;

	@NotNull(message="Item cannot be empty")
	private Item item;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
