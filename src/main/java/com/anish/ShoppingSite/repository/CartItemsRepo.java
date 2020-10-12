package com.anish.ShoppingSite.repository;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anish.ShoppingSite.model.CartItems;
import com.anish.ShoppingSite.model.ShoppingCart;

@Repository
public interface CartItemsRepo extends JpaRepository<CartItems, Long> {

	@Query("select s from CartItems s where s.shoppingCart=:cart")
	public Set<CartItems> findAllByShoppingCart(@Param("cart") ShoppingCart cart);
	
	@Query("select s from CartItems s where s.id=:cart_id")
	public CartItems findCartItemById(@Param("cart_id") long cartId);
	
	@Transactional
	@Modifying
	@Query("delete from CartItems p where p.shoppingCart=(select s.shoppingCart from Users s where s.email =:email)")
	public int deleteAllItems(@Param("email") String email);

	@Query("select count(c) from CartItems c where c.shoppingCart=:cart")
	public int countCartItemsByShoppingCart(@Param("cart") ShoppingCart shoppingCart);
	
	@Query("select count(c) from CartItems c where c.shoppingCart = "
			+ "(select u.shoppingCart from Users u where u.email =:email)")
	public int countCartItemsByEmail(@Param("email") String email);
}
