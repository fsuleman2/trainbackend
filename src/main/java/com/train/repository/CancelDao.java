package com.train.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.train.model.Cancellation;

@Repository
public interface CancelDao extends JpaRepository<Cancellation, Integer>{

	@Query(value = "select * from cancellation where customer_c_id=:id", nativeQuery = true)
	public List<Cancellation> getAllCancelById(@Param("id") long userid);
	

}
