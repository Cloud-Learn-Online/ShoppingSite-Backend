package com.anish.ShoppingSite.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anish.ShoppingSite.dto.USERS;
import com.anish.ShoppingSite.exceptions.UserNotFoundException;
import com.anish.ShoppingSite.exceptions.UserServiceExcpetion;
import com.anish.ShoppingSite.helper.UserHelper;
import com.anish.ShoppingSite.model.ShoppingCart;
import com.anish.ShoppingSite.model.Users;
import com.anish.ShoppingSite.repository.UsersRepo;

@Service("uerServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepo userRepo;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private UserHelper userServiceHelper;


	public void setUserRepo(UsersRepo userRepo) {
		this.userRepo = userRepo;
	}

	public void setShoppingcartservice(ShoppingCartService shoppingcartservice) {
		this.shoppingCartService = shoppingcartservice;
	}

	public void setUserHelper(UserHelper userServiceHelper) {
		this.userServiceHelper = userServiceHelper;
	}

	public boolean isUserAdmin(long userId) {
		Users user = null;
		user = userRepo.findUserById(userId);
		if(user == null)
			throw new UserNotFoundException("User not found");
		String string = "ROLE_ADMIN";
		if (user.getRoles().getRole().equals(string)) {
			return true;
		}
		return false;
	}
	
	public Users findUserById(long user_id){
		Users user = null;
		user = userRepo.findUserById(user_id);
		if(user == null)
			throw new UserNotFoundException("User not found");
		return user;
	}

	@Override
	public HashMap<String,Object> updateUser(USERS user) {
		Users users = null;
		Users usersObject = userServiceHelper.getUsersObject(user);
		users = userRepo.save(usersObject);
		if(users == null)
			throw new UserServiceExcpetion("User updation failed");
		return userServiceHelper.getUserResponce(users);
	}

	@Override
	@Transactional
	public HashMap<String,Object> createUser(USERS userReuestBody) throws UserNotFoundException {

		Users users= null;
		Users user = userServiceHelper.getUsersObject(userReuestBody);
		ShoppingCart shoppingCart = new ShoppingCart();
		ShoppingCart createCart = shoppingCartService.createCart(shoppingCart);
		user.setShoppingCart(createCart);
		users = userRepo.save(user);
		if(users == null)
			throw new UserServiceExcpetion("User Creation failed");
		return userServiceHelper.getUserResponce(users);
	}

	@Override
	public List<HashMap<String,Object>> listOfUsers() throws UserNotFoundException {
		List<Users> findAll = userRepo.findAll();
		if(findAll.size()==0)
		 throw new UserNotFoundException("No users found");
		List<HashMap<String,Object>> responceList = new ArrayList<HashMap<String,Object>>();
		for(Users usr:findAll) {
			HashMap<String, Object> userResponce = userServiceHelper.getUserResponce(usr);
			responceList.add(userResponce);
		}
		return responceList;
	}


	@Override
	public HashMap<String, Object> removeUser(long user_id) throws UserNotFoundException {
		
		HashMap<String,Object> responce = new HashMap<String, Object>();
		Users findUserById = userRepo.findUserById(user_id);
		if(findUserById == null)
			throw new UserNotFoundException("User not found");
		shoppingCartService.removeCart(user_id);
		userRepo.delete(findUserById);
		responce.put("message","User deletion is successful");
		return responce;
	}
}
