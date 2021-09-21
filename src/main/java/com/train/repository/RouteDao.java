package com.train.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.train.model.RouteDetails;

@Repository
public interface RouteDao extends JpaRepository<RouteDetails, Integer> {
	@Query(value ="select  * from route_details where startpoint=:startpoint and td_tid in (select td_tid from route_details where startpoint=:lastpoint) ",nativeQuery=true)
	  public List<RouteDetails>getTrain(@Param("startpoint") String startpoint,@Param("lastpoint") String lastpoint);
	  
	@Query(value ="select  * from route_details where startpoint=:start and td_tid in (select td_tid from route_details where startpoint=:end) and date=:date ",nativeQuery=true)
	  public List<RouteDetails>getTrainBydateloc(@Param("start") String startpoint,@Param("end") String lastpoint,@Param("date") Date date);

	  @Query(value = "select  distance from route_details where startpoint=:start and td_tid=:trainid  ", nativeQuery = true)
	  public int  getTrainBydatelocdis(@Param("start") String startpoint,@Param("trainid") int trainid);
	  
	  @Query(value = "select  arrival from route_details where startpoint=:start and td_tid=:trainid", nativeQuery = true)
	  public Time getTimeOfDest(@Param("start") String end,@Param("trainid") int id);
	  
	  @Query(value = "select  * from route_details where td_tid=:id", nativeQuery = true)
	  public List<RouteDetails> getRoutebyTrainid(@Param("id") int trainid);
}

