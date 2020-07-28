package com.anish.ShoppingSite.service;

import java.util.HashMap;
import java.util.List;

import com.anish.ShoppingSite.dto.ORDER;

public interface OrderService {


	public List<ORDER> orderList(long user_id);

	public ORDER createOrder(ORDER order, long user_id);

	public HashMap<String,Object> removeOrderedItem(long orderedItemID);
	
	public String  cancelOrder(long orderedItemID);

}
