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
	
	private boolean registerCustomer(Customer c) {
		try {
			cDao.save(c);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public Customer getCustomerById(int id) {
		return cDao.getById(id);
	}
	
}
