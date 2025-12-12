package com.cdgn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdgn.model.Employee;
import com.cdgn.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;
	public Employee saveEmployee(Employee employee) {
		return repository.save(employee);
	}
	public boolean existsByEmailAndPassword(String email, String password) {
		return repository.existsByEmailAndPassword(email, password);
	}
	public List<Employee> findAll() {
		return repository.findAll();
	}
	public void deleteById(int id) {
		repository.deleteById(id);
	}
	public Employee findById(int id) {
		return repository.findById(id).orElse(null);
	}
	public Employee findByEmailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password).orElse(null);
	}

}