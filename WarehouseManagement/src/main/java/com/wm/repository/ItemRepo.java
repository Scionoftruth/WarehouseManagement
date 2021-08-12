package com.wm.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.wm.models.Item;

@Repository("itemRepo")
@Transactional
public class ItemRepo {

	private SessionFactory sesFact;
	
	@Autowired
	public ItemRepo(SessionFactory ses) {
		this.sesFact = ses;
	}
	
	public void insert (Item i){
		sesFact.getCurrentSession().save(i);
	}
	
	public void update (Item i) {
		sesFact.getCurrentSession().update(i);
	}
	
	public Item selectItemById (int id) {
		return sesFact.getCurrentSession().get(Item.class, id);
	}
	
	public List<Item> selectAllItems() {
		List<Item> iList = sesFact.getCurrentSession()
		.createQuery("from Item", Item.class).list();
		return iList;	
	}	
	
}
