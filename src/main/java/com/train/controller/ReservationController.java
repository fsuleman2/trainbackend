package com.train.controller;

import java.sql.Date;
import java.util.Calendar;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.train.model.Cancellation;
import com.train.model.Customer;
import com.train.model.ReservationForm;
import com.train.service.ReservationService;

@RestController
@CrossOrigin
@RequestMapping("/reserve")
public class ReservationController {
	
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/add")
	public ReservationForm addReservationForm(@RequestBody ReservationForm reservationForm) {
		
//		System.out.println(date+" im from 33 line");
		reservationForm.setBookingDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
		System.out.println(reservationForm.getBookingDate()+"********Im from booking date");

		System.out.println("----------------------------------------");
		System.out.println(reservationForm);
		System.out.println("********************************************");
		return this.reservationService.addReservationForm(reservationForm);
	}
	
	@GetMapping("/view/{bookingId}")
	public ReservationForm viewReservationForm(@PathVariable("bookingId") int bookingId) {
		return this.reservationService.viewReservationForm(bookingId);
	} 

	@GetMapping("/cust/{custId}")
	public List<ReservationForm> viewReservationFormByCustId(@PathVariable("custId") Long custId) {
		return this.reservationService.getReservationByCustId(custId);
	}
	



	@GetMapping("/checkseats/{seat}/{id}")
	  public boolean checkSeatAvail(@PathVariable String seat,@PathVariable int id) {
	    return this.reservationService.checkSeatAvail(seat,id);
	  }
	
	@GetMapping("/cancel/{id}/{reason}")
	public boolean cancalTicket(@PathVariable int id,@PathVariable String reason) {
		return this.reservationService.cancelTicket(id,reason);
	}
	
	@GetMapping("/allCancel/{username}")
	public List<Cancellation> getAllCancelByUsername(@PathVariable String username)
	{
		return this.reservationService.getAllCancelByUsername(username);
	}
	
	@GetMapping("/allbooking/{username}")
	public List<ReservationForm> getAllBookingByUsername(@PathVariable String username)
	{
		System.out.println("****im executing bro!!!!****");
		return this.reservationService.getAllBookingByUsername(username);
	}
}
