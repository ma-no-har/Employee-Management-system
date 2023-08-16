package com.example.reactjsspringbootCRUD.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.reactjsspringbootCRUD.repository.EmployeeRepo;
import java.util.*;

import com.example.reactjsspringbootCRUD.exception.ResourceNotFoundException;
import com.example.reactjsspringbootCRUD.model.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
	EmployeeRepo er;
	
	 @GetMapping("/employees")
	public List <Employee> getAllEmployees()
	{
		return er.findAll();
	}
	
	 @PostMapping("/employees")
	 public Employee createEmployee(@RequestBody Employee emp)
	 {
		
		 return  er.save(emp);
	 }
	 @GetMapping("/employees/{id}")
		 public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) throws ResourceNotFoundException
		 {
			 Employee employee = er.findById(id)
					 .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :"+id));
			 return ResponseEntity.ok(employee);
			 
		 }
	 
	 @PutMapping("/employees/{id}")
	 public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody Employee employeeDetails) throws ResourceNotFoundException
	 {
		 Employee employee = er.findById(id)
				 .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		 employee.setFirst_name(employeeDetails.getFirst_name());
		 employee.setLast_name(employeeDetails.getLast_name());
		 employee.setEmail_id(employeeDetails.getEmail_id());
		 
		 Employee updatedEmployee = er.save(employee);
		 return ResponseEntity.ok(updatedEmployee);
	 }
	 @DeleteMapping("/employees/{id}")
	 public ResponseEntity<Map<String,Boolean>> deleteEmployee (@PathVariable int id) throws ResourceNotFoundException
	 {
		 Employee employee = er.findById(id)
				 .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:"+id));
		 er.delete(employee);
		 Map<String,Boolean>response = new HashMap<>();
		 response.put( "deleted", Boolean.TRUE);
		 return ResponseEntity.ok(response);
		
		 
	 }
	 @GetMapping("/hello")
	 ResponseEntity<String> hello() {
	     return ResponseEntity.ok("Hello World!");
	 }
}


