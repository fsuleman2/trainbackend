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
@Table
public class Cancellation {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cancelId;
	private String reason;
	
	private String source;
	private String destination;
	private Date travelDate;
	private String coachType;
	private String pName;
	private String pAge;
	private boolean status=false;
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
	
	
}
