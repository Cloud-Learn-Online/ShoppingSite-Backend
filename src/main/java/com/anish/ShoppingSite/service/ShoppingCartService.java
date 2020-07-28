package com.anish.ShoppingSite.service;

import java.util.HashMap;

import com.anish.ShoppingSite.dto.CART_ITEMS;
import com.anish.ShoppingSite.model.ShoppingCart;

public interface ShoppingCartService {
	
	public HashMap<String,Object> addToCart(CART_ITEMS cart_item,long userId);
	
	public ShoppingCart createCart(ShoppingCart shoppingCart);
	
	public ShoppingCart getCartItems(long userId);

	public HashMap<String,Object> removeItemFromCart(long userId, long cartItemId);
	
	public int removeAllItemsfromCart(long user_Id);
	
	public int removeCart(long user_Id);

}
