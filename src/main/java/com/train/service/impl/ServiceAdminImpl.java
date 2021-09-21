package com.train.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.train.model.ReservationForm;
import com.train.model.RouteDetails;
import com.train.model.TrainDetails;
import com.train.repository.AdminDao;
import com.train.repository.RouteDao;
import com.train.service.ServiceAdmin;

@Service
public class ServiceAdminImpl implements ServiceAdmin {
    @Autowired
    private AdminDao admindao;
    @Autowired
    private RouteDao routedao;
    
	@Override
	public TrainDetails createTrain(TrainDetails traindetails) {
		return admindao.save(traindetails);
	}
	@Override
	public List<TrainDetails> getAllTrains() {
		return admindao.findAll();
	}
	@Override
	public List<TrainDetails> getTrainByName(String train_name) {
		
		return admindao.getTrainByName(train_name);
//		return null;
	}
	@Override
	public Optional<TrainDetails> getTrainById(int id) {
		return admindao.findById(id);
	}
	@Override
	public List<TrainDetails> getTrainByDate(Date date) {
		return admindao.getTrainByDate(date);
	}
//////////////////////////route/////////////////
	@Override
	  public List<RouteDetails> getAllTrainsByRoute(String startpoint, String lastpoint) {
	    return routedao.getTrain(lastpoint, lastpoint);
	  }
	
	  @Override
		public List<RouteDetails> getAllTrainsByRouteDate(String start, String end, Date date) throws Exception {
			List<RouteDetails> list = routedao.getTrainBydateloc(start, end, date);
			List<RouteDetails> list1 = new ArrayList<>();
			for (RouteDetails rd : list) {
				int id = rd.getTd().getTid();
				int d1 = routedao.getTrainBydatelocdis(end, id);
				Time t = routedao.getTimeOfDest(end, id);
				if (d1 - rd.getDistance() > 0) {
					rd.setGap(d1 - rd.getDistance());
					rd.setInterval(t);
					list1.add(rd);
				}
			}
			if (list1.isEmpty())
				throw new Exception("Train is not running between this route");

			else
				return list1;
		}

		@Override
		public RouteDetails createRoute(RouteDetails routedetails) {
			return routedao.save(routedetails);
		}

		@Override
		public List<RouteDetails> getByRoute(int id) {
			return routedao.getRoutebyTrainid(id);
			}
		@Override
		public List<RouteDetails> getAllRoutes() {
			// TODO Auto-generated method stub
			return null;
		}
		
//////////////////////////booking/////////////////	
		
		@Override
		public ReservationForm addBooking(ReservationForm reservationForm) {
			// TODO Auto-generated method stub
			//return admindao.save(reservationForm);
			return null;
		}
}
