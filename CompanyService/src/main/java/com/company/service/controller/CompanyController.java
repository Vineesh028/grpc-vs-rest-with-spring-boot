package com.company.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.company.service.services.CompanyGrpcClientImpl;
import com.company.service.services.CompanyServiceImpl;

import demo.interfaces.grpc.Company;
import demo.interfaces.grpc.Employee;

@RestController
public class CompanyController {
	
	@Autowired
	private CompanyGrpcClientImpl companyGrpcClientImpl;
	
	@Autowired
	private CompanyServiceImpl companyServiceImpl;
	
	
	@GetMapping("/restc/company/{empId}")
	public Company getCompanyByEmployeeId(@PathVariable int empId)  {
		return companyServiceImpl.getCompanyByEmployeeId(empId);
	}
	
	@GetMapping("/grpc/employee/{companyName}")
	public Employee getEmployeeByCompanyName(@PathVariable String companyName) {
		
		 long begin = System.currentTimeMillis();
		 Employee employee = companyGrpcClientImpl.getEmployeeByCompanyName(companyName);
	     long end = System.currentTimeMillis();      
	     long time = end-begin;
	     
	     System.out.println("gRPC Elapsed Time: "+time +" milli seconds");
		return employee;
	}
	
	@GetMapping("/rest/employee/{companyName}")
	public String getEmployeeByCompanyNameRest(@PathVariable String companyName) {
		
		 long begin = System.currentTimeMillis();
		 String employee = companyServiceImpl.getEmployeeByCompanyName(companyName);
	     long end = System.currentTimeMillis();      
	     long time = end-begin;
	     
	     System.out.println("REST Elapsed Time: "+time +" milli seconds");
		return employee;
	}

}
