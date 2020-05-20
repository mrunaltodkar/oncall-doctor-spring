package com.couponsdeal.coupons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.couponsdeal.coupons.entity.Coupons;
import com.couponsdeal.coupons.service.CouponsService;

@RestController
public class CouponsController {

	@Autowired
	private CouponsService couponsService;

	@GetMapping("/selectcoupon/{couponId}")
	public ResponseEntity<Coupons> selectedCoupon(@PathVariable String couponId) {
		
		long couponId1 = Long.parseLong(couponId);
		System.out.println(couponId1);
		Coupons coupons = couponsService.findByCouponId(couponId1);
		return new ResponseEntity<Coupons>(coupons, HttpStatus.CONTINUE);

	}

	@GetMapping("/allcoupons")
	public ResponseEntity<List<Coupons>> showAllCoupons() {

		List<Coupons> listCoupons = couponsService.findByAll();
		return new ResponseEntity<List<Coupons>>(listCoupons, HttpStatus.FOUND);
	}

}
