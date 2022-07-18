package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
 public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		Optional<Employee> employee=employeeRepository.findById(id);
		
		if(employee.isPresent()) {
			return employee.get();
		}
		
		else {
			throw new ResourceNotFoundException("Employee", "Id", id);
		}
		//return employeeRepository.findById(id).orElseThrow()-> new ResourceNotFoundException("Employee", "Id", id);
	}


	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// we need to check whether employee wuth given id exist in db or not
		Employee existEmployee=employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
		
		existEmployee.setFirstName(employee.getFirstName());
		existEmployee.setLastName(employee.getLastName());
		existEmployee.setEmail(employee.getEmail());
		
		//save existing employee to database
		employeeRepository.save(existEmployee);
		
		
		return existEmployee;
	}







	@Override
	public void deleteEmplyee(long id) {
		// TODO Auto-generated method stub
		
		//check whether a employee is exist DB or not
		
		employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
	employeeRepository.deleteById(id);	
	}

}
