package com.wm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.enums.StatusEnum;
import com.wm.models.Order;
import com.wm.services.OrderService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 @RestController
 @RequestMapping(value="/order")
 @AllArgsConstructor(onConstructor=@__(@Autowired))
 @NoArgsConstructor
public class OrderController {
	 
	 private OrderService oServ;
	 
	 @PostMapping("/getorders")
	 public ResponseEntity<List<Order>> getAllOrders(){
		 return new ResponseEntity<>(oServ.getAllOrders(),HttpStatus.OK);
	 }
	 
	 @PostMapping("/getorders/{type}")
	 public ResponseEntity<List<Order>> getAllOrdersByType(@PathVariable("type")StatusEnum status){
		 List<Order> all = oServ.getAllOrders();
		 switch(status) {
		 	case PENDING:
		 		
		 }
	 }

}
