package com.couponsdeal.coupons.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsdeal.coupons.dao.CouponsDao;
import com.couponsdeal.coupons.entity.Coupons;
import com.couponsdeal.coupons.service.CouponsService;

@Service
public class CouponsServiceImpl implements CouponsService {
	
	@Autowired
	private CouponsDao couponsDao;

	@Override
	public Coupons findByCouponId(long couponId) {
		
		return couponsDao.findByCouponId(couponId);
	}

	@Override
	public List<Coupons> findByAll() {
		
		return couponsDao.findAll();
	}

}
