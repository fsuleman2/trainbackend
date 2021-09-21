package com.train;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import com.train.exception.CustomerNotFoundException;
import com.train.model.Customer;
import com.train.model.CustomerRole;
import com.train.model.Role;
import com.train.service.CustomerService;
import com.train.service.impl.CustomerServiceImpl;

class CustomerServiceTest {
	public  static CustomerService service = null;
	
	public static Customer customer = null;
	
	@BeforeAll
	public  static void  setup() {
		service = new CustomerServiceImpl();
		customer = new Customer();
		
	}
	@Test
	public void testCustomerLoginUserNameforExisting() throws CustomerNotFoundException{
		customer.setName("Vinay");
		assertTrue("Vinay".equals(customer.getName()));
	}
	@Test
	public void testCustomerLoginUserNameforNonExisting() throws CustomerNotFoundException{
		customer.setName("Vinay");
		assertFalse("Viny".equals(customer.getName()));
	}
	@Test
	public void testCustomerLoginUserNameforExistingEqualsMethod() throws CustomerNotFoundException{
		customer.setName("Vinay");
		assertEquals("Vinay",customer.getName());
	}
	@Test
	public void testCustomerPasswordforExisting() throws CustomerNotFoundException{
		customer.setPassword("vinay@123");
		assertTrue("vinay@123".equals(customer.getPassword()));
	}
	@Test
	public void testCustomerPasswordforNonExisting() throws CustomerNotFoundException{
		customer.setPassword("vinay@123");
		assertFalse("vinay123".equals(customer.getPassword()));
	}
	@Test
	public void testCustomerPasswordforExistingEqualsMethod() throws CustomerNotFoundException{
		customer.setPassword("vinay@123");
		assertEquals("vinay@123",customer.getPassword());
	}
	@Test
	public void testCustomerPhoneforExisting() throws CustomerNotFoundException{
		customer.setPhone("7032055646");
		assertTrue("7032055646".equals(customer.getPhone()));
	}
	@Test
	public void testCustomerPhoneforNonExisting() throws CustomerNotFoundException{
		customer.setPhone("7032055646");
		assertFalse("703205546".equals(customer.getPhone()));
	}
	@Test
	public void testCustomerPhoneforExistingEqualsMethod() throws CustomerNotFoundException{
		customer.setPhone("7032055646");
		assertEquals("7032055646",customer.getPhone());
	}
	@Test
	public void testCustomerEmailforExisting() throws CustomerNotFoundException{
		customer.setEmail("vinayvarshith1@gmail.com");
		assertTrue("vinayvarshith1@gmail.com".equals(customer.getEmail()));
	}
	@Test
	public void testCustomerEmailforNonExisting() throws CustomerNotFoundException{
		customer.setEmail("vinayvarshith1@gmail.com");
		assertFalse("vinayvarshith1@gmil.com".equals(customer.getEmail()));
	}
	@Test
	public void testCustomerEmailforExistingEqualsMethod() throws CustomerNotFoundException{
		customer.setEmail("vinayvarshith1@gmail.com");
		assertEquals("vinayvarshith1@gmail.com",customer.getEmail());
	}
	@Test
	public void testCustomerAdharforExisting() throws CustomerNotFoundException{
		customer.setAadhar("478070322784");
		assertTrue("478070322784".equals(customer.getAadhar()));
	}
	@Test
	public void testCustomerAdharforNonExisting() throws CustomerNotFoundException{
		customer.setAadhar("478070322784");
		assertFalse("47807032274".equals(customer.getAadhar()));
	}
	@Test
	public void testCustomerAdharforExistingEqualsMethod() throws CustomerNotFoundException{
		customer.setAadhar("478070322784");
		assertEquals("478070322784", customer.getAadhar());
	}
	
}
