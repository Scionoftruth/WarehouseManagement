package com.wm.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.wm.models.Item;
import com.wm.models.Order;
import com.wm.models.Transaction;
import com.wm.repository.CustomerRepo;
import com.wm.services.CustomerService;
import com.wm.services.EmailService;
import com.wm.services.ItemService;
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
	 private ItemService iServ;

	 //make a new order
	 //view order by email

	 //@PostMapping("/neworder")

	 // when you make a new order, get the customer info, and try to locate in the database
	 //if there is a match, fill in the remaining info, if not, add to database and fill info
	 //record the customer ID on the transaction table and get the order number from transaction
	 //set the transaction to submitted
	 //add the order to the order table
	@PostMapping("/neworder")
	public ResponseEntity<String> newOrder(@RequestBody LinkedHashMap<String, String> order){  

	//needs to be a customer object
		
		Customer custId = cServ.getCustomerById(Integer.parseInt(order.get("custId")));
		
		Transaction trans = tServ.makeNew(custId);
		System.out.println(trans);
		Order next = new Order();
			next = new Order(trans.getTransId(), Integer.parseInt(order.get("quantity")), iServ.getItemById(Integer.parseInt(order.get("item"))));
//			System.out.println(next);
			next.setTransId(trans);
			oServ.addOrder(next);	
			tServ.registerTransaction(trans);
			
		
		//mailServ.sendMail(trans.getTransId(), Integer.parseInt(order.get("custId")));
		return new ResponseEntity<>("Order Created",HttpStatus.OK);
	}
 
	@PostMapping("/login")
	public ResponseEntity<Customer> loginCustomer(@RequestBody LinkedHashMap<String, String> customer) {	
		Customer c = cServ.loginUser(customer.get("email"), customer.get("password"));
		if (c == null) {
			return new ResponseEntity<Customer>(c, HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<Customer>(c, HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@RequestBody LinkedHashMap<String, String> customer){
		System.out.println(customer);
		
		Customer c = new Customer(customer.get("firstName"),customer.get("lastName"),customer.get("address"),customer.get("city"),customer.get("state"),Integer.parseInt(customer.get("zipCode")),customer.get("email"),customer.get("password"));
		if(cServ.registerCustomer(c)) {
			return new ResponseEntity<>("Customer was registered", HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Email already registered to a user", HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/invoice")
	public ResponseEntity<Customer> invoice(@RequestBody LinkedHashMap<String,String> custId) {
		Customer c = cServ.getCustomerById(Integer.parseInt(custId.get("custId")));
		if (c==null) {
			return new ResponseEntity<Customer>(c, HttpStatus.NOT_FOUND);
		}
		mailServ.sendInvMail(Integer.getInteger(custId.get("custId")));
		return new ResponseEntity<Customer>(c, HttpStatus.OK);
	}
 }




