package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.models.Item;

public interface ItemRepo extends JpaRepository<Item, Integer>{
	public List<Item> findAllItems();
	public Item findById(int id);
	public boolean update(Item item);
	
}
