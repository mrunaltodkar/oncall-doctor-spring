package com.couponsdeal.coupons.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.couponsdeal.coupons.entity.Coupons;

@Repository
public interface CouponsDao extends MongoRepository<Coupons, String> {

	Coupons findByCouponId(long couoponID);

}
