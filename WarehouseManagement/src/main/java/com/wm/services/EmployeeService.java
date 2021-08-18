package com.wm.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.enums.RoleEnum;
import com.wm.enums.StatusEnum;
import com.wm.models.Employee;
import com.wm.models.Transaction;
import com.wm.repository.EmployeeRepo;
import com.wm.repository.TransactionRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class EmployeeService {
	
	private EmployeeRepo eDao;
	private TransactionRepo tDao;
	
	public boolean registerEmployee(Employee em) {
		try {
			eDao.save(em);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	public Employee loginEmployee(int employeeId, String password) {
		Employee em = eDao.findById(employeeId);
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
	
	public Employee displayEmployee(int employeeId) {
		Employee em = eDao.findById(employeeId);
		if(em == null) {
			return null;
		}else {
			return em;
		}
	}
	
	public Employee getEmployeeById(int id) {
		return eDao.getById(id);
	}
	
	public boolean acceptOrder(int orderId, int employeeId){
		//Get Current Employee ID from Session "Local Storage"
		//id=1 is for testing only
		employeeId = 1;

		StatusEnum completed = StatusEnum.valueOf("PENDING");
		
		Transaction t = tDao.findByOrderId(orderId);
		
		if (t == null){
			return false;
		} else {
		
		t.setStatus(completed);
		t.setEmpId(employeeId);
		
		tDao.save(t);
		return true;
		}
	
	}
	
	public boolean completeOrder(int orderId, int employeeId){
		//Get Current Employee ID from Session "Local Storage"
		//id=1 is for testing only
		employeeId = 1;

		StatusEnum completed = StatusEnum.valueOf("COMPLETED");
		
		Transaction t = tDao.findByOrderId(orderId);
		
		if (t == null){
			return false;
		} else {
		
		t.setStatus(completed);
		t.setEmpId(employeeId);
		
		tDao.save(t);
		return true;
		}
	
	}
	
	public boolean cancelOrder(int orderId, int employeeId){
		//Get Current Employee ID from Session "Local Storage"
		//id=1 is for testing only
		employeeId = 1;

		StatusEnum completed = StatusEnum.valueOf("CANCELED");
		
		Transaction t = tDao.findByOrderId(orderId);
		
		if (t == null){
			return false;
		} else {
		
		t.setStatus(completed);
		t.setEmpId(employeeId);
		
		tDao.save(t);
		return true;
		}
	
	}

}
