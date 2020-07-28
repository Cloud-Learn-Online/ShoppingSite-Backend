package com.anish.ShoppingSite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anish.ShoppingSite.model.Order;
import com.anish.ShoppingSite.model.Users;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

	
	@Query("select o from Order o where o.user =:user")
	public List<Order> getOrdersByUsers(@Param("user") Users user);
	
	@Query("select o from Order o where o.id =:orderId")
	public Order findOrderById(@Param("orderId") long orderId);
}
