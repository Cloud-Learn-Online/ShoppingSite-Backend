package com.anish.ShoppingSite.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="cart_items")
public class CartItems extends AbstractEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.MERGE)
	private Item item;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinTable(name="cart_cartitems",joinColumns={@JoinColumn(name="cart_items_id")}, inverseJoinColumns={@JoinColumn(name="cart_id")})
	private ShoppingCart shoppingCart;
	
	private int quantity;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItems [item=" + item + ", shoppingCart=" + shoppingCart + ", quantity=" + quantity + "]";
	}
	

}
