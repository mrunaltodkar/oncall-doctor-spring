package com.couponsdeal.orders.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.couponsdeal.orders.entity.Orders;

@Repository
public interface OrdersDao extends MongoRepository<Orders, String>{

}
