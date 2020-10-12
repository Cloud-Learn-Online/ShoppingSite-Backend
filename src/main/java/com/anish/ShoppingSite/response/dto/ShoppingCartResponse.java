package com.anish.ShoppingSite.response.dto;

import java.util.Set;

public class ShoppingCartResponse {
	
	private Set<CartItemResponse> cartItems;

	public Set<CartItemResponse> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItemResponse> cartItems) {
		this.cartItems = cartItems;
	}
}
