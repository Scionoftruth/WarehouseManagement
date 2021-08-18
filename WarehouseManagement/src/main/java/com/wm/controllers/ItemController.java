package com.wm.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.models.Item;
import com.wm.services.ItemService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

 @RestController
 @RequestMapping(value="/item")
 @AllArgsConstructor(onConstructor=@__(@Autowired))
 @NoArgsConstructor
public class ItemController {

	 private ItemService iServ;
	 
	//Check stock levels by item
	//Add new stock, including new item 
	//Mark an item as discontinued
	 @GetMapping("/stock/{itemId}")
	 public ResponseEntity<Item> checkStock(@PathVariable("itemId")int itemId) {
		 Item i = iServ.getItemById(itemId);
		 return new ResponseEntity<>(i,HttpStatus.OK);
	 }
	 
	 @GetMapping("/stock")
	 public ResponseEntity<List<Item>> getAllItems(){
		 return new ResponseEntity<>(iServ.getAllItems(),HttpStatus.OK);
	 }
	 
	 @PostMapping("/stock/add")
	 public ResponseEntity<String> addItem(@RequestBody LinkedHashMap<String, String> item) {
		Item i = iServ.getItemById(Integer.parseInt(item.get("itemId")));
		if(i == null){
			Item temp = new Item(item.get("itemName"),Float.parseFloat(item.get("itemPrice")),Integer.parseInt(item.get("itemQuantity")));
			iServ.addItem(temp);
			return new ResponseEntity<>("Item Was Successfully Added",HttpStatus.OK);
		}
		iServ.updateStock(i, Integer.parseInt(item.get("itemQuantity")));
		return new ResponseEntity<>("Item Was Successfully Updated",HttpStatus.OK);
	 }
	 
	 @GetMapping("/stock/out")
	 public ResponseEntity<List<Item>> outOfStock(){
		 List<Item> iList = iServ.getAllItems();
		 List<Item> out = new ArrayList<Item>();
		 for(Item i:iList) {
			 if(iServ.checkInStock(i) == 0) {
				 out.add(i);
			 }
		 }
		 return new ResponseEntity<>(out,HttpStatus.OK);
	 }
}
