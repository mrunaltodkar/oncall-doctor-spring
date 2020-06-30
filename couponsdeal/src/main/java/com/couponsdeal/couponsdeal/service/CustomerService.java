package com.couponsdeal.couponsdeal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.couponsdeal.couponsdeal.entity.Customer;

@Service
public interface CustomerService {

	public Customer addDetails(Customer customer);

	public Customer findByEmail(String email);
	
	public  List<Customer> findAll();

	void deleteByEmail(String email);

}
