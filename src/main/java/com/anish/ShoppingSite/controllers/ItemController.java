package com.anish.ShoppingSite.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anish.ShoppingSite.dto.MyResponce;
import com.anish.ShoppingSite.model.Item;
import com.anish.ShoppingSite.service.ItemService;


@RestController
@RequestMapping("/shoppingSite/")
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@PostMapping("{user_id}/Items/create")
	public ResponseEntity<MyResponce<HashMap<String, Object>>> createItem(@PathVariable("user_id") int userId,@Valid @RequestBody Item item)
	{
		logger.info("Entered");
		HashMap<String, Object> createItem= null;
		createItem = itemService.createItem(userId,item);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(new MyResponce<HashMap<String, Object>>(new Date(),"success",createItem),HttpStatus.CREATED);
	}
	

	@PutMapping("{user_id}/Items/update")
	public ResponseEntity<MyResponce<HashMap<String, Object>>> updateItem(@PathVariable("user_id") int userId,@Valid @RequestBody Item item)
	{
		HashMap<String, Object> updateItem= null;
		updateItem = itemService.updateItem(userId, item);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(new MyResponce<HashMap<String, Object>>(new Date(),"success",updateItem),HttpStatus.OK);
	}
	
	
	@GetMapping("/Items/list")
	@ResponseBody
	public ResponseEntity<MyResponce<List<Item>>> getItems()
	{
		logger.info("Entered");
		List<Item> items=null;
		items = itemService.getItems();
		MyResponce<List<Item>> responce = new MyResponce<List<Item>>(new Date(),"success",items);
		return new ResponseEntity<MyResponce<List<Item>>>(responce,HttpStatus.OK);
	}
	
	@GetMapping("/Items/list/{id}")
	@ResponseBody
	public ResponseEntity<MyResponce<Item>> getItem(@PathVariable("id") long id)
	{
		logger.info("Entered");
		Item item=null;
		item = itemService.getItem(id);
		MyResponce<Item> responce = new MyResponce<Item>(new Date(),"success",item);
		return new ResponseEntity<MyResponce<Item>>(responce,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("{user_id}/Items/{id}")
	public ResponseEntity<MyResponce<HashMap<String, Object>>> deleteItem(@PathVariable("user_id") int userId,@PathVariable("id") int id)
	{
		HashMap<String, Object> deleteItem=null;
		deleteItem = itemService.deleteItem(userId,id);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(new MyResponce<HashMap<String, Object>>(new Date(),"success",deleteItem),HttpStatus.OK);
	}
}
