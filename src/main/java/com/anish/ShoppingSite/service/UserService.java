package com.anish.ShoppingSite.service;

import java.util.HashMap;
import java.util.List;

import com.anish.ShoppingSite.dto.USERS;
import com.anish.ShoppingSite.exceptions.UserNotFoundException;
import com.anish.ShoppingSite.model.Users;

public interface UserService {
		
	public HashMap<String,Object> updateUser(USERS user);
	
	public HashMap<String,Object> removeUser(String userEmail);
	
	public HashMap<String,Object> createUser(USERS user);
	
	public List<HashMap<String,Object>> listOfUsers();
	
	public Users findUserByEmail(String email);

}
