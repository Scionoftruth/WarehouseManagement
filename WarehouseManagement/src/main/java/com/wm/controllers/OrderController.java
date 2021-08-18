package com.wm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	 
	 @GetMapping("/getorders")
	 public ResponseEntity<List<Order>> getAllOrders(){
		 return new ResponseEntity<>(oServ.getAllOrders(),HttpStatus.OK);
	 }
	 
	 @GetMapping("/getorders/{id}")
	 public ResponseEntity<Order> getOrderById(@PathVariable("id")int id){
		 return new ResponseEntity<>(oServ.getOrderById(id),HttpStatus.OK);
	 }

}
