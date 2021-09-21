package com.train.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PaymentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	private Integer amount;
	private String receipt;
	private String status;
	@ManyToOne
	private Customer customer;
	private String paymentId;
	public PaymentDetails() {
		// TODO Auto-generated constructor stub
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public PaymentDetails(Long orderId, Integer amount, String receipt, String status, Customer customer,
			String paymentId) {
		super();
		this.orderId = orderId;
		this.amount = amount;
		this.receipt = receipt;
		this.status = status;
		this.customer = customer;
		this.paymentId = paymentId;
	}
	
}
