package com.employee.service.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import demo.interfaces.grpc.Employee;

@Service
public class EmployeeServiceImpl {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${companyservice.companybyemployeeidurl}")
	private String companyByEmployeeIdUrl;

	public String getCompanyByEmployeeId(int empId) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		Map<String, Integer> params = new HashMap<>();
		params.put("empId", empId);


		HttpEntity<String> response = restTemplate.exchange(
				companyByEmployeeIdUrl,
		        HttpMethod.GET,
		        entity,
		        String.class,
		        params
		);

		
		System.out.println("Received..company.."+response.getBody());
		return response.getBody();
	}

	public Employee getEmployeeByCompanyName(String companyName) throws JsonProcessingException {
		// TODO Auto-generated method stub
		Employee employee= Employee.newBuilder().setEmployeeFirstName("Lucy").setEmployeeLastName("Baker")
				.setEmployeeID(1234).setCompanyName(companyName).build();

		System.out.println("Sending employee.."+employee);
		return employee;
	}
	

}
