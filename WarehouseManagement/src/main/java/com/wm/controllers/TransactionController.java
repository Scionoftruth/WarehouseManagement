package com.wm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.services.TransactionService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 @RestController
 @RequestMapping(value="/transaction")
 @AllArgsConstructor(onConstructor=@__(@Autowired))
 @NoArgsConstructor
public class TransactionController {

	 private TransactionService tServ;
	 
}
