package com.anish.ShoppingSite.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.anish.ShoppingSite.dto.USERS;
import com.anish.ShoppingSite.exceptions.UserNotFoundException;
import com.anish.ShoppingSite.exceptions.UserServiceExcpetion;
import com.anish.ShoppingSite.helper.UserHelper;
import com.anish.ShoppingSite.model.Roles;
import com.anish.ShoppingSite.model.ShoppingCart;
import com.anish.ShoppingSite.model.Users;
import com.anish.ShoppingSite.repository.UsersRepo;

public class UserServiceTest {

	@Mock
	private UsersRepo userRepo;
	
	@Mock
	private ShoppingCartService shoppingcartservice;
	
	@Mock
	private UserHelper userHelper;

	UserServiceImpl userService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userService = new UserServiceImpl();
		userService.setShoppingcartservice(shoppingcartservice);
		userService.setUserRepo(userRepo);
		userService.setUserHelper(userHelper);
	}
	
	@Test(expected = UserNotFoundException.class)
	public void findUserByIdTestforUserNotFound() {
		when(userRepo.findUserById(Mockito.anyLong())).thenReturn(null);
		userService.findUserById(Mockito.anyLong());
	}
	
	@Test
	public void findUserByIdTestforSuccess() {
		Roles role = new Roles();
		role.setId(1);
		role.setRole("ROLE_ADMIN");
		Users user = new Users();
		user.setId(1l);
		user.setUser_name("User");
		user.setRoles(role);
		
		when(userRepo.findUserById(Mockito.anyLong())).thenReturn(user);
		Users findUserById = userService.findUserById(Mockito.anyLong());
		assertEquals(user.getUser_name(),findUserById.getUser_name());
	}

	@Test(expected = UserServiceExcpetion.class)
	public void UpdateUserTestforUserServiceException() {
		Roles role = new Roles();
		role.setId(1);
		role.setRole("ROLE_ADMIN");
		Users user = new Users();
		user.setId(1l);
		user.setUser_name("User");
		user.setRoles(role);
		USERS dtoUser = new USERS();
		dtoUser.setId(1l);
		dtoUser.setName("User");
		dtoUser.setRole("ADMIN");
		HashMap<String,Object> responce = new HashMap<String, Object>();
		responce.put("id",user.getId());
		responce.put("name",user.getUser_name());
		responce.put("email",user.getEmail());
		responce.put("roles",user.getRoles());
		when(userHelper.getUsersObject(dtoUser)).thenReturn(user);
		when(userRepo.save(user)).thenReturn(null);
		userService.updateUser(dtoUser);
	}
	
	@Test
	public void UpdateUserTestforSuccessfull() {
		Roles role = new Roles();
		role.setId(1);
		role.setRole("ROLE_ADMIN");
		Users user = new Users();
		user.setId(1l);
		user.setUser_name("User");
		user.setRoles(role);
		USERS dtoUser = new USERS();
		dtoUser.setId(1l);
		dtoUser.setName("User");
		dtoUser.setRole("ADMIN");
		HashMap<String,Object> responce = new HashMap<String, Object>();
		responce.put("id",user.getId());
		responce.put("name",user.getUser_name());
		responce.put("email",user.getEmail());
		responce.put("roles",user.getRoles());
		when(userHelper.getUsersObject(dtoUser)).thenReturn(user);
		when(userRepo.save(user)).thenReturn(user);
		when(userHelper.getUserResponce(user)).thenReturn(responce);
		HashMap<String, Object> updateUser = userService.updateUser(dtoUser);
		assertEquals(responce.get("id"),updateUser.get("id"));
	}
	
	@Test
	public void CreateUserTestforSuccessfull() {
		Roles role = new Roles();
		role.setId(1);
		role.setRole("ROLE_ADMIN");
		Users user = new Users();
		user.setId(1l);
		user.setUser_name("User");
		user.setRoles(role);
		USERS dtoUser = new USERS();
		dtoUser.setId(1l);
		dtoUser.setName("User");
		dtoUser.setRole("ADMIN");
		HashMap<String,Object> responce = new HashMap<String, Object>();
		responce.put("id",user.getId());
		responce.put("name",user.getUser_name());
		responce.put("email",user.getEmail());
		responce.put("roles",user.getRoles());
		when(userHelper.getUsersObject(dtoUser)).thenReturn(user);
		ShoppingCart shoppingCart = new ShoppingCart();
		when(shoppingcartservice.createCart(shoppingCart)).thenReturn(shoppingCart);
	    when(userRepo.save(user)).thenReturn(user);
	    when(userHelper.getUserResponce(user)).thenReturn(responce); 
		HashMap<String, Object> updateUser = userService.createUser(dtoUser);
		assertEquals(responce.get("id"),updateUser.get("id"));
	}
	
	@Test(expected = UserServiceExcpetion.class)
	public void CreateUserTestforUserServiceException() {
		Roles role = new Roles();
		role.setId(1);
		role.setRole("ROLE_ADMIN");
		Users user = new Users();
		user.setId(1l);
		user.setUser_name("User");
		user.setRoles(role);
		USERS dtoUser = new USERS();
		dtoUser.setId(1l);
		dtoUser.setName("User");
		dtoUser.setRole("ADMIN");
		HashMap<String,Object> responce = new HashMap<String, Object>();
		responce.put("id",user.getId());
		responce.put("name",user.getUser_name());
		responce.put("email",user.getEmail());
		responce.put("roles",user.getRoles());
		when(userHelper.getUsersObject(dtoUser)).thenReturn(user);
		ShoppingCart shoppingCart = new ShoppingCart();
		when(shoppingcartservice.createCart(shoppingCart)).thenReturn(shoppingCart);
	    when(userRepo.save(user)).thenReturn(null);
	    when(userHelper.getUserResponce(user)).thenReturn(responce); 
		userService.createUser(dtoUser);
	}
	
	@Test(expected = UserNotFoundException.class)
	public void listOfUsersforUserNotFoundException() {
		
		//Role 1
		Roles role1 = new Roles();
		role1.setId(1);
		role1.setRole("ROLE_ADMIN");
		//Role 2
		Roles role2 = new Roles();
		role2.setId(1);
		role2.setRole("ROLE_USER");
		//User 1
		Users user1 = new Users();
		user1.setId(1l);
		user1.setUser_name("User1");
		user1.setRoles(role1);
		//USER 1
		USERS dtoUser1 = new USERS();
		dtoUser1.setId(1l);
		dtoUser1.setName("User1");
		dtoUser1.setRole("ROLE_ADMIN");
		//User 2
		Users user2 = new Users();
		user2.setId(1l);
		user2.setUser_name("User2");
		user2.setRoles(role2);
		//USER 2
		USERS dtoUser2 = new USERS();
		dtoUser2.setId(1l);
		dtoUser2.setName("User2");
		dtoUser2.setRole("ROLE_USER");
		
		List<Users> userList = new ArrayList<Users>();
		userList.add(user1);
		userList.add(user2);
		
		HashMap<String,Object> responce = new HashMap<String, Object>();
		responce.put("id",user2.getId());
		responce.put("name",user2.getUser_name());
		responce.put("email",user2.getEmail());
		responce.put("roles",user2.getRoles());
		
		when(userRepo.findAll()).thenReturn(new ArrayList<Users>());
		userService.listOfUsers();
	}
	
	@Test
	public void listOfUsersforSuccessfull() {
		
		//Role 1
		Roles role1 = new Roles();
		role1.setId(1);
		role1.setRole("ROLE_ADMIN");
		//Role 2
		Roles role2 = new Roles();
		role2.setId(1);
		role2.setRole("ROLE_USER");
		//User 1
		Users user1 = new Users();
		user1.setId(1l);
		user1.setUser_name("User1");
		user1.setRoles(role1);
		//USER 1
		USERS dtoUser1 = new USERS();
		dtoUser1.setId(1l);
		dtoUser1.setName("User1");
		dtoUser1.setRole("ROLE_ADMIN");
		//User 2
		Users user2 = new Users();
		user2.setId(2l);
		user2.setUser_name("User2");
		user2.setRoles(role2);
		//USER 2
		USERS dtoUser2 = new USERS();
		dtoUser2.setId(2l);
		dtoUser2.setName("User2");
		dtoUser2.setRole("ROLE_USER");
		
		List<Users> userList = new ArrayList<Users>();
		userList.add(user1);
		userList.add(user2);
		
		HashMap<String,Object> responce = new HashMap<String, Object>();
		responce.put("id",user1.getId());
		responce.put("name",user1.getUser_name());
		responce.put("email",user1.getEmail());
		responce.put("roles",user1.getRoles());
		
		HashMap<String,Object> responce2 = new HashMap<String, Object>();
		responce2.put("id",user2.getId());
		responce2.put("name",user2.getUser_name());
		responce2.put("email",user2.getEmail());
		responce2.put("roles",user2.getRoles());
		
		when(userRepo.findAll()).thenReturn(userList);
		when(userHelper.getUserResponce(user1)).thenReturn(responce);
		when(userHelper.getUserResponce(user2)).thenReturn(responce2);
		List<HashMap<String, Object>> listOfUsers = userService.listOfUsers();
		assertEquals(responce2.get("name"),listOfUsers.get(1).get("name"));
		verify(userHelper,times(2)).getUserResponce(Mockito.any(Users.class));	
	}
	
	@Test(expected = UserNotFoundException.class)
	public void removeUserTestForUserNotFoundException() {
		when(userRepo.findUserById(Mockito.anyLong())).thenReturn(null);
		userService.removeUser(Mockito.anyLong());
	}
	
	@Test
	public void removeUserTestForSuccess() {
		//Role 1
		Roles role1 = new Roles();
		role1.setId(1);
		role1.setRole("ROLE_ADMIN");
		//User 1
		Users user1 = new Users();
		user1.setId(1l);
		user1.setUser_name("User1");
		user1.setRoles(role1);
		when(userRepo.findUserById(Mockito.anyLong())).thenReturn(user1);
		when(shoppingcartservice.removeCart(Mockito.anyLong())).thenReturn(1);
		doNothing().when(userRepo).delete(user1);
		HashMap<String, Object> removeUser = userService.removeUser(Mockito.anyLong());
		assertEquals("User deletion is successful", removeUser.get("message"));
	}
	
}