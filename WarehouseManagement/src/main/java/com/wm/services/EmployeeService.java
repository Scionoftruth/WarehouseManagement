package com.wm.services;

import java.math.BigInteger;

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
			Employee r = eDao.save(em);
			int empId = r.getEmpId();
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
	
	public boolean acceptOrder(int transactionId, int employeeId){
		

		StatusEnum completed = StatusEnum.valueOf("PENDING");
		
		Transaction t = tDao.findById(transactionId);
		Employee e = eDao.findById(employeeId);
		if (t == null || e == null){
			return false;
		} else {
		
		t.setStatus(completed);
		t.setEmpId(e);
		
		tDao.save(t);
		return true;
		}
	
	}
	
	public boolean completeOrder(int transactionId, int employeeId){
		

		StatusEnum completed = StatusEnum.valueOf("COMPLETED");
		
		Transaction t = tDao.findById(transactionId);
		Employee e = eDao.findById(employeeId);
		
		if (t == null){
			return false;
		} else {
		
		t.setStatus(completed);
		t.setEmpId(e);
		
		tDao.save(t);
		return true;
		}
	
	}
	
	public boolean cancelOrder(int transactionId, int employeeId){
		
		StatusEnum completed = StatusEnum.valueOf("CANCELED");
		
		Transaction t = tDao.findById(transactionId);
		Employee e = eDao.findById(employeeId);
		
		if (t == null){
			return false;
		} else {
		
		t.setStatus(completed);
		t.setEmpId(e);
		
		tDao.save(t);
		return true;
		}
	
	}
	
	
	
	
	//Update customer information
	
	//View all submitted/cancelled/complete  orders- in TransactionController
	//View their accepted orders- in TransactionController
	//View orders of a specific customer

	//Check stock levels by item
	//Add new stock, including new item 
	//Mark an item as discontinued 
	 

}
