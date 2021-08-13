package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.models.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Integer>{

	public List<Transaction> findAllTransactions();
	public Transaction findById(int id);
	
}
