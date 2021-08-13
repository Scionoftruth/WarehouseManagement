package com.wm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.models.Employee;
import com.wm.repository.EmployeeRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class EmployeeService {
	
	private EmployeeRepo eDao;
	
	public boolean registerEmployee(Employee em) {
		try {
			eDao.save(em);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	public Employee loginEmployee(String username, String password) {
		Employee em = eDao.findByUsername(username);
		if(em==null) {
			return null;
		}else {
			if(!em.getPassword().equals(password)) {
				return null;
			}else {
				return em;
			}
		}
	}
	
	public Employee displayEmployee(String username) {
		Employee em = eDao.findByUsername(username);
		if(em == null) {
			return null;
		}else {
			return em;
		}
	}
	
	public Employee getEmployeeById(int id) {
		return eDao.getById(id);
	}

}
