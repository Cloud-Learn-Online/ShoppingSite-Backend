package com.anish.ShoppingSite.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import com.anish.ShoppingSite.dto.MyResponce;
import com.anish.ShoppingSite.dto.ORDER;
import com.anish.ShoppingSite.repository.UsersRepo;
import com.anish.ShoppingSite.service.OrderService;

@RestController
@RequestMapping("/{user_id}")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

	@Autowired
	OrderService orderService;
	
	@Autowired
	UsersRepo userRepo;

	@PostMapping("/order/create")
	public ResponseEntity<MyResponce<ORDER>> createOrder(@Valid @RequestBody ORDER odr,@PathVariable("user_id") int user_id ) {
		ORDER response=null;
		response = orderService.createOrder(odr, user_id);
		return new ResponseEntity<MyResponce<ORDER>>(new MyResponce<ORDER>(new Date(),"success",response),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/order/list")
	public ResponseEntity<MyResponce<List<ORDER>>> orderList(@PathVariable("user_id") int user_id ) {
		List<ORDER> response=null;
		response = orderService.orderList(user_id);
		return new ResponseEntity<MyResponce<List<ORDER>>>(new MyResponce<List<ORDER>>(new Date(),"success",response),HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/order/{ordered_item}/cancelItem")
	public ResponseEntity<MyResponce<HashMap<String,Object>>> removeOrderedItem(@PathVariable("user_id") int user_id,@PathVariable("ordered_item") long orderedItemId ) {
		HashMap<String,Object> response=null;
		response = orderService.removeOrderedItem(orderedItemId);
		return new ResponseEntity<MyResponce<HashMap<String,Object>>>(new MyResponce<HashMap<String,Object>>(new Date(),"success",response),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/order/{order_id}/cancelOrder")
	public ResponseEntity<MyResponce<String>> cancelOrder(@PathVariable("user_id") int user_id,@PathVariable("order_id") long orderId ) {
		orderService.cancelOrder(orderId);
		return new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(),"success","Order Cancelled Successfully"),HttpStatus.OK);
		
	}
		
}
