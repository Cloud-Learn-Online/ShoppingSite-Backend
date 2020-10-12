package com.anish.ShoppingSite.helper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.anish.ShoppingSite.dto.CART_ITEMS;
import com.anish.ShoppingSite.model.CartItems;
import com.anish.ShoppingSite.model.ShoppingCart;
import com.anish.ShoppingSite.response.dto.CartItemResponse;
import com.anish.ShoppingSite.response.dto.ShoppingCartResponse;

@Component
public class ShoppingCartHelper {

	public CartItems getCartItemsObject(CART_ITEMS c) {

		CartItems cartItems = new CartItems();
		cartItems.setItem(c.getItem());
		cartItems.setQuantity(c.getQuantity());
		cartItems.setShoppingCart(null);
		return cartItems;
	}

	public ShoppingCartResponse prepareShoppingCartResponse(ShoppingCart shoppingCart) {
		ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
		shoppingCartResponse.setCartItems(
			shoppingCart.getCart_items().stream().map(cartItem -> {
			CartItemResponse cartItemResponse = new CartItemResponse();
			cartItemResponse.setId(cartItem.getId());
			cartItemResponse.setItemId(cartItem.getItem().getId());
			cartItemResponse.setQuantity(cartItem.getQuantity());
			cartItemResponse.setItemName(cartItem.getItem().getItemName());
			cartItemResponse.setItemPrice(cartItem.getItem().getItemPrice());
			return cartItemResponse;
		}).collect(Collectors.toSet()));
		
		return shoppingCartResponse;
	}

}
