package com.anish.ShoppingSite.controllers;

import java.util.Date;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anish.ShoppingSite.dto.CART_ITEMS;
import com.anish.ShoppingSite.dto.MyResponce;
import com.anish.ShoppingSite.response.dto.ShoppingCartResponse;
import com.anish.ShoppingSite.service.ShoppingCartService;

@RestController
@RequestMapping("private")
public class ShoppingCartController {

	@Autowired
	ShoppingCartService cartService;

	@PostMapping("cart/items/add/{email}")
	public ResponseEntity<Object> addToCart(Authentication authentication, @Valid @RequestBody CART_ITEMS cart,
			@PathVariable("email") String email) throws Exception {

		isCurrentUserAuthenticated(authentication, email);

		ShoppingCartResponse shoppingCart = cartService.addToCart(cart, email);
		return new ResponseEntity<Object>(
				new MyResponce<ShoppingCartResponse>(new Date(), "success", shoppingCart, HttpStatus.CREATED),
				HttpStatus.CREATED);
	}

	@GetMapping("cart/items/{email}")
	public ResponseEntity<MyResponce<ShoppingCartResponse>> cartItmes(Authentication authentication,
			@PathVariable("email") String email) throws Exception {

		isCurrentUserAuthenticated(authentication, email);

		ShoppingCartResponse shoppingCart = cartService.getCartItems(email);
		return new ResponseEntity<MyResponce<ShoppingCartResponse>>(
				new MyResponce<ShoppingCartResponse>(new Date(), "success", shoppingCart, HttpStatus.OK),
				HttpStatus.OK);
	}

	@GetMapping("cart/items/count/{email}")
	public ResponseEntity<MyResponce<Integer>> getCountOfCartItems(Authentication authentication,
			@PathVariable("email") String email) throws Exception {

		isCurrentUserAuthenticated(authentication, email);
		int cartItemCount = cartService.getCartItemsCount(email);
		return new ResponseEntity<MyResponce<Integer>>(
				new MyResponce<Integer>(new Date(), "success", cartItemCount, HttpStatus.OK), HttpStatus.OK);
	}

	@DeleteMapping("cart/items/remove/{email}/{cart_item_id}")
	public ResponseEntity<MyResponce<HashMap<String, Object>>> removeCartItmes(Authentication authentication,
			@PathVariable("email") String email, @PathVariable("cart_item_id") long cartItemId) throws Exception {

		isCurrentUserAuthenticated(authentication, email);

		HashMap<String, Object> shoppingCart = null;
		shoppingCart = cartService.removeItemFromCart(email, cartItemId);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(
				new MyResponce<HashMap<String, Object>>(new Date(), "success", shoppingCart, HttpStatus.OK),
				HttpStatus.OK);
	}

	@DeleteMapping("cart/items/emptycart/{email}")
	public ResponseEntity<MyResponce<String>> removeAllItems(Authentication authentication,
			@PathVariable("email") String email) throws Exception {

		isCurrentUserAuthenticated(authentication, email);

		int shoppingCart = 0;
		shoppingCart = cartService.removeAllItemsfromCart(email);
		return shoppingCart >= 1 ? new ResponseEntity<MyResponce<String>>(
				new MyResponce<String>(new Date(), "success", "Cart items are removed", HttpStatus.OK), HttpStatus.OK)
				: new ResponseEntity<MyResponce<String>>(
						new MyResponce<String>(new Date(), "success", "Nothing to delete", HttpStatus.OK),
						HttpStatus.OK);
	}

	private boolean isCurrentUserAuthenticated(Authentication authentication, String email) throws Exception {
		if (!authentication.getName().equals(email)) {
			throw new Exception("Access Denied Cannot Modify the resources");
		}
		return true;
	}

}
