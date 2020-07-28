package com.anish.ShoppingSite.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.anish.ShoppingSite.exceptions.AccessDeniedException;
import com.anish.ShoppingSite.exceptions.ItemNotFoundException;
import com.anish.ShoppingSite.exceptions.ItemServiceException;
import com.anish.ShoppingSite.model.Item;
import com.anish.ShoppingSite.repository.ItemRepo;

public class ItemServiceTest {

	
	@Mock
	ItemRepo itemRepo;

	@Mock
	UserService userService;

	@Mock
	OrderService orderService;
	
	ItemServiceImpl itemServ;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		 itemServ = new ItemServiceImpl();
		itemServ.orderService=orderService;
		itemServ.itemRepo=itemRepo;
		itemServ.userService=userService;
	}
	
	@Test
	public void createItemTestSuccessfull() {
		
		Item item = new Item();
		item.setId(1l);
		item.setItem_name("Nirma");
		item.setDescription("Washing Powder");
		item.setItem_price(20);
		item.setQuantity(6);
		item.setRatings(5);
		item.setStockout(false);
		when(userService.isUserAdmin(3l)).thenReturn(true);
		when(itemRepo.save(item)).thenReturn(item);
		HashMap<String, Object> createItem = itemServ.createItem(3l,item);
		assertEquals("Your Item has been Created Successfully", createItem.get("message"));
		
	}
	
	@Test(expected = AccessDeniedException.class)
	public void createItemTestforAccessDeniedException() {
		
		Item item = new Item();
		item.setId(1l);
		item.setItem_name("Nirma");
		item.setDescription("Washing Powder");
		item.setItem_price(20);
		item.setQuantity(6);
		item.setRatings(5);
		item.setStockout(false);
		when(userService.isUserAdmin(3l)).thenReturn(false);
		when(itemRepo.save(item)).thenReturn(item);
		HashMap<String, Object> createItem = itemServ.createItem(3l,item);
		assertEquals("Your Item has been Created Successfully", createItem.get("message"));
		
	}
	
	@Test(expected = ItemServiceException.class)
	public void createItemTestforItemServiceException() {
		
		Item item = new Item();
		item.setId(1l);
		item.setItem_name("Nirma");
		item.setDescription("Washing Powder");
		item.setItem_price(20);
		item.setQuantity(6);
		item.setRatings(5);
		item.setStockout(false);
		when(userService.isUserAdmin(3l)).thenReturn(true);
		when(itemRepo.save(item)).thenReturn(null);
		HashMap<String, Object> createItem = itemServ.createItem(3l,item);
		assertEquals("Your Item has been Created Successfully", createItem.get("message"));
		
	}
	
	@Test
	public void updateItemTestSuccessfull() {
		
		Item item = new Item();
		item.setId(1l);
		item.setItem_name("Nirma");
		item.setDescription("Washing Powder");
		item.setItem_price(20);
		item.setQuantity(6);
		item.setRatings(5);
		item.setStockout(false);
		when(userService.isUserAdmin(3l)).thenReturn(true);
		when(itemRepo.save(item)).thenReturn(item);
		HashMap<String, Object> createItem = itemServ.updateItem(3l,item);
		assertEquals("Your Item has been Updated Successfully", createItem.get("message"));
		
	}
	
	@Test(expected = AccessDeniedException.class)
	public void updateItemTestforAccessDeniedException() {
		
		Item item = new Item();
		item.setId(1l);
		item.setItem_name("Nirma");
		item.setDescription("Washing Powder");
		item.setItem_price(20);
		item.setQuantity(6);
		item.setRatings(5);
		item.setStockout(false);
		when(userService.isUserAdmin(3l)).thenReturn(false);
		when(itemRepo.save(item)).thenReturn(item);
		HashMap<String, Object> createItem = itemServ.updateItem(3l,item);
		assertEquals("Your Item has been Created Successfully", createItem.get("message"));
		
	}
	
	@Test(expected = ItemServiceException.class)
	public void updateItemTestforItemServiceException() {
		
		Item item = new Item();
		item.setId(1l);
		item.setItem_name("Nirma");
		item.setDescription("Washing Powder");
		item.setItem_price(20);
		item.setQuantity(6);
		item.setRatings(5);
		item.setStockout(false);
		when(userService.isUserAdmin(3l)).thenReturn(true);
		when(itemRepo.save(item)).thenReturn(null);
		HashMap<String, Object> createItem = itemServ.updateItem(3l,item);
		assertEquals("Your Item has been Updated Successfully", createItem.get("message"));
		
	}
	
	
	@Test(expected = ItemServiceException.class)
	public void getItemTestforItemNotFoundException() {
		
		when(itemRepo.findAll()).thenReturn(new ArrayList<Item>());
		List<Item> items = itemServ.getItems();
		assertEquals(0, items.size());
		
	}
	
	@Test
	public void getItemTestSuccessfull() {
		Item item = new Item();
		item.setId(1l);
		item.setItem_name("Nirma");
		item.setDescription("Powder");
		item.setItem_price(20);
		item.setQuantity(6);
		item.setRatings(5);
		item.setStockout(false);
		Item item2 = new Item();
		item2.setId(2l);
		item2.setItem_name("Nirma");
		item2.setDescription("Washing Powder");
		item2.setItem_price(20);
		item2.setQuantity(6);
		item2.setRatings(5);
		item2.setStockout(false);
		Item item3 = new Item();
		item3.setId(3l);
		item3.setItem_name("Nirma");
		item3.setDescription("Detergent");
		item3.setItem_price(20);
		item3.setQuantity(6);
		item3.setRatings(5);
		item3.setStockout(false);
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(item);
		itemList.add(item2);
		itemList.add(item3);
		
		when(itemRepo.findAll()).thenReturn(itemList);
		List<Item> result = itemServ.getItems();
		assertEquals(3,result.size());
		System.out.println(result.get(1).getDescription());
		assertEquals("Washing Powder", result.get(1).getDescription());
		
	}
	
	@Test(expected = AccessDeniedException.class)
	public void deleteItemTestforAccessDeniedException() {
			
		when(userService.isUserAdmin(3l)).thenReturn(false);
		itemServ.deleteItem(3l,1);
		
	}
	
	@Test(expected = ItemNotFoundException.class)
	public void deleteItemTestforItemNotFoundException() {
		
		when(userService.isUserAdmin(3l)).thenReturn(true);
		when(itemRepo.findItemById(Mockito.anyLong())).thenReturn(null);
		itemServ.deleteItem(3l,Mockito.anyLong());
		
	}
	
	@Test
	public void deleteItemTestSuccessfull() {
		
		Item item = new Item();
		item.setId(1l);
		item.setItem_name("Nirma");
		item.setDescription("Washing Powder");
		item.setItem_price(20);
		item.setQuantity(6);
		item.setRatings(5);
		item.setStockout(false);
		when(userService.isUserAdmin(3l)).thenReturn(true);
		when(itemRepo.findItemById(Mockito.anyLong())).thenReturn(item);
		HashMap<String, Object> deleteItem = itemServ.deleteItem(3l,Mockito.anyLong());
		assertEquals("Your Item has been deleted Successfully", deleteItem.get("message"));
		
	}
	
	
}
