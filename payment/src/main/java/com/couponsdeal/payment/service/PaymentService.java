package com.couponsdeal.payment.service;

import org.springframework.stereotype.Service;

import com.couponsdeal.payment.entity.Payment;

@Service
public interface PaymentService {

	public Payment addPaymentDetails(Payment payment);
	public Payment findByEmail(String payment);
}
