package com.anish.ShoppingSite.service;

import java.util.HashMap;
import java.util.List;

import com.anish.ShoppingSite.dto.USERS;
import com.anish.ShoppingSite.exceptions.UserNotFoundException;
import com.anish.ShoppingSite.model.Users;

public interface UserService {
	
	public boolean isUserAdmin(long userId) throws UserNotFoundException;
		
	public HashMap<String,Object> updateUser(USERS user);
	
	public HashMap<String,Object> removeUser(long user_id);
	
	public HashMap<String,Object> createUser(USERS user);
	
	public List<HashMap<String,Object>> listOfUsers();

	public Users findUserById(long user_id);
}
