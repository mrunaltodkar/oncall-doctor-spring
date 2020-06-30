package com.couponsdeal.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.couponsdeal.payment.entity.Payment;

@Service
public interface PaymentService {

	public Payment addPaymentDetails(Payment payment);

	public Payment findByEmail(String email);

	public List<Payment> findAllPaymentDetails(String email);

	public Payment updatePaymentDetails(Payment payment);
}
