package com.couponsdeal.couponsdeal.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Wishlist {

	private Coupons coupons;
	private String email;

	public Wishlist() {
		super();
	}

	public Wishlist(Coupons coupons, String email) {
		super();
		this.coupons = coupons;
		this.email = email;
	}

	public Coupons getCoupons() {
		return coupons;
	}

	public void setCoupons(Coupons coupons) {
		this.coupons = coupons;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;

	}

}
