package com.anish.ShoppingSite.controllers;

import java.util.Date;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anish.ShoppingSite.dto.CART_ITEMS;
import com.anish.ShoppingSite.dto.MyResponce;
import com.anish.ShoppingSite.model.ShoppingCart;
import com.anish.ShoppingSite.service.ShoppingCartService;

@RestController
@RequestMapping("shoppingSite/{user_id}")
@CrossOrigin(origins = "http://localhost:4200")
public class ShoppingCartController {

	@Autowired
	ShoppingCartService cartService;

	@PostMapping("cart/add")
	public ResponseEntity<Object> addToCart(@Valid @RequestBody CART_ITEMS cart, @PathVariable("user_id") long userId) {

		HashMap<String, Object> shoppingCart = null;
		shoppingCart = cartService.addToCart(cart, userId);
		if (shoppingCart.containsKey("error"))
			return new ResponseEntity<Object>(shoppingCart.get("error"), HttpStatus.NOT_MODIFIED);
		return new ResponseEntity<Object>(new MyResponce<HashMap<String, Object>>(new Date(),"success",shoppingCart), HttpStatus.CREATED);
	}

	@GetMapping("cart/items")
	public ResponseEntity<MyResponce<ShoppingCart>> cartItmes(@PathVariable("user_id") long userId) {

		ShoppingCart shoppingCart = null;
		shoppingCart = cartService.getCartItems(userId);
		return new ResponseEntity<MyResponce<ShoppingCart>>(new MyResponce<ShoppingCart>(new Date(),"success",shoppingCart), HttpStatus.OK);
	}

	@DeleteMapping("cart/items/remove/{cart_item_id}")
	public ResponseEntity<MyResponce<HashMap<String, Object>>> removeCartItmes(@PathVariable("user_id") long userId,
			@PathVariable("cart_item_id") long cartItemId) {

		HashMap<String, Object> shoppingCart = null;
		shoppingCart = cartService.removeItemFromCart(userId, cartItemId);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(new MyResponce<HashMap<String, Object>>(new Date(),"success",shoppingCart), HttpStatus.OK);
	}

	@DeleteMapping("cart/items/emptycart")
	public ResponseEntity<MyResponce<String>> removeAllItems(@PathVariable("user_id") long userId) {

		int shoppingCart = 0;
		shoppingCart = cartService.removeAllItemsfromCart(userId);
		return shoppingCart >= 1 ? new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"success","Cart items are removed"), HttpStatus.OK)
				: new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"success","Nothing to delete"), HttpStatus.OK);
	}

}
