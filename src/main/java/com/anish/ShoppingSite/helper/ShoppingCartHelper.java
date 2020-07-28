package com.anish.ShoppingSite.helper;

import org.springframework.stereotype.Component;

import com.anish.ShoppingSite.dto.CART_ITEMS;
import com.anish.ShoppingSite.model.CartItems;

@Component
public class ShoppingCartHelper {
	
	public CartItems getCartItemsObject(CART_ITEMS c) {
		
		CartItems cartItems = new CartItems();
		cartItems.setItem(c.getItem());
		cartItems.setQuantity(c.getQuantity());
		cartItems.setShoppingCart(null);
		return cartItems;
	}

}
