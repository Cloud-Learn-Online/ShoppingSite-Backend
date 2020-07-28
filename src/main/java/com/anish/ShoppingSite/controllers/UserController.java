package com.anish.ShoppingSite.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.anish.ShoppingSite.dto.MyResponce;
import com.anish.ShoppingSite.dto.USERS;
import com.anish.ShoppingSite.service.UserService;

@RestController
@RequestMapping("/shoppingSite/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/create")
	public ResponseEntity<MyResponce<HashMap<String, Object>>> createUser(@Valid @RequestBody USERS usr) {
		HashMap<String, Object> createUser = null;
		createUser = userService.createUser(usr);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(
				new MyResponce<HashMap<String, Object>>(new Date(), "success", createUser), HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<MyResponce<HashMap<String, Object>>> updateUserr(@Valid @RequestBody USERS user) {

		HashMap<String, Object> updateUser = null;
		updateUser = userService.updateUser(user);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(
				new MyResponce<HashMap<String, Object>>(new Date(), "success", updateUser), HttpStatus.OK);
	}

	@GetMapping("/list")
	public ResponseEntity<MyResponce<List<HashMap<String, Object>>>> getUser() {

		List<HashMap<String, Object>> listOfUsers = null;
		listOfUsers = userService.listOfUsers();
		return new ResponseEntity<MyResponce<List<HashMap<String, Object>>>>(
				new MyResponce<List<HashMap<String, Object>>>(new Date(), "success", listOfUsers), HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping("/{user_id}")
	public ResponseEntity<MyResponce<String>> deleteUser(@PathVariable("user_id") long userId) {

		userService.removeUser(userId);
		return new ResponseEntity<MyResponce<String>>(new MyResponce<String>(new Date(), "success", "User has bee deleted successfully"),
				HttpStatus.OK);

	}
}
