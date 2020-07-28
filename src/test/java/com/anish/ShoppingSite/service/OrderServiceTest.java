package com.anish.ShoppingSite.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

public class OrderServiceTest {
	
	@Mock
	private UserService userService;
	
	@Mock
	private OrderRepo orderRepo;
	
	@Mock
	private OrderedItemRepo orderedItemRepo;
	
	@Mock
	private OrderHelper orderHelper;
	
	private OrderServiceImpl orderServiceImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		orderServiceImpl = new OrderServiceImpl();
		orderServiceImpl.setOrderedItemRepo(orderedItemRepo);
		orderServiceImpl.setOrderHelper(orderHelper);
		orderServiceImpl.setUserService(userService);
		orderServiceImpl.setOrderRepo(orderRepo);
		
	}
	
	@Test
	public void testCreateOrderForSuccess() {
		
		Users usr= new Users();
		usr.setId(2l);
		Order odr = new Order();
		odr.setId(1l);
		odr.setPrice(1234);
		odr.setOrder_items(new HashSet<OrderedItems>());
		odr.setOrder_status("REQUESTED");
		odr.setRequested_date(new Date());
		odr.setRequested_time(new Date());
		odr.setUser(usr);
		ORDER dtoOdr= new ORDER();
		dtoOdr.setId(1l);
		dtoOdr.setOrderedItems(null);
		dtoOdr.setPrice(1234);
		
		when(orderHelper.getOrdersObject(dtoOdr)).thenReturn(odr);
		when(userService.findUserById(anyLong())).thenReturn(usr);
		when(orderRepo.save(odr)).thenReturn(odr);
		when(orderHelper.getOrderResponce(odr)).thenReturn(dtoOdr);
		ORDER createOrder = orderServiceImpl.createOrder(dtoOdr,anyLong());
		assertEquals(dtoOdr.getId(),createOrder.getId());
		assertEquals(dtoOdr.getPrice(),createOrder.getPrice());
		
	}
	@Test(expected = UserNotFoundException.class )
	public void testCreateOrderForUserNotFoundException() {
		
		Users usr= new Users();
		usr.setId(2l);
		Order odr = new Order();
		odr.setId(1l);
		odr.setPrice(1234);
		odr.setOrder_items(new HashSet<OrderedItems>());
		odr.setOrder_status("REQUESTED");
		odr.setRequested_date(new Date());
		odr.setRequested_time(new Date());
		odr.setUser(usr);
		ORDER dtoOdr= new ORDER();
		dtoOdr.setId(1l);
		dtoOdr.setOrderedItems(null);
		dtoOdr.setPrice(1234);
		
		when(orderHelper.getOrdersObject(dtoOdr)).thenReturn(odr);
		when(userService.findUserById(anyLong())).thenReturn(null);
		orderServiceImpl.createOrder(dtoOdr,anyLong());
		
		
	}
	
	
	@Test
	public void testOrderedListForSuccess() {
		Users usr= new Users();
		usr.setId(2l);
		Order odr = new Order();
		odr.setId(1l);
		odr.setPrice(1234);
		odr.setOrder_items(new HashSet<OrderedItems>());
		odr.setOrder_status("REQUESTED");
		odr.setRequested_date(new Date());
		odr.setRequested_time(new Date());
		odr.setUser(usr);
		ORDER dtoOdr= new ORDER();
		dtoOdr.setId(1l);
		dtoOdr.setOrderedItems(null);
		dtoOdr.setPrice(1234);
		Order odr1 = new Order();
		odr1.setId(3l);
		odr1.setPrice(234);
		odr1.setOrder_items(new HashSet<OrderedItems>());
		odr1.setOrder_status("REQUESTED");
		odr1.setRequested_date(new Date());
		odr1.setRequested_time(new Date());
		odr1.setUser(usr);
		ORDER dtoOdr2= new ORDER();
		dtoOdr2.setId(3l);
		dtoOdr2.setOrderedItems(null);
		dtoOdr2.setPrice(234);
		
		List<Order> odrList = new ArrayList<Order>();
		odrList.add(odr1);
		odrList.add(odr);
		when(userService.findUserById(anyLong())).thenReturn(usr);
		when(orderRepo.getOrdersByUsers(any(Users.class))).thenReturn(odrList);
		when(orderHelper.getOrderResponce(odr)).thenReturn(dtoOdr);
		when(orderHelper.getOrderResponce(odr1)).thenReturn(dtoOdr2);
		List<ORDER> createOrder = orderServiceImpl.orderList(anyLong());
		assertEquals(2,createOrder.size());
		assertEquals(dtoOdr.getPrice(),createOrder.get(1).getPrice());
	}
	
	@Test
	public void testRemoveOrderedItemForSuccess() {
		
		OrderedItems oItem = new OrderedItems();
		oItem.setId(1l);
		oItem.setPrice(123);
		when(orderedItemRepo.findOrderedItemById(anyLong())).thenReturn(oItem);
		doNothing().when(orderedItemRepo).delete(oItem);
		HashMap<String, Object> removeOrderedItem = orderServiceImpl.removeOrderedItem(1l);
		assertEquals("Order Item for the order has been cancelled successfully", removeOrderedItem.get("message"));
	}
	
	
	@Test(expected = OrderNotFoundException.class)
	public void testRemoveOrderedItemForOrderNotFoundException() {
		
		OrderedItems oItem = new OrderedItems();
		oItem.setId(1l);
		oItem.setPrice(123);
		when(orderedItemRepo.findOrderedItemById(anyLong())).thenReturn(null);
		orderServiceImpl.removeOrderedItem(1l);
	}
	
	@Test
	public void testCancelOrderForSuccess() {
		Users usr= new Users();
		usr.setId(2l);
		Order odr = new Order();
		odr.setId(1l);
		odr.setPrice(1234);
		odr.setOrder_items(new HashSet<OrderedItems>());
		odr.setOrder_status("REQUESTED");
		odr.setRequested_date(new Date());
		odr.setRequested_time(new Date());
		odr.setUser(usr);
		when(orderRepo.findOrderById(anyLong())).thenReturn(odr);
		doNothing().when(orderRepo).delete(odr);
		String cancelOrder = orderServiceImpl.cancelOrder(1l);
		assertEquals("Order cancelled Successfully", cancelOrder);
	}
	
	@Test(expected = BadRequest.class)
	public void testCancelOrderForCompleted() {
		Users usr= new Users();
		usr.setId(2l);
		Order odr = new Order();
		odr.setId(1l);
		odr.setPrice(1234);
		odr.setOrder_items(new HashSet<OrderedItems>());
		odr.setOrder_status("COMPLETED");
		odr.setRequested_date(new Date());
		odr.setRequested_time(new Date());
		odr.setUser(usr);
		when(orderRepo.findOrderById(anyLong())).thenReturn(odr);
		doNothing().when(orderRepo).delete(odr);
		orderServiceImpl.cancelOrder(1l);
	}
	
	@Test(expected = OrderNotFoundException.class)
	public void testCancelOrderForOrderNotFoundException() {
		Users usr= new Users();
		usr.setId(2l);
		Order odr = new Order();
		odr.setId(1l);
		odr.setPrice(1234);
		odr.setOrder_items(new HashSet<OrderedItems>());
		odr.setOrder_status("COMPLETED");
		odr.setRequested_date(new Date());
		odr.setRequested_time(new Date());
		odr.setUser(usr);
		when(orderRepo.findOrderById(anyLong())).thenReturn(null);
		orderServiceImpl.cancelOrder(1l);
	}
}
