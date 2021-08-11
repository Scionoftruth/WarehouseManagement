package com.wm.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wm.models.Customer;

@Repository("customerRepo")
@Transactional
public class CustomerRepo {

	private SessionFactory sesFact;
	
	@Autowired
	public CustomerRepo(SessionFactory ses) {
		this.sesFact = ses;
	}
	
	public void insert(Customer c) {
		sesFact.getCurrentSession().save(c);
	}
	
	public void update(Customer c) {
		sesFact.getCurrentSession().update(c);
	}
	
	public Customer selectCustomerById(int id) {
		return sesFact.getCurrentSession().get(Customer.class, id);
	}
	
	public List<Customer> selectAllCustomers(){
		List<Customer> cList = sesFact.getCurrentSession()
				.createQuery("from Customer", Customer.class).list();
		return cList;
	}
}
