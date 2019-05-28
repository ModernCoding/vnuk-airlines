package vn.edu.vnuk.airlines.rowmapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.airlines.model.Day;
import vn.edu.vnuk.airlines.model.Flight;
import vn.edu.vnuk.airlines.model.PlaneModel;
import vn.edu.vnuk.airlines.model.Route;

public class FlightRowMapper implements RowMapper<Flight>{
	
	public Flight mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Route route = new Route();
		Day day = new Day();
		PlaneModel planeModel = new PlaneModel();
		Flight flight = new Flight();
		
		route.setId(rs.getLong("route_id"));
		route.setTakeOffAirportId(rs.getLong("route_take_off_airport_id"));
		route.setLandingAirportId(rs.getLong("route_landing_airport_id"));
		
		day.setId(rs.getLong("day_id"));
		day.setLabel(rs.getString("day_label"));
		
		planeModel.setId(rs.getLong("plane_model_id"));
		planeModel.setPlaneManufacturerId(rs.getLong("plane_model_plane_manufacturer_id"));
		planeModel.setLabel(rs.getString("plane_model_label"));
		
		flight.setId(rs.getLong("id"));
		flight.setRouteId(rs.getLong("route_id"));
		flight.setDayId(rs.getLong("day_id"));
		flight.setPlaneModelId(rs.getLong("plane_model_id"));
		flight.setDepartureTime(rs.getDate("depature_time"));
		flight.setArrivalTime(rs.getDate("arrival_time"));
		flight.setRoute(route);
		flight.setDay(day);
		flight.setPlaneModel(planeModel);
		
		return flight;
	}
	
	
	public List<Flight> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Flight> flights = new ArrayList<Flight>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Route route = new Route();
    		Day day = new Day();
    		PlaneModel planeModel = new PlaneModel();
    		Flight flight = new Flight();
    		
    		route.setId((Long) row.get("route_id"));
    		route.setTakeOffAirportId((Long) row.get("route_take_off_airport_id"));
    		route.setLandingAirportId((Long) row.get("route_landing_airport_id"));
    		
    		day.setId((Long) row.get("day_id"));
    		day.setLabel((String) row.get("day_label"));
    		
    		planeModel.setId((Long) row.get("plane_model_id"));
    		planeModel.setPlaneManufacturerId((Long) row.get("plane_model_plane_manufacturer_id"));
    		planeModel.setLabel((String) row.get("plane_model_label"));
    		
    		flight.setId((Long) row.get("id"));
    		flight.setRouteId((Long) row.get("route_id"));
    		flight.setDayId((Long) row.get("day_id"));
    		flight.setPlaneModelId((Long) row.get("plane_model_id"));
    		flight.setDepartureTime((Date) row.get("departure_time"));
    		flight.setArrivalTime((Date) row.get("arrival_time"));
    		flight.setRoute(route);
    		flight.setDay(day);
    		flight.setPlaneModel(planeModel);
			
			
    		flights.add(flight);
			
		}
		
    	
		return flights;

	}

}