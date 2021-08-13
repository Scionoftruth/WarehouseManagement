package com.wm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wm.models.Employee;

public interface EmployeeRepo extends JpaRepository <Employee, Integer>{

	public List<Employee> findAll();
	public Employee findById(int id);
	public Employee findByUsername(String username);

}
