package com.couponsdeal.coupons.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.couponsdeal.coupons.entity.Coupons;

@Service
public interface CouponsService {
	
	public Coupons findByCouponId(long couponId);

	public List<Coupons> findByAll();
	

}
