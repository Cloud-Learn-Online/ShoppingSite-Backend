package com.anish.ShoppingSite.service;

import java.util.HashMap;
import java.util.List;

import com.anish.ShoppingSite.model.Item;

public interface ItemService {

	public HashMap<String, Object> createItem(Item item);

	public HashMap<String, Object> updateItem(Item item);

	public List<Item> getItems();

	public HashMap<String, Object> deleteItem(long id);

	public Item getItem(long id);

}