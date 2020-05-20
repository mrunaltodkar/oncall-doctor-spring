package com.couponsdeal.orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.couponsdeal.orders.service.OrdersService;


@Controller
public class OrdersController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OrdersService ordersService;

	
}
