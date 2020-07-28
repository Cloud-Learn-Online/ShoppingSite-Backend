package com.anish.ShoppingSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anish.ShoppingSite.model.OrderedItems;

@Repository
public interface OrderedItemRepo extends JpaRepository<OrderedItems, Long> {

	@Query("select o from OrderedItems o where id=:id")
	public OrderedItems findOrderedItemById(@Param("id")long id);
}
