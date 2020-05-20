package com.couponsdeal.payment.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.couponsdeal.payment.entity.Payment;

@Repository
public interface PaymentDao extends MongoRepository<Payment, String> {

	public Payment findByEmail(String email);
}
