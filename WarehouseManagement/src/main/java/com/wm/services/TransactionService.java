package com.wm.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.enums.StatusEnum;
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
		// New entry into the database
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
	
	public Transaction getTransactionById(int transId) {
		try {
			Transaction trans = tDao.findByTransId(transId);
			return trans;
		}catch(Exception e) {
			return null;
		}
	}

	public List<Transaction> getUnresolvedByCustomer(int custId) {
		List<Transaction> returnList = new ArrayList<Transaction>();
		List<Transaction> totalList = new ArrayList<Transaction>();
		try {
			totalList = tDao.findByCustId(custId);

			for (int i = 0; i < returnList.size(); i++) {
				// if the transaction is unresolved, add to the list
				if (totalList.get(i).getStatus() == StatusEnum.SUBMITTED | totalList.get(i).getStatus() == StatusEnum.PENDING) {
					returnList.add(totalList.get(i));
				}
			}
			return returnList;

		}catch(Exception e) {
			return null;
		}
	
	}

	public Transaction makeNew() {
		//return a new transaction that doesn't have a matching number in database
		Transaction trans = new Transaction();
		if (tDao.findById(trans.getTransId()) != null){
			makeNew();
		}
		return trans;

	}
}
