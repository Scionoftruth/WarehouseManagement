package com.wm.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.models.Order;
import com.wm.models.ResponseOrder;
import com.wm.services.OrderService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 @RestController
 @RequestMapping(value="/order")
 @AllArgsConstructor(onConstructor=@__(@Autowired))
 @NoArgsConstructor
 @CrossOrigin(origins="*")
public class OrderController {
	 
	 private OrderService oServ;

	 
	 @GetMapping("/getorders")
	 public ResponseEntity<List<ResponseOrder>> getAllOrders(){
		 //return new ResponseEntity<>(oServ.getAllOrders(),HttpStatus.OK);
		 List<Order> order = oServ.getAllOrders();
			List<ResponseOrder> resOrder = new ArrayList<>();
			
			for (int i = 0 ; i < order.size(); i++) {
				if (order.get(i) != null) {
					if (order.get(i).getTransId().getStatus().toString() == "SUBMITTED") {
						String address = order.get(i).getTransId().getCustId().getAddress() + ", " + order.get(i).getTransId().getCustId().getCity() +", " + order.get(i).getTransId().getCustId().getState() +" "+ order.get(i).getTransId().getCustId().getZipCode();
						String employee = "";
						int qty = order.get(i).getOrderQty();
						String customer = order.get(i).getTransId().getCustId().getFirstName() + " " + order.get(i).getTransId().getCustId().getLastName();
						
						resOrder.add(new ResponseOrder(	qty, 
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
					
					resOrder.add(new ResponseOrder(	qty, 
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
	 
	 @GetMapping("/getorders/{id}")
	 public ResponseEntity<List<ResponseOrder>> getOrderById(@PathVariable("orderId")int id){
		 //return new ResponseEntity<>(oServ.getOrderById(id),HttpStatus.OK);
		 List<Order> order = oServ.getAllOrders();
		 List<ResponseOrder> resOrder = new ArrayList<>();
			
			for (int i = 0 ; i < order.size(); i++) {
				if (order.get(i) != null) {
					if (order.get(i).getTransId().getStatus().toString() == "SUBMITTED") {
						String address = order.get(i).getTransId().getCustId().getAddress() + ", " + order.get(i).getTransId().getCustId().getCity() +", " + order.get(i).getTransId().getCustId().getState() +" "+ order.get(i).getTransId().getCustId().getZipCode();
						String employee = "";
						int qty = order.get(i).getOrderQty();
						String customer = order.get(i).getTransId().getCustId().getFirstName() + " " + order.get(i).getTransId().getCustId().getLastName();
						
						resOrder.add(new ResponseOrder(	qty, 
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
					
					resOrder.add(new ResponseOrder(	qty, 
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

}
