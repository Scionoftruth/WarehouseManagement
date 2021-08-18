package com.wm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.models.Order;
import com.wm.repository.OrderRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class OrderService {

	OrderRepo oDao;
	
	public List<Order> getAllOrders(){
		return oDao.findAll();
		
	}

	public Order getOrderById(int num) {
		return oDao.getById(num);
	}
	
	
	public void addOrder(Order order) {
		oDao.save(order);
	}
	
	

	public Object getAll(int custId) {
		//this should grab all unresolved orders based on cust id
		// TODO Auto-generated method stub
		return null;
	}
}
