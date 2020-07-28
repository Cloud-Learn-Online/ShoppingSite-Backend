package com.anish.ShoppingSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anish.ShoppingSite.model.ShoppingCart;

public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {
	
}
