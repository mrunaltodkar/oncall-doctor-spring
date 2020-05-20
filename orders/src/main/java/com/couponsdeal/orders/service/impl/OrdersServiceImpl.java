package com.couponsdeal.orders.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couponsdeal.orders.dao.OrdersDao;
import com.couponsdeal.orders.entity.Orders;
import com.couponsdeal.orders.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersDao ordersDao;
	
	@Override
	public Orders addOrdersDetails(Orders orders) {
		return ordersDao.save(orders);
	}

}
