package com.wm.services;

import java.util.List;

import org.apache.catalina.servlets.CGIServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.models.Item;
import com.wm.models.Order;
import com.wm.repository.ItemRepo;
import com.wm.repository.OrderRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ItemService {

	private ItemRepo iDao;
	private OrderRepo oDao;
	
	//fetch by id
	//update the quantity
	//add/remove new item
	
	public Item getItemById(int num) {
		Item item = iDao.getById(num);
		return item;
	}
	
	public void completedOrder(int orderId) {
		Order o = oDao.getById(orderId);
		int sub = o.getOrderQty();
		Item i = o.getItemId();
		int stock = i.getInvQuantity();
		int buffer = i.getBuffer();
		i.setBuffer(buffer-sub);
		i.setInvQuantity(stock-sub);
		
		Item result = new Item(i.getItemId(),i.getItemName(),i.getItemPrice(),i.getInvQuantity(),i.getBuffer());
		iDao.save(result);
	}
	
	public void deniedOrder(int orderId) {
		Order o = oDao.getById(orderId);
		int sub = o.getOrderQty();
		Item i = o.getItemId();
		int buffer = i.getBuffer();
		i.setBuffer(buffer-sub);
		
		Item result = new Item(i.getItemId(),i.getItemName(),i.getItemPrice(),i.getInvQuantity(),i.getBuffer());
		iDao.save(result);
	}
	
	public void acceptedOrder(int orderId) {
		Order o = oDao.getById(orderId);
		int add = o.getOrderQty();
		Item i = o.getItemId();
		int buffer = i.getBuffer();
		i.setBuffer(buffer+add);
		
		Item result = new Item(i.getItemId(),i.getItemName(),i.getItemPrice(),i.getInvQuantity(),i.getBuffer());
		iDao.save(result);
	}
	
	public void addItem(Item i) {
		i.setItemPrice(Float.parseFloat(String.format("%12.2f",(Float.toString(i.getItemPrice())))));
//		float price =i.getItemPrice();
//		String price2 = Float.toString(price);
//		price = Float.parseFloat(String.format("%6.2f", price2));
		iDao.save(i);
	}
	
	public List<Item> getAllItems(){
		return iDao.findAll();
	}
	
	public void updateStock(Item i, int add) {
		int q = i.getInvQuantity();
		i.setInvQuantity(q+add);
		Item result = new Item(i.getItemId(),i.getItemName(),i.getItemPrice(),i.getInvQuantity(),i.getBuffer());
		iDao.save(result);
	}
	
	public int checkInStock(Item i) {
		return (i.getInvQuantity()-i.getBuffer());
	}

		
		
		
	
}