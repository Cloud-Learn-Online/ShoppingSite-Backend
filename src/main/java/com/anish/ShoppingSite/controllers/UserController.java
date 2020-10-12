package com.anish.ShoppingSite.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anish.ShoppingSite.dto.MyResponce;
import com.anish.ShoppingSite.dto.USERS;
import com.anish.ShoppingSite.model.Users;
import com.anish.ShoppingSite.response.dto.UserResponse;
import com.anish.ShoppingSite.security.JwtUtil;
import com.anish.ShoppingSite.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("public/user/create")
	public ResponseEntity<MyResponce<HashMap<String, Object>>> createUser(@Valid @RequestBody USERS usr) {
		HashMap<String, Object> createUser = null;
		createUser = userService.createUser(usr);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(
				new MyResponce<HashMap<String, Object>>(new Date(), "success", createUser, HttpStatus.CREATED),
				HttpStatus.CREATED);
	}

	@PutMapping("private/user/update")
	public ResponseEntity<MyResponce<HashMap<String,Object>>> updateUserr(Authentication authentication, @Valid @RequestBody USERS user) {
		System.out.println("------------- "+authentication.getName()+" -------------");
		if(!authentication.getName().equals(user.getEmail())) {
			HashMap<String,Object> hm = new HashMap<String, Object>();
			hm.put("error","Cannot modify the resource");
			return new ResponseEntity<MyResponce<HashMap<String, Object>>>(
					new MyResponce<HashMap<String, Object>>(new Date(), "error",hm, HttpStatus.FORBIDDEN),
					HttpStatus.FORBIDDEN);
		}
		HashMap<String, Object> updateUser = null;
		updateUser = userService.updateUser(user);
		return new ResponseEntity<MyResponce<HashMap<String, Object>>>(
				new MyResponce<HashMap<String, Object>>(new Date(), "success", updateUser, HttpStatus.OK),
				HttpStatus.OK);
	}

	@GetMapping("admin/user/list")
	public ResponseEntity<MyResponce<List<HashMap<String, Object>>>> getUser() {

		List<HashMap<String, Object>> listOfUsers = null;
		listOfUsers = userService.listOfUsers();
		return new ResponseEntity<MyResponce<List<HashMap<String, Object>>>>(
				new MyResponce<List<HashMap<String, Object>>>(new Date(), "success", listOfUsers, HttpStatus.OK),
				HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping("private/user/{user_id}")
	public ResponseEntity<MyResponce<String>> deleteUser(@PathVariable("user_id") String email) {

		userService.removeUser(email);
		return new ResponseEntity<MyResponce<String>>(
				new MyResponce<String>(new Date(), "success", "User has bee deleted successfully", HttpStatus.OK),
				HttpStatus.OK);

	}

	@PostMapping("/authenticate")
	public Map<String,Object> generateToken(@RequestBody USERS authRequest) throws Exception {
		
		String email = authRequest.getEmail();
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(email, authRequest.getPassword()));
		}catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		Users user = userService.findUserByEmail(email);
		System.out.println("The User has "+user.getShoppingCart().getCart_items().size());
		Map<String,Object> hm = new HashMap<>();
		hm.put("user", new UserResponse(user));
		hm.put("token",jwtUtil.generateToken(authRequest.getEmail()));
		return hm;
	}
}
