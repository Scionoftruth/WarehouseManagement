package com.wm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.LinkedHashMap;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wm.services.EmployeeService;
import com.wm.models.Employee;
import com.wm.enums.RoleEnum;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 @RestController
 @RequestMapping(value="/employee")
 @AllArgsConstructor(onConstructor=@__(@Autowired))
 @NoArgsConstructor
public class EmployeeController {
	//Update customer information
	
	//View all submitted/cancelled/complete  orders- in TransactionController
	//View their accepted orders- in TransactionController
	//View orders of a specific customer
	 
	
	
	//Check stock levels by item
	//Add new stock, including new item 
	//Mark an item as discontinued 
	 
	//Accept order - DONE
	//Cancel or complete orders -DONE
	
	private EmployeeService eServ;

	//login
	@PostMapping("/login")
	public ResponseEntity<Employee> loginEmployee(@RequestBody LinkedHashMap<String, String> employee){
    	Employee e = eServ.loginEmployee(Integer.parseInt(employee.get("id")), employee.get("password"));
    	
    	if (e == null){
        	return new ResponseEntity<Employee>(e, HttpStatus.FORBIDDEN);
      }
      return new ResponseEntity<Employee>(e, HttpStatus.OK);
  	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> createEmployee(@RequestBody LinkedHashMap<String, String> employee){
		System.out.println(employee);
		
		RoleEnum role = RoleEnum.valueOf(employee.get("role").toUpperCase());
		
		Employee e = new Employee(employee.get("firstName"), employee.get("lastName"), employee.get("email"), employee.get("password"), role);
		
		if(eServ.registerEmployee(e)) {
			return new ResponseEntity<>("User was registered", HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Username or email already registered to a user", HttpStatus.CONFLICT);
		}
	}
	
	//Accept an order
	@PostMapping("/accept")
	public ResponseEntity<String> acceptOrder(@RequestBody LinkedHashMap<String, String> order){
		
		boolean isAccept = eServ.acceptOrder( Integer.parseInt(order.get("orderId")), Integer.parseInt(order.get("employeeId")) );
		
		if (isAccept){
			return new ResponseEntity<>("Order has been Accepted marked PENDING", HttpStatus.OK);
		} else {
      		return new ResponseEntity<>("Order cannot be found in the database", HttpStatus.NOT_MODIFIED);
    	}
		
	}
	//Complete order
	@PostMapping("/complete")
	public ResponseEntity<String> completeOrder(@RequestBody LinkedHashMap<String, String> order){
		
		boolean isComplete = eServ.completeOrder( Integer.parseInt(order.get("orderId")), Integer.parseInt(order.get("employeeId")) );
		
		if (isComplete){
			return new ResponseEntity<>("Order has marked COMPLETED", HttpStatus.OK);
		} else {
      		return new ResponseEntity<>("Order cannot be found in the database", HttpStatus.NOT_MODIFIED);
    	}
		
	}
	
	//Cancel order
	@PostMapping("/cancel")
	public ResponseEntity<String> cancelOrder(@RequestBody LinkedHashMap<String, String> order){
		
		boolean isCancel = eServ.cancelOrder( Integer.parseInt(order.get("orderId")), Integer.parseInt(order.get("employeeId")) );
		
		if (isCancel){
			return new ResponseEntity<>("Order has marked CANCELED", HttpStatus.OK);
		} else {
      		return new ResponseEntity<>("Order cannot be found in the database", HttpStatus.NOT_MODIFIED);
    	}
		
	}

	//logout
	

}
