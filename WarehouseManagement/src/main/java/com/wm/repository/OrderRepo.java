package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.models.Order;

public interface OrderRepo extends JpaRepository<Order, Integer>{
	public List<Order> findAllOrders();
	public Order findOrderById(int id);
	

}
