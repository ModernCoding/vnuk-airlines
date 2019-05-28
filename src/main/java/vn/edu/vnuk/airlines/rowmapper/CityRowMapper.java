package vn.edu.vnuk.airlines.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.airlines.model.City;
import vn.edu.vnuk.airlines.model.Country;


public class CityRowMapper  implements RowMapper<City>{
	
	public City mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Country country = new Country();
		City city = new City();
		
		country.setId(rs.getLong("country_id"));
		country.setLabel(rs.getString("country_label"));
		
		city.setId(rs.getLong("id"));
		city.setCountryId(rs.getLong("country_id"));
		city.setLabel(rs.getString("label"));
		city.setCountry(country);
		
		return city;
	}
	
	
	public List<City> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<City> cities = new ArrayList<City>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Country country = new Country();
			City city = new City();
			
			country.setId((Long) row.get("country_id"));
			country.setLabel((String) row.get("country_label"));
			
			city.setId((Long) row.get("id"));
			city.setCountryId((Long) row.get("country_id"));
			city.setLabel((String) row.get("label"));
			city.setCountry(country);
			
			cities.add(city);
			
		}
		
    	
		return cities;

	}

}
