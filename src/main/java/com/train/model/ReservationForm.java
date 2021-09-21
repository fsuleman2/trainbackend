package com.train.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "reservation")
public class ReservationForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookingId;
	private String source;
	private String destination;
	private Date travelDate;
	private String coachType;
	private String pName;
	private String pAge;
	private boolean status;
	private String pGender;
	private boolean pDisabled;
	private float price;
	private int totalDistance;
	private int seatNumber;
	private String coachId;
	private Date bookingDate;
	@ManyToOne(fetch = FetchType.EAGER)
	private TrainDetails trainDetails;
	@ManyToOne(fetch = FetchType.EAGER)
	private Customer customer;
	
	
	public boolean ispDisabled() {
		return pDisabled;
	}
	public void setpDisabled(boolean pDisabled) {
		this.pDisabled = pDisabled;
	}
	
	

	

//	public ReservationForm() {
//		// TODO Auto-generated constructor stub
//	}
//
//
//	public ReservationForm(String source, String destination, Date travelDate, String coachType,
//			String pName, String pAge, String pGender, boolean pDisabled, float price, int totalDistance,
//			int seatNumber, int coachId, Date bookingDate) {
//		this.source = source;
//		this.destination = destination;
//		this.travelDate = travelDate;
//		this.coachType = coachType;
//		this.pName = pName;
//		this.pAge = pAge;
//		this.pGender = pGender;
//		this.pDisabled = pDisabled;
//		this.price = price;
//		this.totalDistance = totalDistance;
//		this.seatNumber = seatNumber;
//		this.coachId = coachId;
//		this.bookingDate = bookingDate;
//	}
	
	
	

}
