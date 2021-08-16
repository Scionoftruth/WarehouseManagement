package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wm.models.Item;
@Repository
public interface ItemRepo extends JpaRepository<Item, Integer>{
	public List<Item> findAll();
	public Item findById(int id);
	//public boolean update(Item item);
	
}
