package com.example.reactjsspringbootCRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.reactjsspringbootCRUD.model.*;
@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
	
	
	

}
