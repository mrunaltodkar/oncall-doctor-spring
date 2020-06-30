package com.couponsdeal.couponsdeal.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.couponsdeal.couponsdeal.entity.Customer;

@Repository
public interface CustomerDao extends MongoRepository<Customer, String> {

	public Customer findByEmail(String email);
	
	void deleteByEmail(String email);

}
