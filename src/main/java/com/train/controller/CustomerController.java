package com.train.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.train.model.Customer;
import com.train.model.CustomerRole;
import com.train.model.PaymentDetails;
import com.train.model.Role;
import com.train.repository.PaymentRepository;
import com.train.service.CustomerService;
import com.razorpay.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin("*")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private PaymentRepository paymentRepository;
	
	@PostMapping("/create_payment")
	@ResponseBody
	public String createOrder(@RequestBody Map<String, Object> data,Principal principal) throws Exception {
//		System.out.println("im executing");
//		System.out.println(data);
		int amount = Integer.parseInt(data.get("amount").toString());
		
		RazorpayClient client =new RazorpayClient("rzp_test_iqRjbnSjncHo5L", "KjiFXbgYLPAn0la3QlYlhh3z");
		JSONObject ob = new JSONObject();
		ob.put("amount", amount*100);
		ob.put("currency", "INR");
		ob.put("receipt", "txn_235425");
		//creating order
		
		Order order=client.Orders.create(ob);
		System.out.println(order);
		//we save this data inside data base
		PaymentDetails paymentDetails = new PaymentDetails();
		int amount1 = Integer.parseInt(data.get("amount").toString());
		System.out.println(amount1);
//		Integer result1=amount1/100;
		paymentDetails.setAmount(amount1);
		paymentDetails.setPaymentId(order.get("id"));
		paymentDetails.setStatus("paid");
		paymentDetails.setCustomer(this.customerService.getCustomerByUsername(principal.getName()));
		paymentDetails.setReceipt(order.get("receipt"));
		
		this.paymentRepository.save(paymentDetails);
		return order.toString();
	}
	@PutMapping("/update_payment")
	public ResponseEntity<?> updatePayment(@RequestBody Map<String, Object> data){
		PaymentDetails paymentDetails1 = this.paymentRepository.findByPaymentId(data.get("order_id").toString());
		
		paymentDetails1.setPaymentId(data.get("payment_id").toString());
		this.paymentRepository.save(paymentDetails1);
		System.out.println(data);
		System.out.println(data);
		return ResponseEntity.ok(paymentDetails1);
	}
	
	/***************************************************/
	
	//creating customer by username
	@PostMapping("/")
	public Customer createCustomer(@RequestBody Customer customer) throws Exception {
		Set<CustomerRole> roles =  new HashSet<>();
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("NORMAL");
		CustomerRole customerRole = new CustomerRole(); 
		customerRole.setCustomer(customer);
		customerRole.setRole(role);
		roles.add(customerRole);
		return this.customerService.createCustomer(customer, roles);
	}
	
	@GetMapping("/{username}")
	public Customer getCutomer(@PathVariable String username) {
		return this.customerService.getCustomerByUsername(username);
	}
	
	@DeleteMapping("/{customerId}")
	public void deleteUserById(@PathVariable Long customerId) {
		this.customerService.deleteCustomerById(customerId);
	}
}
