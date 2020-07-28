package com.anish.ShoppingSite.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.anish.ShoppingSite.dto.CART_ITEMS;
import com.anish.ShoppingSite.exceptions.ShoppingCartException;
import com.anish.ShoppingSite.helper.ShoppingCartHelper;
import com.anish.ShoppingSite.model.CartItems;
import com.anish.ShoppingSite.model.ShoppingCart;
import com.anish.ShoppingSite.model.Users;
import com.anish.ShoppingSite.repository.CartItemsRepo;
import com.anish.ShoppingSite.repository.ShoppingCartRepo;

public class ShoppingCartServiceImplTest {

	@Mock
	private CartItemsRepo cartItemRepo;

	@Mock
	ShoppingCartRepo shoppingCartRepo;

	@Mock
	private UserService userService;
	
	@Mock
	private ShoppingCartHelper shoppingCartHelper;
	
	private ShoppingCartServiceImpl shoppingService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		shoppingService = new ShoppingCartServiceImpl();
		shoppingService.setCartItemRepo(cartItemRepo);
		shoppingService.setShoppingCartRepo(shoppingCartRepo);
		shoppingService.setShoppingCartHelper(shoppingCartHelper);
		shoppingService.setUserService(userService);
		
	}
	
	@Test
	public void testAddToCart() {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(123l);
		Users usr= new Users();
		usr.setId(2l);
		usr.setShoppingCart(shoppingCart);
		CART_ITEMS dtoCartItem = new CART_ITEMS();
		dtoCartItem.setId(1l);
		dtoCartItem.setQuantity(2);
		CartItems cartItem = new CartItems();
		cartItem.setId(1l);
		cartItem.setQuantity(2);
		Set<CartItems> cartItemsList = new HashSet<CartItems>();
		shoppingCart.setCart_items(new HashSet<CartItems>());
		shoppingCart.getCart_items().add(cartItem);
	
		when(shoppingCartHelper.getCartItemsObject(dtoCartItem)).thenReturn(cartItem);
		when(userService.findUserById(anyLong())).thenReturn(usr);
		when(cartItemRepo.findAllByShoppingCart(shoppingCart)).thenReturn(cartItemsList);
		when(cartItemRepo.save(cartItem)).thenReturn(cartItem);
		HashMap<String, Object> addToCart = shoppingService.addToCart(dtoCartItem, anyLong());
		assertEquals(shoppingCart.getCart_items().size(),((ShoppingCart)addToCart.get("ShoppingCart")).getCart_items().size());
	}
	
	@Test(expected = ShoppingCartException.class)
	public void testAddToCartForShoppingCartException() {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(123l);
		Users usr= new Users();
		usr.setId(2l);
		usr.setShoppingCart(shoppingCart);
		CART_ITEMS dtoCartItem = new CART_ITEMS();
		dtoCartItem.setId(1l);
		dtoCartItem.setQuantity(2);
		CartItems cartItem = new CartItems();
		cartItem.setId(1l);
		cartItem.setQuantity(2);
		cartItem.setShoppingCart(shoppingCart);
		Set<CartItems> cartItemsList = new HashSet<CartItems>();
		CartItems c1 = new CartItems();
		CartItems c2 = new CartItems();
		CartItems c3 = new CartItems();
		CartItems c4 = new CartItems();
		CartItems c5 = new CartItems();
		cartItemsList.add(c1);
		cartItemsList.add(c2);
		cartItemsList.add(c3);
		cartItemsList.add(c4);
		cartItemsList.add(c5);
		
		shoppingCart.setCart_items(new HashSet<CartItems>());
		shoppingCart.getCart_items().add(cartItem);
	
		when(shoppingCartHelper.getCartItemsObject(dtoCartItem)).thenReturn(cartItem);
		when(userService.findUserById(anyLong())).thenReturn(usr);
		when(cartItemRepo.findAllByShoppingCart(shoppingCart)).thenReturn(cartItemsList);
		shoppingService.addToCart(dtoCartItem, anyLong());
	}
	
	@Test(expected = ShoppingCartException.class)
	public void testAddToCartForShoppingCartExceptionCase2() {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setId(123l);
		Users usr= new Users();
		usr.setId(2l);
		usr.setShoppingCart(shoppingCart);
		CART_ITEMS dtoCartItem = new CART_ITEMS();
		dtoCartItem.setId(1l);
		dtoCartItem.setQuantity(2);
		CartItems cartItem = new CartItems();
		cartItem.setId(1l);
		cartItem.setQuantity(6);
		Set<CartItems> cartItemsList = new HashSet<CartItems>();
		shoppingCart.setCart_items(new HashSet<CartItems>());
		shoppingCart.getCart_items().add(cartItem);
		when(shoppingCartHelper.getCartItemsObject(dtoCartItem)).thenReturn(cartItem);
		when(userService.findUserById(anyLong())).thenReturn(usr);
		when(cartItemRepo.findAllByShoppingCart(shoppingCart)).thenReturn(cartItemsList);
		shoppingService.addToCart(dtoCartItem, anyLong());
	}
	

//	@Test
//	public void testGetCartItems() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCreateCart() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveItemFromCart() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveAllItemsfromCart() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testRemoveCart() {
//		fail("Not yet implemented");
//	}

}
