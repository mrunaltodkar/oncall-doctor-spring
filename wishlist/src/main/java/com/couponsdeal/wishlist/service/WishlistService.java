package com.couponsdeal.wishlist.service;

import org.springframework.stereotype.Service;

import com.couponsdeal.wishlist.entity.Wishlist;

@Service
public interface WishlistService {

	Wishlist addWishlistDetails(Wishlist wishlist);

	Wishlist findWishlistByEmail(String email);
	
	boolean deleteWishlistByEmail(String email);
	
}
