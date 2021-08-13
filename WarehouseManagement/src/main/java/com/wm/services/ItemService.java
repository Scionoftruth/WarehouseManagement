package com.wm.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.models.Item;
import com.wm.repository.ItemRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class ItemService {

	private ItemRepo iDao;
	
	//fetch by id
	//update the quantity
	//add/remove new item
	
	public Item getItemById(int num) {
		Item item = iDao.getById(num);
		return item;
	}
	


		
		
		
	
}