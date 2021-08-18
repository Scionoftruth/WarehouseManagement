package com.wm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.enums.StatusEnum;
import com.wm.models.Transaction;
import com.wm.services.CustomerService;
import com.wm.services.TransactionService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 @RestController
 @RequestMapping(value="/transaction")
 @AllArgsConstructor(onConstructor=@__(@Autowired))
 @NoArgsConstructor
public class TransactionController {

	 private TransactionService tServ;
	 private CustomerService cServ;
	 
	 @GetMapping("/gettransactions/{type}")
	 public ResponseEntity<List<Transaction>> getAllOrdersByType(@PathVariable("type")StatusEnum status){
		List<Transaction> all = tServ.getAllTransactions();
		List<Transaction> pending = new ArrayList<Transaction>();
		switch(status) {
		 	case PENDING:
		 		for(Transaction p:all) {
		 			if(p.getStatus().equals(StatusEnum.PENDING)) {
		 				pending.add(p);
		 			}
		 		}
		 		break;
			case SUBMITTED:
		 		for(Transaction p:all) {
		 			if(p.getStatus().equals(StatusEnum.SUBMITTED)) {
		 				pending.add(p);
		 			}
		 		}
		 		break;
			case CANCELED:
		 		for(Transaction p:all) {
		 			if(p.getStatus().equals(StatusEnum.CANCELED)) {
		 				pending.add(p);
		 			}
		 		}
		 		break;
			case COMPLETED:
		 		for(Transaction p:all) {
		 			if(p.getStatus().equals(StatusEnum.COMPLETED)) {
		 				pending.add(p);
		 			}
		 		}
		 		break;
			default:
				return new ResponseEntity<>(null,HttpStatus.PRECONDITION_FAILED);
		
		 }
		 return new ResponseEntity<>(pending,HttpStatus.OK);
	 }
	 
	 @GetMapping("gettransactions/{custId}")
	 public ResponseEntity<List<Transaction>> getAllCustomerOrders(@PathVariable("custId")int id){
		 List<Transaction> all = tServ.getAllTransactions();
		 List<Transaction> pending = new ArrayList<Transaction>();
		 for (Transaction t:all) {
			 if(t.getCustId().equals(cServ.getCustomerById(id))) {
				 pending.add(t);
			 }
		 }
		 return new ResponseEntity<>(pending,HttpStatus.OK);
	 }
}
