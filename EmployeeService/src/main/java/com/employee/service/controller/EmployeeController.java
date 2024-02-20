package com.employee.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.employee.service.services.EmployeeGrpcClientImpl;
import com.employee.service.services.EmployeeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;

import demo.interfaces.grpc.Company;
import demo.interfaces.grpc.Employee;



@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeGrpcClientImpl employeeGrpcClientImpl;
	
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@GetMapping("/grpc/company/{empId}")
	public Company getCompanyByEmployeeId(@PathVariable int empId) {
		
		 long begin = System.currentTimeMillis();
		 Company company = employeeGrpcClientImpl.getCompanyByEmployeeId(empId);
	     long end = System.currentTimeMillis();      
	     long time = end-begin;
	     
	     System.out.println("gRPC Elapsed Time: "+time +" milli seconds");
		return company;
	}
	
	@GetMapping("/rest/company/{empId}")
	public String getCompanyByEmployeeIdRest(@PathVariable int empId) {
		 long begin = System.currentTimeMillis();
		 String company = employeeServiceImpl.getCompanyByEmployeeId(empId);
	     long end = System.currentTimeMillis();      
	     long time = end-begin;
	     
	     System.out.println("REST Elapsed Time: "+time +" milli seconds");
		return company;
	}
	
	@GetMapping("/reste/employee/{companyName}")
	public Employee getEmployeeByCompanyName(@PathVariable String companyName) throws JsonProcessingException {
		return employeeServiceImpl.getEmployeeByCompanyName(companyName);
	}

}
