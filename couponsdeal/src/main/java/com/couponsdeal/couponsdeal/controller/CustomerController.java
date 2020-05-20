package com.couponsdeal.couponsdeal.controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.couponsdeal.couponsdeal.entity.CardDetails;
import com.couponsdeal.couponsdeal.entity.Coupons;
import com.couponsdeal.couponsdeal.entity.Customer;
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

	/*
	 * @Autowired private MongoOperations mongoOperations;
	 */
	@PostMapping("/signUp")
	public ResponseEntity<Customer> signUpDetailsForCustomer(@RequestBody Customer customer) {
		 System.out.println(customer);
		//Customer customer1 = new Customer();
		Customer customerFind = customerService.findByEmail(customer.getEmail());
		System.out.println(customerFind);
		if (customerFind != null) {
			return new ResponseEntity<Customer>(HttpStatus.CONFLICT);
		}

		Wishlist wishlist = new Wishlist();

		Payment payment = new Payment();
		CardDetails cardDetails = new CardDetails();
		payment.setCardDetails(cardDetails);

		Coupons coupons = new Coupons();
		wishlist.setCoupons(coupons);
		customer.setPayment(payment);
		customer.setWishlist(wishlist);
		Customer status = customerService.addDetails(customer);

		return new ResponseEntity<Customer>(status, HttpStatus.CREATED);

	}

	@PostMapping("/signIn")
	public ResponseEntity<Customer> logInDetailsForCustomer(@RequestBody Customer customer) {

		/*
		 * System.out.println(customer.getEmail());
		 * System.out.println(customer.getPassword());
		 */
		Customer customer1 = customerService.findByEmail(customer.getEmail());

		if (customer1 == null) {
			return new ResponseEntity<Customer>(customer1, HttpStatus.NOT_FOUND);

		}
		if ((customer1.getEmail().equals(customer.getEmail())
				&& (customer1.getPassword().equals(customer.getPassword())))) {

			this.email = customer.getEmail();
			System.out.println(this.email);

			return new ResponseEntity<Customer>(customer1, HttpStatus.OK);
		}

		return new ResponseEntity<Customer>(HttpStatus.UNAUTHORIZED);

	}

	@GetMapping("/selectcoupon/{couponId}")
	public ResponseEntity<Coupons> selectedCoupon(@PathVariable String couponId) {

		Coupons coupons = restTemplate.getForEntity("http://COUPONSDEAL-COUPON/selectcoupon/" + couponId, Coupons.class)
				.getBody();

		return new ResponseEntity<Coupons>(coupons, HttpStatus.CONTINUE);

	}

	@PostMapping("/whishlist/{couponid}")
	public ResponseEntity<Wishlist> whishlistCoupon(@PathVariable String couponId) {

		Customer customer = customerService.findByEmail(this.email);

		Wishlist wishlist = restTemplate.getForEntity("http://couponsdeal-wishlist/wishlist/{couponid}", Wishlist.class)
				.getBody();

		customer.setWishlist(wishlist);
		return new ResponseEntity<Wishlist>(wishlist, HttpStatus.CREATED);

	}

	@PostMapping("/payment")
	public ResponseEntity<Payment> payment(@RequestBody Payment payment) throws Exception {
		// System.out.println(this.email);
		Customer customer = customerService.findByEmail(this.email);
		System.out.println(this.email);
		System.out.println(customer.getPayment().getCardDetails().getCardHolderName());

		HttpEntity<Payment> requestEntity = new HttpEntity<Payment>(payment);

		Payment payment1 = restTemplate
				.exchange("http://couponsdeal-payment/payment", HttpMethod.POST, requestEntity, Payment.class).getBody();

		customer.setPayment(payment1);
		MongoClient client = new MongoClient("localhost", 27017);
		MongoDatabase db = client.getDatabase("coupons");
		MongoCollection<Document> collection = db.getCollection("customer");

		collection.deleteOne(new Document("email", this.email));
		customerService.addDetails(customer);

		return new ResponseEntity<Payment>(payment1, HttpStatus.OK);
	}

}
