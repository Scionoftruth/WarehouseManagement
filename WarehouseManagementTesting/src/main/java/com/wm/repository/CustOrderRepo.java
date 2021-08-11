package com.wm.repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import com.wm.models.Customer;
import com.wm.models.CustOrder;
@Transactional
@Repository("custOrderRepo")

public class CustOrderRepo {

	private SessionFactory sesFact;
	
	@Autowired
	public CustOrderRepo(SessionFactory ses) {
		this.sesFact = ses;
	}
	
	public void insert(CustOrder order) {
		sesFact.getCurrentSession().save(order);
	}
	
	public void update(CustOrder order) {
		sesFact.getCurrentSession().update(order);
	}
	
	public CustOrder selectOrderById(int id) {
		return sesFact.getCurrentSession().get(CustOrder.class, id);
	}
	
	public List<CustOrder> selectAllCustOrders(){
		List<CustOrder> oList = sesFact.getCurrentSession()
				.createQuery("from CustOrder", CustOrder.class).list();
		return oList;
	}
}