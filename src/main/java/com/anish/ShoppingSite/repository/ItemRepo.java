package com.anish.ShoppingSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anish.ShoppingSite.model.Item;

public interface ItemRepo extends JpaRepository<Item, Long> {
	
	@Query("from Item where id=:id")
	public Item findItemById(@Param("id")long id) throws NullPointerException;

	
}
