package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wm.models.Order;
@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{
	public List<Order> findAll();
	public Order findById(int id);
	//public List<Order> findOrderList(Order order);
	public Order findById(Order orderId);

}
