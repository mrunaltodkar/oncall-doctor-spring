package com.couponsdeal.couponsdeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableEurekaServer
@SpringBootApplication
public class CouponsdealApplication {

	public static void main(String[] args) {
		SpringApplication.run(CouponsdealApplication.class, args);
	}

	@Bean
	//@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
