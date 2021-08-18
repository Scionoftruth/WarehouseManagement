package com.wm.services;

import java.math.BigInteger;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.enums.RoleEnum;
import com.wm.enums.StatusEnum;
import com.wm.models.Employee;
import com.wm.models.Order;
import com.wm.models.Transaction;
import com.wm.repository.EmployeeRepo;
import com.wm.repository.OrderRepo;
import com.wm.repository.TransactionRepo;
import com.wm.services.ItemService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class EmployeeService {
	
	private EmployeeRepo eDao;
	private TransactionRepo tDao;
	private ItemService iServ;
	private OrderRepo oDao;
	
	
	public boolean registerEmployee(Employee em) {
		try {
			Employee r = eDao.save(em);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	public boolean updateEmployee(Employee em) {
		Employee e = eDao.getById(em.getEmpId());
		
		if (em.getEmail() != ""){
			e.setEmail(em.getEmail());
		}
		if (em.getFirstName() != ""){
			e.setFirstName(em.getFirstName());
		}
		
		if (em.getLastName() != ""){
			e.setLastName(em.getLastName());
		}
		
		if (em.getPassword() != ""){
			e.setPassword(em.getPassword());
		}
		
		try {
			Employee r = eDao.save(e);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	public Employee loginEmployee(String email, String password) {
		Employee em = eDao.findByEmail(email);
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
		

		StatusEnum pending = StatusEnum.valueOf("PENDING");
		
		Transaction t = tDao.findByTransId(transactionId);
		Employee e = eDao.findById(employeeId);
		
		
		
		if (t == null || e == null){
			return false;
		} else {
		//Get Order ID By transaction ID
		List<Order> o = oDao.findByTransId(transactionId);
		int orderId = o.get(0).getOrderId();
		
		for (int i = 0 ; i < o.size(); i++){
			iServ.acceptedOrder(orderId);
		}
		t.setStatus(pending);
		t.setEmpId(e);
		
		tDao.save(t);
		return true;
		}
	
	}
	//complete order for customers
	public boolean completeOrder(int transactionId, int employeeId){
		

		StatusEnum completed = StatusEnum.valueOf("COMPLETED");
		
		Transaction t = tDao.findByTransId(transactionId);
		Employee e = eDao.findById(employeeId);
		
		
		
		if (t == null){
			return false;
		} else {
		//Get Order ID By transaction ID
		List<Order> o = oDao.findByTransId(transactionId);
		int orderId = o.get(0).getOrderId();
		
		for (int i = 0 ; i < o.size(); i++){
			iServ.completedOrder(orderId);
		}
		t.setStatus(completed);
		t.setEmpId(e);
		
		tDao.save(t);
		return true;
		}
	
	}
	
	public boolean cancelOrder(int transactionId, int employeeId){
		
		StatusEnum completed = StatusEnum.valueOf("CANCELED");
		
		Transaction t = tDao.findByTransId(transactionId);
		Employee e = eDao.findById(employeeId);
		
		
		
		if (t == null){
			return false;
		} else {
		//Get Order ID By transaction ID
		List<Order> o = oDao.findByTransId(transactionId);
		int orderId = o.get(0).getOrderId();
		
		for (int i = 0 ; i < o.size(); i++){
			iServ.deniedOrder(orderId);
		}
		t.setStatus(completed);
		t.setEmpId(e);
		
		tDao.save(t);
		return true;
		}
	
	}
	
	public List<Order> viewOrderByCustomer(int custId){
		List<Transaction> t = tDao.findByCustId(custId);
		List<Order> order = new ArrayList<>();
		for (int i =0; i<t.size();i++){
			order.add(oDao.findBytransId( t.get(i).getTransId() ));
		}
		return order;
	}
	
	public List<Order> viewOrderByTransaction(List<Transaction> t){

		List<Order> order = new ArrayList<>();
		for (int i =0; i< t.size();i++){
			order.add(oDao.findBytransId( t.get(i).getTransId() ));
		}
		return order;
	}
	
	public List<Transaction> viewByStatus(StatusEnum status){
		return tDao.findByStatus(status);
	}
	

}
