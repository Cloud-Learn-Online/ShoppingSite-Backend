package com.anish.ShoppingSite.service;

import java.util.HashMap;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anish.ShoppingSite.dto.CART_ITEMS;
import com.anish.ShoppingSite.exceptions.AccessDeniedException;
import com.anish.ShoppingSite.exceptions.BadRequest;
import com.anish.ShoppingSite.exceptions.ItemNotFoundException;
import com.anish.ShoppingSite.exceptions.ShoppingCartException;
import com.anish.ShoppingSite.exceptions.UserNotFoundException;
import com.anish.ShoppingSite.helper.ShoppingCartHelper;
import com.anish.ShoppingSite.model.CartItems;
import com.anish.ShoppingSite.model.ShoppingCart;
import com.anish.ShoppingSite.model.Users;
import com.anish.ShoppingSite.repository.CartItemsRepo;
import com.anish.ShoppingSite.repository.ShoppingCartRepo;

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
	
	public void setCartItemRepo(CartItemsRepo cartItemRepo) {
		this.cartItemRepo = cartItemRepo;
	}

	public void setShoppingCartRepo(ShoppingCartRepo shoppingCartRepo) {
		this.shoppingCartRepo = shoppingCartRepo;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setShoppingCartHelper(ShoppingCartHelper shoppingCartServiceHelper) {
		this.shoppingCartHelper = shoppingCartServiceHelper;
	}

	@Override
	public HashMap<String, Object> addToCart(CART_ITEMS cartItem, long userId) {
		HashMap<String, Object> responce = new HashMap<String, Object>();
		CartItems cart = shoppingCartHelper.getCartItemsObject(cartItem);
		Users findUserById = userService.findUserById(userId);
		Set<CartItems> findAllByShoppingCart = cartItemRepo.findAllByShoppingCart(findUserById.getShoppingCart());	
		if (findAllByShoppingCart.size() < 5) {
			if (cart.getQuantity() > 5)
				throw new ShoppingCartException("Cannot have more than 5 quantity of an Item");
			cart.setShoppingCart(findUserById.getShoppingCart());
			CartItems savedCartItem = cartItemRepo.save(cart);
			ShoppingCart shoppingCart = savedCartItem.getShoppingCart();
			shoppingCart.getCart_items().add(savedCartItem);
			responce.put("ShoppingCart", shoppingCart);
		}else 
			throw new ShoppingCartException("Cannot add more then 5 items in a cart");
		return responce;
	}

	@Override
	public ShoppingCart getCartItems(long userId) {

		ShoppingCart cartByUser = null;
		Users findUserById = userService.findUserById(userId);
		if(findUserById == null)
			throw new UserNotFoundException("User not found");
		cartByUser = findUserById.getShoppingCart();
		Set<CartItems> findAllByShoppingCart = cartItemRepo.findAllByShoppingCart(cartByUser);
		if(findAllByShoppingCart.size() == 0)
			throw new ShoppingCartException("Something went wrong");
		cartByUser.setCart_items(findAllByShoppingCart);
		return cartByUser;
	}

	@Override
	public ShoppingCart createCart(ShoppingCart shoppingCart) throws ShoppingCartException {

		ShoppingCart save = shoppingCartRepo.save(shoppingCart);
		if(save == null)
			throw new ShoppingCartException("Cart creation failed");
		return save;

	}

	@Override
	public HashMap<String, Object> removeItemFromCart(long userId, long cartItemId) {
		HashMap<String, Object> responce = new HashMap<String, Object>();
			/// this will be used to verify the user only has orderd the item.
		Users findUserById = userService.findUserById(userId);
		CartItems findCartItemById = cartItemRepo.findCartItemById(cartItemId);
		if(findCartItemById == null)
			throw new ItemNotFoundException("Item not found");
		cartItemRepo.delete(findCartItemById);
		responce.put("message", "Cart Items Deleted");
		return responce;
	}

	@Override
	public int removeAllItemsfromCart(long user_Id) throws ShoppingCartException {

		int deleteAllItems = 0;

		Users findUserById = userService.findUserById(user_Id);
		ShoppingCart shoppingCart = findUserById.getShoppingCart();
		deleteAllItems = cartItemRepo.deleteAllItems(shoppingCart);
		return deleteAllItems;
	}
	
	@Override
	public int removeCart(long userId) {
		Users findUserById = userService.findUserById(userId);
		ShoppingCart shoppingCart = findUserById.getShoppingCart();
		cartItemRepo.deleteAllItems(shoppingCart);
		shoppingCartRepo.delete(shoppingCart);
		return 1;
	}

}