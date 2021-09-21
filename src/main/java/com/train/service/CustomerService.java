package com.train.service;

import java.util.Set;

import com.train.exception.CustomerFoundException;
import com.train.model.Customer;
import com.train.model.CustomerRole;

public interface CustomerService {
	public Customer createCustomer(Customer customer, Set<CustomerRole> customerRoles) throws CustomerFoundException;

	public Customer getCustomerByUsername(String username);

	public void deleteCustomerById(Long customerId);
}
