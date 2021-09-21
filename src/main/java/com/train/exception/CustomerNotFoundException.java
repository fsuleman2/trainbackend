package com.train.exception;

public class CustomerNotFoundException extends Exception{
	public CustomerNotFoundException() {
		super("Customer Already Exist!!");
	}
	public CustomerNotFoundException(String msg) {
		super(msg);
	}
}
