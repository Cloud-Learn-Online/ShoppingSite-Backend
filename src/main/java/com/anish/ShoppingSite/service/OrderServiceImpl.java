package com.anish.ShoppingSite.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.anish.ShoppingSite.dto.ORDER;
import com.anish.ShoppingSite.exceptions.BadRequest;
import com.anish.ShoppingSite.exceptions.OrderNotFoundException;
import com.anish.ShoppingSite.exceptions.UserNotFoundException;
import com.anish.ShoppingSite.helper.OrderHelper;
import com.anish.ShoppingSite.model.Order;
import com.anish.ShoppingSite.model.OrderedItems;
import com.anish.ShoppingSite.model.Users;
import com.anish.ShoppingSite.repository.OrderRepo;
import com.anish.ShoppingSite.repository.OrderedItemRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private OrderedItemRepo orderedItemRepo;
	
	@Autowired
	private OrderHelper orderHelper;


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setOrderRepo(OrderRepo orderRepo) {
		this.orderRepo = orderRepo;
	}

	public void setOrderedItemRepo(OrderedItemRepo orderedItemRepo) {
		this.orderedItemRepo = orderedItemRepo;
	}

	public void setOrderHelper(OrderHelper orderHelper) {
		this.orderHelper = orderHelper;
	}

	@Override
	@Transactional
	public ORDER createOrder(ORDER odr,String user_id) {
		ORDER responce=null;
		Order order = orderHelper.getOrdersObject(odr);
		Users findUserByEmail = userService.findUserByEmail(user_id);
		if(findUserByEmail == null)			
			throw new UserNotFoundException("User does not exist");
		order.setUser(findUserByEmail);
		Order save = orderRepo.save(order);	
		responce=orderHelper.getOrderResponce(save);
		return responce;
	}

	@Override
	public List<ORDER> orderList(String email) {
		
		List<ORDER> responce = null;
		Users foundUser = null;
		List<Order> ordersByUserId =null;
		foundUser = userService.findUserByEmail(email);
		ordersByUserId = orderRepo.getOrdersByUsers(foundUser);
		responce = new ArrayList<ORDER>();
		for(Order o : ordersByUserId) {
			ORDER ordr = orderHelper.getOrderResponce(o);
			responce.add(ordr);
		}
		return responce;
	}

	@Override
	public HashMap<String, Object> removeOrderedItem(long orderedItemID) {
		
		OrderedItems findOrderedItemById;
		HashMap<String, Object> responce = new HashMap<String, Object>();
		findOrderedItemById = orderedItemRepo.findOrderedItemById(orderedItemID);
		if(findOrderedItemById == null)
			throw new OrderNotFoundException("Ordered item not found");
		orderedItemRepo.delete(findOrderedItemById);
		responce.put("message","Order Item for the order has been cancelled successfully");
		return responce;
	}

	@Override
	public String cancelOrder(long orderId) {
		Optional<Order> findById;
		System.out.println("Trace");
		findById = orderRepo.findById(orderId);
		if(findById.isPresent()) {
			Order order = findById.get();
			String status = order.getOrder_status();
			if(!StringUtils.isEmpty(status) && status.equalsIgnoreCase("COMPLETED"))
				throw new BadRequest("Completed order cannot be cancelled");
			
			System.out.println("Trace id");
			orderRepo.delete(order);
			System.out.println("Trace deletion");
		}
		else
			throw new OrderNotFoundException("Order not found");
		return "Order cancelled Successfully";
	}
}
