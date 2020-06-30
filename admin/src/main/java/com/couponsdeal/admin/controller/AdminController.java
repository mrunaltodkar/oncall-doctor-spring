package com.couponsdeal.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.couponsdeal.admin.entity.Admin;
import com.couponsdeal.admin.entity.CustomerList;
import com.couponsdeal.admin.entity.DoctorList;

@RestController
public class AdminController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/adminsignin")
	public ResponseEntity<Admin> signin() {
		Admin admin = new Admin();
		admin.setAdminEmail("admin@coupons.com");
		admin.setAdminPassword("admin@123");

		return new ResponseEntity<Admin>(admin, HttpStatus.OK);

	}

	@GetMapping("/allcustomers")
	public ResponseEntity<CustomerList> allCustomerList() throws Exception {

		CustomerList customerList = restTemplate
				.getForEntity("http://couponsdeal-customer/allcustomers", CustomerList.class).getBody();
		return new ResponseEntity<CustomerList>(customerList, HttpStatus.OK);

	}

	@GetMapping("/doctorlist")
	public ResponseEntity<DoctorList> allDoctors() throws Exception {

		DoctorList status = restTemplate.getForEntity("http://couponsdeal-doctor/doctorlist", DoctorList.class).getBody();

		return new ResponseEntity<DoctorList>(status, HttpStatus.OK);
	}

}
