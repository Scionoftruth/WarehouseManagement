package com.wm.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wm.models.Customer;
import com.wm.models.Order;

@Repository("orderRepo")
@Transactional
public class OrderRepo {

	private SessionFactory sesFact;
	
	@Autowired
	public OrderRepo(SessionFactory ses) {
		this.sesFact = ses;
	}
	
	public void insert(Order order) {
		sesFact.getCurrentSession().save(order);
	}
	
	public void update(Order order) {
		sesFact.getCurrentSession().update(order);
	}
	
	public Order selectOrderById(int id) {
		return sesFact.getCurrentSession().get(Order.class, id);
	}
	
	public List<Order> selectAllOrders(){
		List<Order> oList = sesFact.getCurrentSession()
				.createQuery("from Order", Order.class).list();
		return oList;
	}
}