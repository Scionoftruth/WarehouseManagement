package com.wm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.models.Transaction;
import com.wm.repository.TransactionRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class TransactionService {
	
	private TransactionRepo tDao;
	
	public boolean registerTransaction(Transaction t) {
		try {
			tDao.save(t);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	public List<Transaction> getAllTransactions() {
		return tDao.findAll();
	}
	
	public boolean getTransactionById(int id) {
		try {
			tDao.getById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public Transaction makeNew() {
		Transaction trans = new Transaction();
		if (tDao.findByOrderId(trans.getOrderId()) != null){
			makeNew();
		}
		return trans;

	}

}
