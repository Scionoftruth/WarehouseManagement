package com.wm.repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.wm.models.ItemTypes;

@Repository("itemTypesRepo")
@Transactional
public class ItemTypesRepo {
	
	private SessionFactory sesFact;
	
	@Autowired
	public ItemTypesRepo(SessionFactory ses) {
		this.sesFact = ses;
	}
	public void insert (ItemTypes type){
		sesFact.getCurrentSession().save(type);
	}
	
	public void update (ItemTypes type) {
		sesFact.getCurrentSession().update(type);
	}
	
	public ItemTypes selectItemById (int id) {
		return sesFact.getCurrentSession().get(ItemTypes.class, id);
	}

}
