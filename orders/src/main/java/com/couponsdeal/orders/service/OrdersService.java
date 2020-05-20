package com.couponsdeal.orders.service;

import org.springframework.stereotype.Service;

import com.couponsdeal.orders.entity.Orders;

@Service
public interface OrdersService {
	public Orders addOrdersDetails(Orders orders);
	
}
