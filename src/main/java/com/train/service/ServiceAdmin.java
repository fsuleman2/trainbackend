package com.train.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.train.model.ReservationForm;
import com.train.model.RouteDetails;
import com.train.model.TrainDetails;

public interface ServiceAdmin {
	public TrainDetails createTrain(TrainDetails traindetails);

	public List<TrainDetails> getAllTrains();
	
	public List<TrainDetails> getTrainByName(String train_name);
	
	public Optional<TrainDetails> getTrainById(int id);
	
	public List<TrainDetails> getTrainByDate(Date date);

	public List<RouteDetails> getAllTrainsByRoute(String startpoint, String lastpoint);

	public List<RouteDetails> getAllTrainsByRouteDate(String start, String end, Date date)throws Exception;

	public RouteDetails createRoute(RouteDetails routedetails);

	public List<RouteDetails> getByRoute(int id);
	
	public List<RouteDetails> getAllRoutes();
	
	public ReservationForm addBooking(ReservationForm reservationForm);
}
