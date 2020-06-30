package com.couponsdeal.couponsdeal.controller;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.couponsdeal.couponsdeal.entity.Admin;
import com.couponsdeal.couponsdeal.entity.CardDetails;
import com.couponsdeal.couponsdeal.entity.Coupons;
import com.couponsdeal.couponsdeal.entity.Customer;
import com.couponsdeal.couponsdeal.entity.CustomerList;
import com.couponsdeal.couponsdeal.entity.Doctor;
import com.couponsdeal.couponsdeal.entity.DoctorList;
import com.couponsdeal.couponsdeal.entity.Payment;
import com.couponsdeal.couponsdeal.entity.Wishlist;
import com.couponsdeal.couponsdeal.service.CustomerService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@RestController
@CrossOrigin("*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private RestTemplate restTemplate;

	private String email = "";

	@Autowired
	private MongoOperations mongoOperations;

	@PostMapping("/signUp")
	public ResponseEntity<Customer> signUpDetailsForCustomer(@RequestBody Customer customer) {
		/*
		 * System.out.println(customer);
		 */

		Customer customerFind = customerService.findByEmail(customer.getEmail());
		System.out.println(customerFind);
		if (customerFind != null) {
			return new ResponseEntity<Customer>(HttpStatus.CONFLICT);
		}

		Customer status = customerService.addDetails(customer);

		return new ResponseEntity<Customer>(status, HttpStatus.CREATED);

	}

	@PostMapping("/signIn")
	public ResponseEntity<Customer> logInDetailsForCustomer(@RequestBody Customer customerui) {

		if (customerui.getEmail() == "admin@coupons.com") {
			adminLogin();
		}

		Customer customer = customerService.findByEmail(customerui.getEmail());
		if (customer == null) {
			return new ResponseEntity<Customer>(customer, HttpStatus.NOT_FOUND);
		}
		if ((customer.getEmail().equals(customerui.getEmail())
				&& (customer.getPassword().equals(customerui.getPassword())))) {

			this.email = customer.getEmail();

			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}

		return new ResponseEntity<Customer>(HttpStatus.UNAUTHORIZED);

	}
	
	@GetMapping("/allcustomers")
	public ResponseEntity<CustomerList> listOfAllCustomers() {
		List<Customer> customersList = customerService.findAll();

		CustomerList customerList = new CustomerList();
		customerList.setCustomers(customersList);
		return new ResponseEntity<CustomerList>(customerList, HttpStatus.OK);

	}

	@GetMapping("/deletecustomer/{email}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable String email) {
		customerService.deleteByEmail(email);

		return new ResponseEntity<Customer>(HttpStatus.OK);

	}

	@GetMapping("/adminsignin")
	public Admin adminLogin() {

		Admin admin1 = restTemplate.getForEntity("http://couponsdeal-admin/adminsignin", Admin.class).getBody();

		return admin1;

	}

	@PostMapping("/payment")
	public ResponseEntity<Payment> payment(@RequestBody Payment payment) throws Exception {

		payment.setEmail(this.email);

		HttpEntity<Payment> requestEntity = new HttpEntity<Payment>(payment);

		Payment payment1 = restTemplate
				.exchange("http://couponsdeal-payment/payment", HttpMethod.POST, requestEntity, Payment.class).getBody();
		return new ResponseEntity<Payment>(payment1, HttpStatus.ACCEPTED);
	}

	@GetMapping("/mypayments")
	public ResponseEntity<Payment> myPayments() throws Exception {

		Payment myPayments = restTemplate.getForEntity("http://couponsdeal-payment/mypayments/" + this.email, Payment.class)
				.getBody();

		return new ResponseEntity<Payment>(myPayments, HttpStatus.OK);

	}

	@PostMapping("/addwishlist")
	public ResponseEntity<Wishlist> addToWishlist(@RequestBody Coupons coupons) throws Exception{

		System.out.println(coupons.getCouponId());
		Wishlist wishlist = new Wishlist(); // creating new wishlist
		wishlist.setCoupons(coupons);
		wishlist.setEmail(this.email);

		HttpEntity<Wishlist> requestEntity = new HttpEntity<Wishlist>(wishlist);

		Wishlist status = restTemplate
				.exchange("http://couponsdeal-wishlist/addwishlist", HttpMethod.POST, requestEntity, Wishlist.class)
				.getBody();

		return new ResponseEntity<Wishlist>(status, HttpStatus.OK);

	}

	@GetMapping("/mywishlist")
	public ResponseEntity<Coupons> showWislistItems() {

		Wishlist wishlist = restTemplate
				.getForEntity("http://couponsdeal-wishlist/mywishlist" + this.email, Wishlist.class).getBody();

		return new ResponseEntity<Coupons>(wishlist.getCoupons(), HttpStatus.OK);

	}

	@GetMapping("/deletewishlist")
	public ResponseEntity<Wishlist> deleteWhishlist() throws Exception {
		Wishlist wishlist = restTemplate
				.getForEntity("http://couponsdeal-wishlist/deletewishlist/" + this.email, Wishlist.class).getBody();
		return new ResponseEntity<Wishlist>(HttpStatus.OK);
	}
	
	@GetMapping("/doctorlist")
	public ResponseEntity<DoctorList> listOfDoctors() throws Exception {

		DoctorList status = restTemplate.getForEntity("http://couponsdeal-doctor/doctorlist", DoctorList.class)
				.getBody();

		if (status == null) {
			return new ResponseEntity<DoctorList>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<DoctorList>(status, HttpStatus.OK);

	}

	@PostMapping("/bookeddoctor")
	public ResponseEntity<Doctor> bookedDoctor(@RequestBody Doctor doctorEmail) {

		Doctor status = restTemplate
				.getForEntity("http://couponsdeal-doctor/bookeddoctor/" + doctorEmail.getEmail(), Doctor.class)
				.getBody();

		return new ResponseEntity<Doctor>(HttpStatus.OK);

	}

	@GetMapping("/selectcoupon/{couponid}")
	public ResponseEntity<Coupons> selectedCoupon(@PathVariable String couponId) {

		Coupons coupons = restTemplate.getForEntity("http://couponsdeal-coupon/selectcoupon/{couponId}/", Coupons.class)
				.getBody();
		
		return new ResponseEntity<Coupons>(coupons, HttpStatus.OK);
	}

}
