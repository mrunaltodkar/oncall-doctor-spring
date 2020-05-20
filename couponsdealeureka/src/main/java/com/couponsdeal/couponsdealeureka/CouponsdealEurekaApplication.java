package com.couponsdeal.couponsdealeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CouponsdealEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponsdealEurekaApplication.class, args);
	}

}
