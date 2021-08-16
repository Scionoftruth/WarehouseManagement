package com.wm.controllers;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.models.Customer;
import com.wm.models.Order;
import com.wm.models.Transaction;
import com.wm.repository.CustomerRepo;
import com.wm.services.CustomerService;
import com.wm.services.EmailService;
import com.wm.services.OrderService;
import com.wm.services.TransactionService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 @RestController
 @RequestMapping(value="/customer")
 @AllArgsConstructor(onConstructor=@__(@Autowired))
 @NoArgsConstructor
public class CustomerController {
	 
	 private CustomerRepo cDao;
	 private CustomerService cServ = new CustomerService(cDao);
	 private OrderService oServ;
	private TransactionService tServ;
	private EmailService mailServ;

	 //make a new order
	 //view order by email

@PostMapping("/neworder")
public ResponseEntity<String> newOrder(@RequestBody LinkedHashMap<String, String> order){
	Order orde = new Order(order.get("get the information fields"));
	Customer cust = new Customer(order.get("get the information fields"));
	Customer newcust = cServ.Verify(cust);

	Transaction trans = new Transaction(order.getOrderId(), newcust.getCustId());
	tServ.save(trans); //add trans to database
	oServ.save(orde); //add order to database
	mailServ.sendMail(orde.getOrderId());

	@GetMapping("/invoice")
	public ResponseEntity<Customer> invoice (@PathVariable"custId") int custId) {
		Customer c = cServ.getCustomerById(custId);
		if (c==null) {
			return new ResponseEntity<Customer>(c, HttpStatus.NOT_FOUND);
		}
		mailServ.sendInvMail(oServ.getAll(custId), custId);
		return new ResponseEntity<Customer>(c, HttpStatus.OK);
	}
}




}
