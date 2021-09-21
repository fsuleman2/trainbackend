package com.train.exception;

public class CustomerFoundException extends Exception {
	public CustomerFoundException() {
		super("Customer with this Username is already there in DB !! try with another one");
	}
	public CustomerFoundException(String msg) {
		super(msg);
	}
}
