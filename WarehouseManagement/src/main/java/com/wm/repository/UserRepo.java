package com.wm.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wm.models.User;

@Repository("userRepo")
@Transactional
public class UserRepo {

	private SessionFactory sesFact;
	
	@Autowired
	public UserRepo(SessionFactory ses) {
		this.sesFact = ses;
	}
	
	public void insert(User u) {
		sesFact.getCurrentSession().save(u);
	}
	
	public void update(User u) {
		sesFact.getCurrentSession().update(u);
	}
	
	public User selectUserById(int id) {
		return sesFact.getCurrentSession().get(User.class, id);
	}
	
	public List<User> selectAllUsers(){
		List<User> uList = sesFact.getCurrentSession()
				.createQuery("from User", User.class).list();
		return uList;
	}
}
