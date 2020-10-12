package com.anish.ShoppingSite.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.anish.ShoppingSite.dto.MyResponce;
import com.anish.ShoppingSite.model.Item;
import com.anish.ShoppingSite.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

	@PostMapping("admin/items/create")
	public ResponseEntity<MyResponce<HashMap<String, Object>>> createItem(
			@Valid @RequestBody Item item) {
		logger.info("Entered");
		HashMap<String, Object> createItem = null;
		createItem = itemService.createItem(item);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(
				new MyResponce<HashMap<String, Object>>(new Date(), "success", createItem, HttpStatus.CREATED),
				HttpStatus.CREATED);
	}

	@PutMapping("admin/items/update")
	public ResponseEntity<MyResponce<HashMap<String, Object>>> updateItem(
			@Valid @RequestBody Item item) {
		HashMap<String, Object> updateItem = null;
		updateItem = itemService.updateItem(item);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(
				new MyResponce<HashMap<String, Object>>(new Date(), "success", updateItem, HttpStatus.OK),
				HttpStatus.OK);
	}

	@GetMapping("public/items/list")
	@ResponseBody
	public ResponseEntity<MyResponce<List<Item>>> getItems() {
		logger.info("Entered");
		List<Item> items = null;
		items = itemService.getItems();
		MyResponce<List<Item>> responce = new MyResponce<List<Item>>(new Date(), "success", items, HttpStatus.OK);
		return new ResponseEntity<MyResponce<List<Item>>>(responce, HttpStatus.OK);
	}

	@GetMapping("public/items/list/{id}")
	@ResponseBody
	public ResponseEntity<MyResponce<Item>> getItem(@PathVariable("id") long id) {
		logger.info("Entered");
		Item item = null;
		item = itemService.getItem(id);
		MyResponce<Item> responce = new MyResponce<Item>(new Date(), "success", item, HttpStatus.OK);
		return new ResponseEntity<MyResponce<Item>>(responce, HttpStatus.OK);
	}

	@DeleteMapping("admin/items/{id}")
	public ResponseEntity<MyResponce<HashMap<String, Object>>> deleteItem(@PathVariable("id") int id) {
		HashMap<String, Object> deleteItem = null;
		deleteItem = itemService.deleteItem(id);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(
				new MyResponce<HashMap<String, Object>>(new Date(), "success", deleteItem, HttpStatus.OK),
				HttpStatus.OK);
	}
}
