package com.couponsdeal.couponsdeal.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer {

	private String username;
	private String password;
	private long number;
	private String email;
	private Payment payment;
	private Wishlist wishlist;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String username, String password, long number, String email, Payment payment, Wishlist wishlist) {
		super();
		this.username = username;
		this.password = password;
		this.number = number;
		this.email = email;
		this.payment = payment;
		this.wishlist = wishlist;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}

	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", number=" + number + ", email=" + email
				+ ", payment=" + payment + ", whishlist=" + wishlist +  "]";
	}

}
