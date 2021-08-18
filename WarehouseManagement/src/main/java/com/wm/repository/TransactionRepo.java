package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wm.models.Transaction;
import com.wm.enums.StatusEnum;


@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer>{

	public List<Transaction> findAll();
	public Transaction findByTransId(int transactionId);
	public List<Transaction> findByCustId(int custId);
	public List<Transaction> findByStatus(StatusEnum status);
//	public Transaction findByEmpId(int empId);
	
	
}
