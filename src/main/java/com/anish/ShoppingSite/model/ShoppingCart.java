package com.anish.ShoppingSite.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class ShoppingCart extends AbstractEntity implements Serializable{
		
	private static final long serialVersionUID = 1L;
		
	@OneToMany(mappedBy = "shoppingCart")
	private Set<CartItems> cartItems = new HashSet<>();
	
	@OneToOne(mappedBy = "shoppingCart")
    private Users user;

	public Set<CartItems> getCart_items() {
		return cartItems;
	}

	public void setCart_items(Set<CartItems> cart_items) {
		this.cartItems = cart_items;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ShoppingCart [cart_items=" + cartItems + "]";
	}
}
