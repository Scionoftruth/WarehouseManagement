package com.wm.services;

import org.springframework.stereotype.Service;

import com.wm.models.Customer;
import com.wm.repository.CustomerRepo;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class CustomerService {
 
	private CustomerRepo cDao;
	
	public boolean registerCustomer(Customer c) {
		try {
			cDao.save(c);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public Customer loginUser(String email, String password) {
		Customer u = cDao.findByEmail(email);

		if ((u == null) | (!u.getPassword().equals(password))) {
			return null;
		}
		return u;
	}

	
	public Customer getCustomerById(int id) {
		return cDao.getById(id);
	}
	public Customer getCustomerByEmail(String email) {
		return cDao.findByEmail(email);
	}
	
public Customer verify(String email) {
	
	Customer cust = cDao.findByEmail(email);
	if (cust == null) {
		return null;
	}	
		return cust;
	}

}
