package com.train.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.model.Cancellation;
import com.train.model.Customer;
import com.train.model.ReservationForm;
import com.train.model.TrainDetails;

import com.train.repository.AdminDao;
import com.train.repository.CancelDao;
import com.train.repository.CustomerRepository;
import com.train.repository.ReservationRepository;
import com.train.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	@Autowired
	private AdminDao admindao;
	@Autowired
	private CustomerRepository cd;
	@Autowired
	private CancelDao canceldao;

	@Override
	public ReservationForm addReservationForm(ReservationForm reservationForm) {
		System.out.println("11111111111111111111111111111111111111111111111111111111111111");
		System.out.println(reservationForm.getTrainDetails().getTid());
		
		reservationForm.getTrainDetails().setTname(admindao.getMyTrainId(reservationForm.getTrainDetails().getTid()));

		reservationForm.setStatus(false);
		System.out.println("2");
		// reservationForm.setStatus(true);

		List<Customer> cust = cd.findAll();
		for (Customer customer : cust) {
			if (customer.getUsername().equals(reservationForm.getCustomer().getUsername()))
				reservationForm.getCustomer().setcId(customer.getcId());
		}
		System.out.println("3");
		TrainDetails td = this.admindao.getAvailableSeat(reservationForm.getTrainDetails().getTid());
		List<ReservationForm> rv = this.reservationRepository
				.getCancelTicket(reservationForm.getTrainDetails().getTid(), reservationForm.getCoachType());
		if (!rv.isEmpty()) {
			rv.get(0).setSource(reservationForm.getSource());
			rv.get(0).setDestination(reservationForm.getDestination());
			rv.get(0).setTravelDate(reservationForm.getTravelDate());
			rv.get(0).setPName(reservationForm.getPName());
			rv.get(0).setPAge(reservationForm.getPAge());
			rv.get(0).setStatus(false);
			rv.get(0).setPGender(reservationForm.getPGender());
			rv.get(0).setpDisabled(reservationForm.ispDisabled());
			rv.get(0).setPrice(reservationForm.getPrice());
			rv.get(0).setTotalDistance(reservationForm.getTotalDistance());
			rv.get(0).setBookingDate(reservationForm.getBookingDate());
			rv.get(0).getCustomer().setcId(reservationForm.getCustomer().getcId());
			reservationRepository.save(rv.get(0));
			return rv.get(0);
		} else {
			System.out.println("else part");
			if (reservationForm.getCoachType().equals("availAcSleeperSeat")) {
				System.out.println("1.1");
				int Total_seat = td.getTotalAcSleeperSeat();
				String[] coach = { "1A", "2A", "3A", "4A", "5A", "6A", "7A", "8A", "9A" };
				int Current_total_seat_booked = this.reservationRepository
						.getCoachNumber(reservationForm.getTrainDetails().getTid(), "availAcSleeperSeat");
				String Coach_Id = coach[(Current_total_seat_booked) / 50];
				int seat_number_allocated = ((Current_total_seat_booked) % 50) + 1;
				reservationForm.setCoachId(Coach_Id);
				reservationForm.setSeatNumber(seat_number_allocated);
				int avail = td.getAvailAcSleeperSeat();

				td.setAvailAcSleeperSeat(avail - 1); // updating available seats
				System.out.println("1.2");
				admindao.save(td);
				System.out.println("1.3");
				return this.reservationRepository.save(reservationForm);

			} else if (reservationForm.getCoachType().equals("availAcSittingSeat")) {
				int Total_seat = td.getTotalAcSittingSeat();
				String[] coach = { "1B", "2B", "3B", "4B", "5B", "6B", "7B", "8B", "9B" };
				int Current_total_seat_booked = this.reservationRepository
						.getCoachNumber(reservationForm.getTrainDetails().getTid(), "availAcSittingSeat");
				String Coach_Id = coach[(Current_total_seat_booked) / 70];
				int seat_number_allocated = ((Current_total_seat_booked) % 70) + 1;
				reservationForm.setCoachId(Coach_Id);
				reservationForm.setSeatNumber(seat_number_allocated);
				int avail = td.getAvailAcSittingSeat();
				td.setAvailAcSittingSeat(avail - 1);
				admindao.save(td);
				return this.reservationRepository.save(reservationForm);

			} else if (reservationForm.getCoachType().equals("availNonAcSleeperSeat")) {
				int Total_seat = td.getTotalNonAcSittingSeat();
				String[] coach = { "1C", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C" };
				int Current_total_seat_booked = this.reservationRepository
						.getCoachNumber(reservationForm.getTrainDetails().getTid(), "availNonAcSleeperSeat");
				String Coach_Id = coach[(Current_total_seat_booked) / 50];
				int seat_number_allocated = ((Current_total_seat_booked) % 50) + 1;
				reservationForm.setCoachId(Coach_Id);
				reservationForm.setSeatNumber(seat_number_allocated);
				int avail = td.getAvailNonAcSleeperSeat();
				td.setAvailNonAcSleeperSeat(avail - 1);
				admindao.save(td);
				return this.reservationRepository.save(reservationForm);

			} else {
				int Total_seat = td.getTotalNonAcSittingSeat();
				String[] coach = { "1D", "2D", "3D", "4D", "5D" };
				int Current_total_seat_booked = this.reservationRepository
						.getCoachNumber(reservationForm.getTrainDetails().getTid(), "availNonAcSittingSeat");
				String Coach_Id = coach[(Current_total_seat_booked) / 70];
				int seat_number_allocated = ((Current_total_seat_booked) % 70) + 1;
				reservationForm.setCoachId(Coach_Id);
				reservationForm.setSeatNumber(seat_number_allocated);
				int avail = td.getAvailNonAcSittingSeat();
				td.setAvailNonAcSittingSeat(avail - 1);
				admindao.save(td);
				return this.reservationRepository.save(reservationForm);

			}
		}

//		return this.reservationRepository.save(reservationForm);
	}

//	AcSleeper=50 {1A,2A,3A,4A,5A}
//	AcSitting=70 {1B.........
//	NonAcSleeper=50{1C............
//	NonAcSitting=70{1D...........

	@Override
	public ReservationForm viewReservationForm(int bookingid) {
		// TODO Auto-generated method stub
		return this.reservationRepository.findById(bookingid).get();
	}

	@Override
	public List<ReservationForm> getReservationByCustId(Long custid) {
		// TODO Auto-generated method stub
		return this.reservationRepository.getReservationbyCustId(custid);
	}

	@Override
	public boolean checkSeatAvail(String seat, int id) {
		System.out.println(seat);
		TrainDetails td = this.admindao.getAvailableSeat(id);
		System.out.println(td);
		System.out.println("-----------------------------------------------------------------");

		List<ReservationForm> rv = this.reservationRepository.getCancelTicket(id, seat);
		if (!rv.isEmpty())
			return true;
		else {

			if (seat.equals("availNonAcSittingSeat")) {
				System.out.println(1);
				if (td.getAvailNonAcSittingSeat() > 0)
					return true;
				else
					return false;

			} else if (seat.equals("availAcSittingSeat")) {
				System.out.println(2);
				if (td.getAvailAcSittingSeat() > 0)
					return true;
				else
					return false;

			} else if (seat.equals("availNonAcSleeperSeat")) {
				System.out.println(3);
				if (td.getAvailNonAcSleeperSeat() > 0)
					return true;
				else
					return false;

			} else {
				System.out.println(4);
				if (td.getAvailAcSleeperSeat() > 0)
					return true;
				else
					return false;
			}
		}

	}

	@Override
	public boolean cancelTicket(int id, String reason) {

		ReservationForm rv = reservationRepository.findByBookingID(id);
		System.out.println(rv);
		if (!rv.isStatus()) {
			
			Cancellation cancellation = new Cancellation();
			cancellation.setReason(reason);
			cancellation.setSource(rv.getSource());
			cancellation.setDestination(rv.getDestination());
			cancellation.setTravelDate(rv.getTravelDate());
			cancellation.setCoachType(rv.getCoachType());
			cancellation.setPName(rv.getPName());
			cancellation.setPAge(rv.getPAge());
			cancellation.setStatus(false);
			cancellation.setPGender(rv.getPGender());
			cancellation.setPDisabled(rv.ispDisabled());
			cancellation.setPrice(rv.getPrice());
			cancellation.setTotalDistance(rv.getTotalDistance());
			cancellation.setSeatNumber(rv.getSeatNumber());
			cancellation.setCoachId(rv.getCoachId());
			cancellation.setBookingDate(rv.getBookingDate());
			int tid = rv.getTrainDetails().getTid();
			System.out.println("----------------------" + tid);
			cancellation.setTrainDetails(rv.getTrainDetails());
			cancellation.setCustomer(rv.getCustomer());
			System.out.println("**************************************");
			System.out.println(cancellation);
			System.out.println("=======================================");
			rv.setStatus(true);
			reservationRepository.save(rv);
			canceldao.save(cancellation);
			
			return true;
		} else
			return false;

	}

	@Override
	public List<Cancellation> getAllCancelByUsername(String username) {
		long userid = 0;

		List<Customer> cust = cd.findAll();
		for (Customer customer : cust) {
			if (customer.getUsername().equals(username))
				userid = customer.getcId();
		}
		return this.canceldao.getAllCancelById(userid);
	}

	@Override
	public List<ReservationForm> getAllBookingByUsername(String username) {
		long userid = 0;

		List<Customer> cust = cd.findAll();
		for (Customer customer : cust) {
			if (customer.getUsername().equals(username))
				userid = customer.getcId();
		}
		return this.reservationRepository.getAllBookingById(userid);
	}

}
