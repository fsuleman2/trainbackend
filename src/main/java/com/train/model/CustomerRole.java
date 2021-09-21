package com.train.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CustomerRole {
	public Long getCustomerRoleId() {
		return customerRoleId;
	}
	public CustomerRole() {
		// TODO Auto-generated constructor stub
	}

	public void setCustomerRoleId(Long customerRoleId) {
		this.customerRoleId = customerRoleId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public CustomerRole(Long customerRoleId, Customer customer, Role role) {
		super();
		this.customerRoleId = customerRoleId;
		this.customer = customer;
		this.role = role;
	}

	@Id
	@GeneratedValue
	private Long customerRoleId;
	
	//customer
	@ManyToOne(fetch = FetchType.EAGER)
	private Customer customer;
	
	//for role
	@ManyToOne
    private Role role;
}
