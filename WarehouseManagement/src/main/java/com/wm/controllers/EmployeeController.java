package com.wm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wm.services.EmployeeService;
import com.wm.services.ItemService;

import com.wm.models.Employee;
import com.wm.models.Order;
import com.wm.models.ResponseOrder;
import com.wm.models.Transaction;
import com.wm.enums.RoleEnum;
import com.wm.enums.StatusEnum;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 @RestController
 @RequestMapping(value="/employee")
 @AllArgsConstructor(onConstructor=@__(@Autowired))
 @NoArgsConstructor
 @CrossOrigin(origins="*")
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
		
		//RoleEnum role = RoleEnum.valueOf("EMPLOYEE");
		
		Employee e = new Employee(employee.get("firstName"), employee.get("lastName"), employee.get("email"), employee.get("password"));
		
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
		
		Employee e = new Employee(Integer.parseInt(employee.get("id")), employee.get("firstName"), employee.get("lastName"), employee.get("email"), employee.get("password"));
		
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
	
	@GetMapping("/customer/order/{custId}")
	public ResponseEntity<List<ResponseOrder>> viewOrderByCustomer(@PathVariable("custId")int custId){
		List<Order> order = eServ.viewOrderByCustomer(custId);
		List<ResponseOrder> resOrder = new ArrayList<>();
		
		for (int i = 0 ; i < order.size(); i++) {
			if (order.get(i) != null) {
				if (order.get(i).getTransId().getStatus().toString() == "SUBMITTED") {
					String address = order.get(i).getTransId().getCustId().getAddress() + ", " + order.get(i).getTransId().getCustId().getCity() +", " + order.get(i).getTransId().getCustId().getState() +" "+ order.get(i).getTransId().getCustId().getZipCode();
					String employee = "";
					int qty = order.get(i).getOrderQty();
					String customer = order.get(i).getTransId().getCustId().getFirstName() + " " + order.get(i).getTransId().getCustId().getLastName();
					
					resOrder.add(new ResponseOrder(	order.get(i).getOrderId(), order.get(i).getTransId().getTransId(), order.get(i).getItemId().getItemId(),qty, 
							order.get(i).getItemId().getItemName(),
							order.get(i).getItemId().getItemPrice(),
							order.get(i).getItemId().getInvQuantity(), 
							order.get(i).getTransId().getStatus().toString(),
							customer, employee, address));
				}
				else {
				String address = order.get(i).getTransId().getCustId().getAddress() + ", " + order.get(i).getTransId().getCustId().getCity() +", " + order.get(i).getTransId().getCustId().getState() +" "+ order.get(i).getTransId().getCustId().getZipCode();
				String employee = order.get(i).getTransId().getEmpId().getFirstName() + " "+ order.get(i).getTransId().getEmpId().getLastName();
				int qty = order.get(i).getOrderQty();
				String customer = order.get(i).getTransId().getCustId().getFirstName() + " " + order.get(i).getTransId().getCustId().getLastName();
				
				resOrder.add(new ResponseOrder(	order.get(i).getOrderId(), order.get(i).getTransId().getTransId(), order.get(i).getItemId().getItemId(),qty, 
						order.get(i).getItemId().getItemName(),
						order.get(i).getItemId().getItemPrice(),
						order.get(i).getItemId().getInvQuantity(), 
						order.get(i).getTransId().getStatus().toString(),
						customer, employee, address));
				}
			}
		}
		return new ResponseEntity<>(resOrder, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/bystatus/{status}")
	public ResponseEntity<List<ResponseOrder>> viewByStatus(@PathVariable("status")String status){
		List<ResponseOrder> nullOrder = new ArrayList<>();
		status = status.toLowerCase();
		System.out.println(status);
		if (status.equals("completed") | status.equals("pending") | status.equals("canceled") |status.equals("submitted")) {
		StatusEnum statusTo = StatusEnum.valueOf(status.toUpperCase());
		List<Transaction> t = eServ.viewByStatus(statusTo);
		
		List<Order> order = eServ.viewOrderByTransaction(t);
		List<ResponseOrder> resOrder = new ArrayList<>();
		
		for (int i = 0 ; i < order.size(); i++) {
			if (order.get(i) != null) {
				if (order.get(i).getTransId().getStatus().toString() == "SUBMITTED") {
					String address = order.get(i).getTransId().getCustId().getAddress() + ", " + order.get(i).getTransId().getCustId().getCity() +", " + order.get(i).getTransId().getCustId().getState() +" "+ order.get(i).getTransId().getCustId().getZipCode();
					String employee = "";
					int qty = order.get(i).getOrderQty();
					String customer = order.get(i).getTransId().getCustId().getFirstName() + " " + order.get(i).getTransId().getCustId().getLastName();
					
					resOrder.add(new ResponseOrder(	order.get(i).getOrderId(), order.get(i).getTransId().getTransId(), order.get(i).getItemId().getItemId(),qty, 
							order.get(i).getItemId().getItemName(),
							order.get(i).getItemId().getItemPrice(),
							order.get(i).getItemId().getInvQuantity(), 
							order.get(i).getTransId().getStatus().toString(),
							customer, employee, address));
				}
				else {
				String address = order.get(i).getTransId().getCustId().getAddress() + ", " + order.get(i).getTransId().getCustId().getCity() +", " + order.get(i).getTransId().getCustId().getState() +" "+ order.get(i).getTransId().getCustId().getZipCode();
				String employee = order.get(i).getTransId().getEmpId().getFirstName() + " "+ order.get(i).getTransId().getEmpId().getLastName();
				int qty = order.get(i).getOrderQty();
				String customer = order.get(i).getTransId().getCustId().getFirstName() + " " + order.get(i).getTransId().getCustId().getLastName();
				
				resOrder.add(new ResponseOrder(	order.get(i).getOrderId(), order.get(i).getTransId().getTransId(), order.get(i).getItemId().getItemId(),qty, 
						order.get(i).getItemId().getItemName(),
						order.get(i).getItemId().getItemPrice(),
						order.get(i).getItemId().getInvQuantity(), 
						order.get(i).getTransId().getStatus().toString(),
						customer, employee, address));
				}
			}
		}
		
		return new ResponseEntity<>(resOrder, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(nullOrder, HttpStatus.NOT_FOUND);
		}
		
		
	}

	
	

}
