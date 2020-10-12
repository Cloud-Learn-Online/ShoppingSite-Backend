package com.anish.ShoppingSite.service;

import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anish.ShoppingSite.exceptions.AccessDeniedException;
import com.anish.ShoppingSite.exceptions.ItemNotFoundException;
import com.anish.ShoppingSite.exceptions.ItemServiceException;
import com.anish.ShoppingSite.model.Item;
import com.anish.ShoppingSite.repository.ItemRepo;

@Service("itemServiceImpl")
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemRepo itemRepo;

	@Autowired
	UserService userService;

	@Autowired
	OrderService orderService;

	@Transactional
	@Override
	public HashMap<String, Object> createItem(Item item) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		Item save = itemRepo.save(item);
		if (save == null)
			throw new ItemServiceException("Item Creation failed");
		hm.put("message", "Your Item has been Created Successfully");
		hm.put("Item", save);
		return hm;
	}

	@Transactional
	@Override
	public HashMap<String, Object> updateItem(Item item) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		Item save = itemRepo.save(item);
		if (save == null)
			throw new ItemServiceException("Updating Item failed");

		hm.put("message", "Your Item has been Updated Successfully");
		hm.put("Item", save);
		return hm;
	}

	@Override
	public List<Item> getItems() {
		List<Item> findAll = itemRepo.findAll();
		if (findAll.size() == 0)
			throw new ItemServiceException("No Items found");
		return findAll;
	}

	@Transactional
	@Override
	public HashMap<String, Object> deleteItem(long id) {

		HashMap<String, Object> hm = new HashMap<String, Object>();
		Item item1;
		item1 = itemRepo.findItemById(id);
		if (item1 == null)
			throw new ItemNotFoundException("Item Not found");

		itemRepo.delete(item1);
		hm.put("message", "Your Item has been deleted Successfully");
		return hm;
	}

	@Override
	public Item getItem(long id) {
		Item item1;
		item1 = itemRepo.findItemById(id);
		if (item1 == null)
			throw new ItemNotFoundException("Item Not found");
		return item1;
	}

}
