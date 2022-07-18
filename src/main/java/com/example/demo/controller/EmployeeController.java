package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
 private EmployeeService employeeService;

public EmployeeController(EmployeeService employeeService) {
	super();
	this.employeeService = employeeService;
}
 
 //build create rest api
@PostMapping("/employee")
public ResponseEntity<Employee> saveEmployee(@Valid @RequestBody Employee employee){
	//employeeService.saveEmployee(employee);
	return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
	
	
}
//build all employess rest api
@GetMapping()
public List<Employee> getAllEmployees(){
	
	return employeeService.getAllEmployees();
}

//build get employee by id rest api
//http://localhost:8080/api/employees/1
@GetMapping("{id}")
public ResponseEntity<Employee> gwtEmployeeById(@PathVariable("id") long employeeid){
	//employeeService.saveEmployee(employee);
	return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeid), HttpStatus.OK);
	
	
}

@PutMapping("{id}")
public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee){
	//employeeService.saveEmployee(employee);
	return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
	
	
}
//build delete rest api

@DeleteMapping("{id}")
public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
	employeeService.deleteEmplyee(id);
	return new ResponseEntity<String>("Employee deleted successfully! .",HttpStatus.OK);
	
}



}
