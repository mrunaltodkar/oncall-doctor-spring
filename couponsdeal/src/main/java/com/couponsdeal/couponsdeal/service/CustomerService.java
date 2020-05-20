package com.couponsdeal.couponsdeal.service;

import org.springframework.stereotype.Service;

import com.couponsdeal.couponsdeal.entity.Customer;

@Service
public interface CustomerService {

	public Customer addDetails(Customer customer);

	public Customer findByEmail(String email);

}
