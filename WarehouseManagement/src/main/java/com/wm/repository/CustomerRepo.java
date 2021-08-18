package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wm.models.Customer;
@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	public List<Customer> findAll();
	
	public Customer findByEmail(String email);
	
	public Customer findByCustId(int custId);
	
}
