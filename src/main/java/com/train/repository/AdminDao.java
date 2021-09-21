package com.train.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.train.model.TrainDetails;

@Repository
public interface AdminDao extends JpaRepository<TrainDetails, Integer> {
// 
	@Query(value ="select * from train_details where tname=:n",nativeQuery=true)
	public List<TrainDetails> getTrainByName(@Param("n") String name);
	
	@Query(value ="select * from train_details where date =:d",nativeQuery=true)
	public List<TrainDetails> getTrainByDate(@Param("d") Date date);
	


	@Query(value = "select * from train_details where tid=:id ", nativeQuery = true)
	  public TrainDetails getAvailableSeat(@Param("id") int trainid);

	@Query(value = "select tname from train_details where tid=:id ", nativeQuery = true)
	public String getMyTrainId(@Param("id") int trainid);

}
