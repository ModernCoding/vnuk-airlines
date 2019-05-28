package vn.edu.vnuk.airlines.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.airlines.model.Airport;
import vn.edu.vnuk.airlines.model.Route;

public class RouteRowMapper implements RowMapper<Route>{
	
	public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Airport airport = new Airport();
		Route route = new Route();
		
		airport.setId(rs.getLong("airport_id"));
		airport.setCityId(rs.getLong("airport_city_id"));
		airport.setLabel(rs.getString("airport_label"));
		airport.setCode(rs.getString("airport_code"));
		
		route.setId(rs.getLong("id"));
		route.setTakeOffAirportId(rs.getLong("take_off_airport_id"));
		route.setLandingAirportId(rs.getLong("landing_airport_id"));
		route.setAirport(airport);
		
		return route;
	}
	
	
	public List<Route> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Route> routes = new ArrayList<Route>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Airport airport = new Airport();
			Route route = new Route();
			
			airport.setId((Long) row.get("airport_id"));
			airport.setCityId((Long) row.get("airport_city_id"));
			airport.setLabel((String) row.get("airport_label"));
			airport.setCode((String) row.get("airport_code"));
			
			route.setId((Long) row.get("id"));
			route.setTakeOffAirportId((Long) row.get("take_off_airport_id"));
			route.setLandingAirportId((Long) row.get("landing_airport_id"));
			route.setAirport(airport);
			
			routes.add(route);
			
		}
		
    	
		return routes;

	}

}
