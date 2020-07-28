package com.anish.ShoppingSite.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anish.ShoppingSite.dto.ORDER;
import com.anish.ShoppingSite.dto.ORDERED_ITEMS;
import com.anish.ShoppingSite.exceptions.ItemNotFoundException;
import com.anish.ShoppingSite.model.Item;
import com.anish.ShoppingSite.model.Order;
import com.anish.ShoppingSite.model.OrderedItems;
import com.anish.ShoppingSite.repository.ItemRepo;

@Component
public class OrderHelper {
	
	@Autowired
	private ItemRepo itemRepo;

	public  Item isStockedOut(List<Item> items) throws ItemNotFoundException {
		try {
			for (Item item : items) {
				Item retrivedItem = itemRepo.findItemById(item.getId());
				if(retrivedItem == null)
					throw new NullPointerException("Item not found");
				
				if (item.getQuantity() > retrivedItem.getQuantity())
					return item;
			}
		}catch (NullPointerException e) {
			throw new ItemNotFoundException(e.getMessage());
		}
		return null;
	}
	
	public Order getOrdersObject(ORDER odr) {
		
		Order order = new Order();
		order.setOrder_items(new HashSet<OrderedItems>());
		for(ORDERED_ITEMS oItems:odr.getOrderedItems()) {
			OrderedItems o= new OrderedItems();
			o.setItem(oItems.getItems());
			o.setPrice(oItems.getPrice());
			o.setQuantity(oItems.getQunatity());
			order.getOrder_items().add(o);
		}
		order.setOrder_status(odr.getStatus());
		order.setPrice(odr.getPrice());
		return order;
	}
	
	public ORDER getOrderResponce(Order responceOrder) {
		
		ORDER order = new ORDER();
		order.setId(responceOrder.getId());
		order.setPrice(responceOrder.getPrice());
		order.setStatus(responceOrder.getOrder_status());
		order.setRequested_date(responceOrder.getRequested_date());
		order.setRequested_time(responceOrder.getRequested_time());
		order.setOrderedItems(new ArrayList<ORDERED_ITEMS>());
		for(OrderedItems oItems: responceOrder.getOrder_items()) {
			ORDERED_ITEMS o_Items = new ORDERED_ITEMS();
			o_Items.setId(oItems.getId());
			o_Items.setItems(oItems.getItem());
			o_Items.setPrice(oItems.getPrice());
			o_Items.setQunatity(oItems.getQuantity());
			o_Items.setStatus(oItems.getStatus());
			order.getOrderedItems().add(o_Items);
		}
		return order;
	}
}
