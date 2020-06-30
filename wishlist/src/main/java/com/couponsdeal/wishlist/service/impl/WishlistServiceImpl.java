package com.couponsdeal.wishlist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsdeal.wishlist.dao.WishlistDao;
import com.couponsdeal.wishlist.entity.Wishlist;
import com.couponsdeal.wishlist.service.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	WishlistDao wishlistDao;
	
	@Override
	public Wishlist addWishlistDetails(Wishlist wishlist) {
		return wishlistDao.save(wishlist);
	}

	@Override
	public Wishlist findWishlistByEmail(String email) {
		return wishlistDao.findByEmail(email);
	}

	@Override
	public boolean deleteWishlistByEmail(String email) {
		wishlistDao.deleteByEmail(email);
		return true;
	}

}
