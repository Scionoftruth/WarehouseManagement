package com.wm.services;

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
	
	

}
