package com.couponsdeal.wishlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.couponsdeal.wishlist.entity.Coupons;
import com.couponsdeal.wishlist.entity.Wishlist;

@Controller
public class WishListController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/whishlist/{couponid}")
	public ResponseEntity<Wishlist> getWhislistCoupon() {

		Coupons coupons = restTemplate.getForEntity("http://groupon-coupon/selectcoupon/{couponid}/", Coupons.class)
				.getBody();
		Wishlist wishlist = new Wishlist();
		wishlist.setCoupons(coupons);
		return new ResponseEntity<Wishlist>(wishlist, HttpStatus.CONTINUE);
	}
}
