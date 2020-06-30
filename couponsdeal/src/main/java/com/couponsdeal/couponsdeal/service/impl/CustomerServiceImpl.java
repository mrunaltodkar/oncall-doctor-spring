package com.couponsdeal.couponsdeal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsdeal.couponsdeal.dao.CustomerDao;
import com.couponsdeal.couponsdeal.entity.Customer;
import com.couponsdeal.couponsdeal.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	private static final String String = null;

	@Override
	public Customer addDetails(Customer customer) {

		return customerDao.save(customer);
	}

	@Override
	public Customer findByEmail(String email) {

		return customerDao.findByEmail(email);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public void deleteByEmail(String email) {
		customerDao.deleteByEmail(email);
		
	}

}
