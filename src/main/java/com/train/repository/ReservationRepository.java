package com.train.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.train.model.ReservationForm;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationForm, Integer> {
	
	@Query(value = "select * from reservation where customer_c_id=:id", nativeQuery = true)
	  public List<ReservationForm> getReservationbyCustId(@Param("id") Long custid);
	
	@Query(value = "select count(coach_id) from reservation where train_details_tid=:id and coach_type=:coachtype", nativeQuery = true)
	  public int getCoachNumber(@Param("id") int trainid,@Param("coachtype") String coachtype);
	
	@Query(value = "select * from reservation where train_details_tid=:id and coach_type=:coachtype and status=1", nativeQuery = true)
	public List<ReservationForm> getCancelTicket(@Param("id") int trainid,@Param("coachtype") String coachtype);

	@Query(value = "select * from reservation where booking_id=:id", nativeQuery = true)
	public ReservationForm findByBookingID(@Param("id") int id);

	@Query(value = "select * from reservation where customer_c_id=:id and status=false", nativeQuery = true)
	public List<ReservationForm> getAllBookingById(@Param("id") long userid);

}
