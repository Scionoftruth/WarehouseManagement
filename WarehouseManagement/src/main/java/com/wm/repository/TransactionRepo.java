package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wm.models.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer>{

	public List<Transaction> findAll();
	public Transaction findById(int transactionId);
//	public Transaction findByCustId(int custId);
//	public Transaction findByEmpId(int empId);
	
	
}
