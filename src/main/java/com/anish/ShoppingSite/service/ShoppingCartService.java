package com.anish.ShoppingSite.service;

import java.util.HashMap;

import com.anish.ShoppingSite.dto.CART_ITEMS;
import com.anish.ShoppingSite.model.ShoppingCart;
import com.anish.ShoppingSite.response.dto.ShoppingCartResponse;

public interface ShoppingCartService {
	
	public ShoppingCartResponse addToCart(CART_ITEMS cart_item,String userId);
	
	public ShoppingCart createCart(ShoppingCart shoppingCart);
	
	public ShoppingCartResponse getCartItems(String userId);

	public HashMap<String, Object> removeItemFromCart(String userId, long cartItemId);
	
	public int removeAllItemsfromCart(String user_Id);

	public int getCartItemsCount(String email);

}
