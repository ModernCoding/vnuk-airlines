package vn.edu.vnuk.airlines.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.airlines.model.Airport;
import vn.edu.vnuk.airlines.model.City;

public class AirportRowMapper implements RowMapper<Airport>{
	
	public Airport mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		City city = new City();
		Airport airport = new Airport();
		

		city.setId(rs.getLong("city_id"));
		city.setCountryId(rs.getLong("city_country_id"));
		city.setLabel(rs.getString("city_label"));
		
		airport.setId(rs.getLong("id"));
		airport.setCityId(rs.getLong("city_id"));
		airport.setLabel(rs.getString("label"));
		airport.setCode(rs.getString("code"));
		airport.setCity(city);
		
		return airport;
	}
	
	
	public List<Airport> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Airport> airports = new ArrayList<Airport>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		City city = new City();
    		Airport airport = new Airport();
			
			city.setId((Long) row.get("city_id"));
			city.setCountryId((Long) row.get("city_country_id"));
			city.setLabel((String) row.get("city_label"));
			
			airport.setId((Long) row.get("id"));
			airport.setCityId((Long) row.get("city_id"));
			airport.setLabel((String) row.get("label"));
			airport.setCode((String) row.get("code"));
			airport.setCity(city);
			
			airports.add(airport);
			
		}
		
    	
		return airports;

	}

}