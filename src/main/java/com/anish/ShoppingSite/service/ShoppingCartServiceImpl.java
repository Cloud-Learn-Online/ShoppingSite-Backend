package com.anish.ShoppingSite.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anish.ShoppingSite.dto.CART_ITEMS;
import com.anish.ShoppingSite.exceptions.ShoppingCartException;
import com.anish.ShoppingSite.helper.ShoppingCartHelper;
import com.anish.ShoppingSite.model.CartItems;
import com.anish.ShoppingSite.model.ShoppingCart;
import com.anish.ShoppingSite.model.Users;
import com.anish.ShoppingSite.repository.CartItemsRepo;
import com.anish.ShoppingSite.repository.ShoppingCartRepo;
import com.anish.ShoppingSite.response.dto.ShoppingCartResponse;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private CartItemsRepo cartItemRepo;

	@Autowired
	ShoppingCartRepo shoppingCartRepo;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ShoppingCartHelper shoppingCartHelper;
	
	@Override
	public ShoppingCartResponse addToCart(CART_ITEMS cartItem, String email) {
		
		CartItems cart = shoppingCartHelper.getCartItemsObject(cartItem);
		Users findUserByEmail = userService.findUserByEmail(email);
		ShoppingCart shoppingCart = findUserByEmail.getShoppingCart();
		long noOfItems = cartItemRepo.countCartItemsByShoppingCart(shoppingCart);	
		
		if (noOfItems < 5) {
			if (cart.getQuantity() > 5)
				throw new ShoppingCartException("Cannot have more than 5 quantity of an Item");
			
			cart.setShoppingCart(shoppingCart);
			CartItems savedCartItem = cartItemRepo.save(cart);
			shoppingCart = savedCartItem.getShoppingCart();
			shoppingCart.getCart_items().add(savedCartItem);
			return shoppingCartHelper.prepareShoppingCartResponse(shoppingCart);
		}else 
			throw new ShoppingCartException("Cannot add more then 5 items in a cart");
	}

	@Override
	public ShoppingCartResponse getCartItems(String email) {
		Users user = userService.findUserByEmail(email);
		
		if(user.getShoppingCart().getCart_items().size() == 0) {
			throw new ShoppingCartException("No Items on the Cart");
		}
		return shoppingCartHelper.prepareShoppingCartResponse(user.getShoppingCart());
	}

	@Override
	public ShoppingCart createCart(ShoppingCart shoppingCart) throws ShoppingCartException {
		ShoppingCart save = shoppingCartRepo.save(shoppingCart);
		if(save == null)
			throw new ShoppingCartException("Cart creation failed");
		return save;

	}

	@Override
	public HashMap<String, Object> removeItemFromCart(String email, long cartItemId) {
		HashMap<String, Object> responce = new HashMap<String, Object>();
		try {
			cartItemRepo.deleteById(cartItemId);
		}catch (Exception e) {
			throw new ShoppingCartException(e.getMessage());
			// TODO: handle exception
		}
		responce.put("message", "Cart Items Deleted");
		return responce;
	}

	@Override
	public int removeAllItemsfromCart(String email) throws ShoppingCartException {

		int deleteAllItems = cartItemRepo.deleteAllItems(email);
		return deleteAllItems;
	}

	@Override
	public int getCartItemsCount(String email) throws ShoppingCartException {
		try {
			return cartItemRepo.countCartItemsByEmail(email);
		}catch(Exception  e) {
			throw new ShoppingCartException(e.getMessage());
		}
	}
	
}