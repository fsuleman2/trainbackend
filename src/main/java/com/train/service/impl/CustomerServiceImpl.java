package com.train.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.exception.CustomerFoundException;
import com.train.model.Customer;
import com.train.model.CustomerRole;
import com.train.repository.CustomerRepository;
import com.train.repository.RoleRepository;
import com.train.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private RoleRepository roleRepository;
	//creating user
	@Override
	public Customer createCustomer(Customer customer, Set<CustomerRole> customerRoles) throws CustomerFoundException {
		Customer local = this.customerRepository.findByUsername(customer.getUsername());
		 if (local != null) {
	            System.out.println("Customer is already there !!");
	            throw new CustomerFoundException();
	        } else {
	            //customer create
	            for (CustomerRole cr : customerRoles) {
	                roleRepository.save(cr.getRole());
	            }

	            customer.getCustomerRoles().addAll(customerRoles);
	            local = this.customerRepository.save(customer);

	        }
		return local;
	}
	@Override
	public Customer getCustomerByUsername(String username) {
		
		return this.customerRepository.findByUsername(username);
	}
	@Override
	public void deleteCustomerById(Long customerId) {
		this.customerRepository.deleteById(customerId);
		
	}

}
