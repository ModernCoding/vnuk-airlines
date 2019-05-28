package vn.edu.vnuk.airlines.rowmapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.airlines.model.Flight;
import vn.edu.vnuk.airlines.model.Price;
import vn.edu.vnuk.airlines.model.PriceType;
import vn.edu.vnuk.airlines.model.Class;

public class PriceRowMapper implements RowMapper<Price>{
	
	public Price mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Flight flight = new Flight();
		Class classes = new Class();
		PriceType priceType = new PriceType();
		Price price = new Price();
		
		flight.setId(rs.getLong("flight_id"));
		flight.setRouteId(rs.getLong("flight_route_id"));
		flight.setDayId(rs.getLong("flight_day_id"));
		flight.setPlaneModelId(rs.getLong("flight_plane_model_id"));
		flight.setDepartureTime(rs.getDate("flight_departure_time"));
		flight.setArrivalTime(rs.getDate("flight_arrival_time"));
		
		classes.setId(rs.getLong("class_id"));
		classes.setLabel(rs.getString("class_label"));
		
		priceType.setId(rs.getLong("price_type_id"));
		priceType.setLabel(rs.getString("price_type_label"));
		priceType.setDescription(rs.getString("price_type_description"));
		
		price.setId(rs.getLong("id"));
		price.setFlightId(rs.getLong("flight_id"));
		price.setClassId(rs.getLong("class_id"));
		price.setPriceTypeId(rs.getLong("price_type_id"));
		price.setValue(rs.getLong("value"));
		price.setFlight(flight);
		price.setClasses(classes);
		price.setPriceType(priceType);

		
		return price;
	}
	
	
	public List<Price> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Price> prices = new ArrayList<Price>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Flight flight = new Flight();
    		Class classes = new Class();
    		PriceType priceType = new PriceType();
    		Price price = new Price();
			
    		flight.setId((Long) row.get("flight_id"));
    		flight.setRouteId((Long) row.get("flight_route_id"));
    		flight.setDayId((Long) row.get("flight_day_id"));
    		flight.setPlaneModelId((Long) row.get("flight_plane_model_id"));
    		flight.setDepartureTime((Date) row.get("flight_departure_time"));
    		flight.setArrivalTime((Date) row.get("flight_arrival_time"));
    		
    		classes.setId((Long) row.get("class_id"));
    		classes.setLabel((String) row.get("class_label"));
    		
    		priceType.setId((Long) row.get("price_type_id"));
    		priceType.setLabel((String) row.get("price_type_label"));
    		priceType.setDescription((String) row.get("price_type_description"));
			
    		price.setId((Long) row.get("id"));
    		price.setFlightId((Long) row.get("flight_id"));
    		price.setClassId((Long) row.get("class_id"));
    		price.setPriceTypeId((Long) row.get("price_type_id"));
    		price.setValue((Long) row.get("value"));
			
			prices.add(price);
			
		}
		
    	
		return prices;

	}

}
