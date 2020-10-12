package com.anish.ShoppingSite;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.anish.ShoppingSite.dto.USERS;
import com.anish.ShoppingSite.model.Item;
import com.anish.ShoppingSite.model.Roles;
import com.anish.ShoppingSite.repository.ItemRepo;
import com.anish.ShoppingSite.repository.RolesRepo;
import com.anish.ShoppingSite.service.UserService;

@Component
public class StartUp implements CommandLineRunner {
	
	@Autowired
	private RolesRepo rolesRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Override
	public void run(String... args) throws Exception {		
		Roles roleUser = new Roles();
		roleUser.setRole("ROLE_USER");
		Roles roleAdmin = new Roles();
		roleAdmin.setRole("ROLE_ADMIN");
		
		rolesRepo.saveAll(Arrays.asList(new Roles[]{roleAdmin, roleUser}));
		
		USERS usr =  new USERS();
		usr.setName("Anish");
		usr.setEmail("anish123");
		usr.setPassword("1234");
		usr.setRole("ADMIN");
		
		USERS usr1 =  new USERS();
		usr1.setName("Anish");
		usr1.setEmail("anish321");
		usr1.setPassword("1234");
		usr1.setRole("USER");
		
		userService.createUser(usr);
		userService.createUser(usr1);
		
		Item item = new Item();
		item.setItemName("Item1");
		item.setDescription("Desc1");
		item.setItemPrice(123);
		item.setQuantity(200);
		item.setRatings(1);
		item.setStockout(false);
		
		Item item1 = new Item();
		item1.setItemName("Item 2");
		item1.setDescription("Desc 2 ");
		item1.setItemPrice(95);
		item1.setQuantity(200);
		item1.setRatings(3);
		item1.setStockout(false);
		
		Item item2 = new Item();
		item2.setItemName("Item 3");
		item2.setDescription("Desc 3");
		item2.setItemPrice(30);
		item2.setQuantity(200);
		item2.setRatings(1);
		item2.setStockout(false);
		
		itemRepo.saveAll(Arrays.asList(item, item1, item2));
		
	}

}
