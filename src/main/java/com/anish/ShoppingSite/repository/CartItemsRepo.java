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
	@Query("delete from CartItems where shoppingCart=:cart")
	public int deleteAllItems(@Param("cart") ShoppingCart cart);
}
