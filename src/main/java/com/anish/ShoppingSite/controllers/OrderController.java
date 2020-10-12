package com.anish.ShoppingSite.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
@RequestMapping("private")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	UsersRepo userRepo;

	@PostMapping("/order/create/{email}")
	public ResponseEntity<MyResponce<ORDER>> createOrder(Authentication authentication, @Valid @RequestBody ORDER odr,
			@PathVariable("email") String email) throws Exception{
		if(!authentication.getName().equals(email)) {
			throw new Exception("Access Denied Cannot Modify the resources");
		}
		ORDER response = null;
		response = orderService.createOrder(odr, email);
		return new ResponseEntity<MyResponce<ORDER>>(
				new MyResponce<ORDER>(new Date(), "success", response, HttpStatus.CREATED), HttpStatus.CREATED);

	}

	@GetMapping("/order/list/{email}")
	public ResponseEntity<MyResponce<List<ORDER>>> orderList(Authentication authentication,
			@PathVariable("email") String email) throws Exception{
		if(!authentication.getName().equals(email)) {
			throw new Exception("Access Denied Cannot Modify the resources");
		}
		
		List<ORDER> response = null;
		response = orderService.orderList(email);
		return new ResponseEntity<MyResponce<List<ORDER>>>(
				new MyResponce<List<ORDER>>(new Date(), "success", response, HttpStatus.OK), HttpStatus.OK);

	}

	@DeleteMapping("/order/cancelItem/{email}/{ordered_item}")
	public ResponseEntity<MyResponce<HashMap<String, Object>>> removeOrderedItem(Authentication authentication, @PathVariable("email") String email,
			@PathVariable("ordered_item") long orderedItemId) throws Exception {
		if(!authentication.getName().equals(email)) {
			throw new Exception("Access Denied Cannot Modify the resources");
		}
		HashMap<String, Object> response = null;
		response = orderService.removeOrderedItem(orderedItemId);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(
				new MyResponce<HashMap<String, Object>>(new Date(), "success", response, HttpStatus.OK), HttpStatus.OK);

	}

	@DeleteMapping("/order/cancelOrder/{email}/{order_id}")
	public ResponseEntity<MyResponce<String>> cancelOrder(Authentication authentication,@PathVariable("email") String email,
			@PathVariable("order_id") long orderId) throws Exception {
		if(!authentication.getName().equals(email)) {
			throw new Exception("Access Denied Cannot Modify the resources");
		}
		System.out.println("Trace");
		orderService.cancelOrder(orderId);
		return new ResponseEntity<MyResponce<String>>(
				new MyResponce<String>(new Date(), "success", "Order Cancelled Successfully", HttpStatus.OK),
				HttpStatus.OK);

	}

}
