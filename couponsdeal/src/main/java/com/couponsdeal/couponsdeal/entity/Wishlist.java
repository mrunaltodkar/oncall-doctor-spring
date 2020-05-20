package com.couponsdeal.couponsdeal.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Wishlist {

	private Coupons coupons;

	public Wishlist(Coupons coupons) {
		super();
		this.coupons = coupons;
	}

	public Wishlist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Coupons getCoupons() {
		return coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

}
