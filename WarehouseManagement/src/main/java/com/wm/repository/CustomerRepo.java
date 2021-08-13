package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.models.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	public List<Customer> findAll();
	
	public Customer findByEmail(String email);
	
	public Customer findById(int id);
	
}
