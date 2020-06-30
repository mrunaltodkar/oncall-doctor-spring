package com.couponsdeal.wishlist.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.couponsdeal.wishlist.entity.Wishlist;

@Repository
public interface WishlistDao extends MongoRepository<Wishlist, String> {

	Wishlist findByEmail(String email);
	
	void deleteByEmail(String email);
	
}
