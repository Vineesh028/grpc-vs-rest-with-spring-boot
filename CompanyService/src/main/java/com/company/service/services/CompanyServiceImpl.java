package com.company.service.services;

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

import demo.interfaces.grpc.Company;

@Service
public class CompanyServiceImpl {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${employeeservice.employeebycompanynameurl}")
	private String employeebycompanynameurl;

	public Company getCompanyByEmployeeId(int empId) {
		// TODO Auto-generated method stub
		Company company = Company.newBuilder().setCompanyName("Amazon")
				.setCompanyLocation("Seattle").setEmployeeID(empId).build();
		
		System.out.println("Sending company.."+company);
		return company;
	}

	public String getEmployeeByCompanyName(String companyName) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		Map<String, String> params = new HashMap<>();
		params.put("companyName", companyName);


		HttpEntity<String> response = restTemplate.exchange(
				employeebycompanynameurl,
		        HttpMethod.GET,
		        entity,
		        String.class,
		        params
		);
		
		System.out.println("Received employee.."+response.getBody());
		return response.getBody();
	}

}
