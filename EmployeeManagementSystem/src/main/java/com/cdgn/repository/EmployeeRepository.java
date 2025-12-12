package com.cdgn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdgn.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	public boolean existsByEmailAndPassword(String email,String password);
	public Optional<Employee> findByEmailAndPassword(String email,String password);

}