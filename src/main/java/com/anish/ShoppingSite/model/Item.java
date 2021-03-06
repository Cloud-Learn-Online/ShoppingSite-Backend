package com.anish.ShoppingSite.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item")
public class Item extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotBlank(message="Cannot be blank")
	private String itemName;
	
	@NotNull(message="Cannot be blank")
	private int itemPrice;
	
	@Column(name = "description", columnDefinition = "TEXT default 'empty'")
	private String description;
	
	@Column(name = "ratings")
	@NotNull(message="Cannot be blank")
	private int ratings;
	
	@Column(name = "stockout", columnDefinition = "BOOLEAN default 'false'")
	private boolean stockout;

	@Column(name = "item_count")
	@NotNull
	private int quantity;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public boolean isStockout() {
		return stockout;
	}

	public void setStockout(boolean stockout) {
		this.stockout = stockout;
	}



	public String getItemName() {
		return itemName;
	}

	public void setItemName(String item_name) {
		this.itemName = item_name;
	}

	public int getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(int item_price) {
		this.itemPrice = item_price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item [id=" + super.getId() + ", item_name=" + itemName + ", item_price=" + itemPrice + ", quantity=" + quantity
				+ "]";
	}

}
