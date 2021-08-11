package com.wm.repository;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wm.models.OrderStatus;


@Repository("orderStatusRepo")
@Transactional
public class OrderStatusRepo {

	private SessionFactory sesFact;
	
	@Autowired
	public OrderStatusRepo(SessionFactory ses){
		this.sesFact = ses;
	}
	
	public void insert (OrderStatus o){
		sesFact.getCurrentSession().save(o);
	}
	
	public void update (OrderStatus o) {
		sesFact.getCurrentSession().update(o);
	}
	
	public OrderStatus selectOrderStatusById (int id) {
		return sesFact.getCurrentSession().get(OrderStatus.class, id);
	}
	
	public List<OrderStatus> selectAllOrderStatus() {
		List<OrderStatus> oList = sesFact.getCurrentSession()
		.createQuery("from OrderStatus", OrderStatus.class).list();
		return oList;	
	}
}
