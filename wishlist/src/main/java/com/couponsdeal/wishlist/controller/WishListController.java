package com.couponsdeal.wishlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.couponsdeal.wishlist.entity.Wishlist;
import com.couponsdeal.wishlist.service.WishlistService;

@CrossOrigin("*")
@RestController
public class WishListController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WishlistService wishlistService;

	@PostMapping("/addwishlist")
	public ResponseEntity<Wishlist> getWislistCoupon(@RequestBody Wishlist wishlist) throws Exception {
		wishlistService.addWishlistDetails(wishlist);

		return new ResponseEntity<Wishlist>(HttpStatus.OK);
	}

	@GetMapping("/mywishlist/{email}")
	public ResponseEntity<Wishlist> showWishlist(@PathVariable String email) throws Exception {
		Wishlist status = wishlistService.findWishlistByEmail(email);
		return new ResponseEntity<Wishlist>(status, HttpStatus.OK);

	}

	@GetMapping("/deletewishlist/{email}")
	public ResponseEntity<Wishlist> deleteWishlist(@PathVariable String email) throws Exception {

		wishlistService.deleteWishlistByEmail(email);
		return new ResponseEntity<Wishlist>(HttpStatus.OK);
	}
}
