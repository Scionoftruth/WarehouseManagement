package com.wm.services;

import com.wm.models.Order;
import com.wm.repository.OrderRepo;

public class OrderService {

	OrderRepo oDao;

	public Order getOrderById(int num) {
		return oDao.getById(num);
	
	//public boolean update(Order order) {
		
	//}
	
	
}
}
