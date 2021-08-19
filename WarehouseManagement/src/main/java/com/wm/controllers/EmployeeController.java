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
import com.wm.services.ItemService;

import com.wm.models.Employee;
import com.wm.models.Order;
import com.wm.models.Transaction;
import com.wm.enums.RoleEnum;
import com.wm.enums.StatusEnum;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 @RestController
 @RequestMapping(value="/employee")
 @AllArgsConstructor(onConstructor=@__(@Autowired))
 @NoArgsConstructor
public class EmployeeController {
	
	//View all submitted/cancelled/complete  orders- in TransactionController
	//View their accepted orders- in TransactionController
	//View orders of a specific customer

	private ItemService iServ;
	private EmployeeService eServ;

	//login
	@PostMapping("/login")
	public ResponseEntity<Employee> loginEmployee(@RequestBody LinkedHashMap<String, String> employee){
    	Employee e = eServ.loginEmployee(employee.get("email"), employee.get("password"));
    	
    	if (e == null){
        	return new ResponseEntity<Employee>(e, HttpStatus.FORBIDDEN);
      }
      return new ResponseEntity<Employee>(e, HttpStatus.OK);
  	}
	
	
	@PostMapping("/register")
	public ResponseEntity<String> createEmployee(@RequestBody LinkedHashMap<String, String> employee){
		System.out.println(employee);
		
		RoleEnum role = RoleEnum.valueOf("EMPLOYEE");
		
		Employee e = new Employee(employee.get("firstName"), employee.get("lastName"), employee.get("email"), employee.get("password"), role);
		
		if(eServ.registerEmployee(e)) {
			return new ResponseEntity<>("Employee was registered", HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Email already registered to a user", HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updateEmployee(@RequestBody LinkedHashMap<String, String> employee){
		
		RoleEnum role = RoleEnum.valueOf("EMPLOYEE");
		
		Employee e = new Employee(Integer.parseInt(employee.get("id")), employee.get("firstName"), employee.get("lastName"), employee.get("email"), employee.get("password"), role);
		
		if(eServ.updateEmployee(e)) {
			return new ResponseEntity<>("User was Updated", HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<>("Email already registered to a user", HttpStatus.CONFLICT);
		}
	}
	
	//Accept an order
	@PostMapping("/accept")
	public ResponseEntity<String> acceptOrder(@RequestBody LinkedHashMap<String, String> transaction){
		
		boolean isAccept = eServ.acceptOrder( Integer.parseInt(transaction.get("transactionId")), Integer.parseInt(transaction.get("employeeId")) );
		
		if (isAccept){
			return new ResponseEntity<>("Order has been Accepted marked PENDING", HttpStatus.OK);
		} else {
      		return new ResponseEntity<>("Order cannot be found in the database", HttpStatus.NOT_MODIFIED);
    	}
		
	}
	//Complete order
	@PostMapping("/complete")
	public ResponseEntity<String> completeOrder(@RequestBody LinkedHashMap<String, String> transaction){
		
		boolean isComplete = eServ.completeOrder( Integer.parseInt(transaction.get("transactionId")), Integer.parseInt(transaction.get("employeeId")) );
		
		if (isComplete){
			return new ResponseEntity<>("Order has marked COMPLETED", HttpStatus.OK);
		} else {
      		return new ResponseEntity<>("Order cannot be found in the database", HttpStatus.NOT_MODIFIED);
    	}
		
	}
	
	//Cancel order
	@PostMapping("/cancel")
	public ResponseEntity<String> cancelOrder(@RequestBody LinkedHashMap<String, String> transaction){
		
		boolean isCancel = eServ.cancelOrder( Integer.parseInt(transaction.get("transactionId")), Integer.parseInt(transaction.get("employeeId")) );
		
		if (isCancel){
			return new ResponseEntity<>("Order has marked CANCELED", HttpStatus.OK);
		} else {
      		return new ResponseEntity<>("Order cannot be found in the database", HttpStatus.NOT_MODIFIED);
    	}
		
	}
	
	@GetMapping("/{custId}")
	public ResponseEntity<List<Order>> viewOrderByCustomer(@PathVariable("custId")int custId){
		
		return new ResponseEntity<List<Order>>(eServ.viewOrderByCustomer(custId), HttpStatus.OK);
		
		
	}
	
	@GetMapping("/{status}")
	public ResponseEntity<List<Order>> viewByStatus(@PathVariable("status")String status){
		StatusEnum statusTo = StatusEnum.valueOf(status.toUpperCase());
		List<Transaction> t = eServ.viewByStatus(statusTo);
		
		
		return new ResponseEntity<List<Order>>(eServ.viewOrderByTransaction(t), HttpStatus.OK);
		
		
	}

	//logout
	

}
