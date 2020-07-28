package com.anish.ShoppingSite.helper;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.anish.ShoppingSite.dto.USERS;
import com.anish.ShoppingSite.model.Roles;
import com.anish.ShoppingSite.model.ShoppingCart;
import com.anish.ShoppingSite.model.Users;

@Component
public class UserHelper {
		
	
	public Users getUsersObject(USERS usr){
		Users user = new Users();
		user.setId(usr.getId());
		user.setEmail(usr.getEmail());
		user.setPassword(usr.getPassword());
		user.setUser_name(usr.getName());
		user.setRoles(new Roles());
		if(usr.getRole().equalsIgnoreCase("admin"))
		{
			user.getRoles().setId(1);
			user.getRoles().setRole("ROLE_ADMIN");
		}
		else {
			user.getRoles().setId(2);
			user.getRoles().setRole("ROLE_USER");
		}
		user.setShoppingCart(new ShoppingCart());
		return user;
	}
	
	
	public HashMap<String,Object> getUserResponce(Users user){
		
		HashMap<String,Object> responce = new HashMap<String, Object>();
		responce.put("id",user.getId());
		responce.put("name",user.getUser_name());
		responce.put("email",user.getEmail());
		responce.put("roles",user.getRoles());
		return responce;
	}
	

}
