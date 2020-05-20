package com.couponsdeal.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsdeal.payment.dao.PaymentDao;
import com.couponsdeal.payment.entity.Payment;
import com.couponsdeal.payment.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	@Override
	public Payment addPaymentDetails(Payment payment) {
		return paymentDao.save(payment);
	}

	@Override
	public Payment findByEmail(String payment) {
		return paymentDao.findByEmail(payment);
	}

}
