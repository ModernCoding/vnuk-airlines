package vn.edu.vnuk.airlines.rowmapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.airlines.model.Booking;
import vn.edu.vnuk.airlines.model.Flight;
import vn.edu.vnuk.airlines.model.PaymentMethod;
import vn.edu.vnuk.airlines.model.Price;
import vn.edu.vnuk.airlines.model.User;

public class BookingRowMapper implements RowMapper<Booking>{
	
	public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		Flight flight = new Flight();
		Price price = new Price();
		PaymentMethod paymentMethod = new PaymentMethod();
		Booking booking = new Booking();
		
		user.setId(rs.getLong("user_id"));
		user.setUserTypeId(rs.getLong("user_user_type_id"));
		user.setLastName(rs.getString("user_last_name"));
		user.setMiddleName(rs.getString("user_middle_name"));
		user.setFirstName(rs.getString("user_first_name"));
		user.setDateOfBirth(rs.getDate("user_date_of_birth"));
		user.setAddress(rs.getString("user_address"));
		user.setEmail(rs.getString("uesr_email"));
		user.setPhone(rs.getInt("user_phone"));
		user.setIdentificationTypeId(rs.getLong("user_identification_type_id"));
		user.setIdentificationNumber(rs.getInt("user_identification_number"));
		
		flight.setId(rs.getLong("flight_id"));
		flight.setRouteId(rs.getLong("flight_route_id"));
		flight.setDayId(rs.getLong("flight_day_id"));
		flight.setPlaneModelId(rs.getLong("flight_plane_model_id"));
		flight.setDepartureTime(rs.getDate("flight_departure_time"));
		flight.setArrivalTime(rs.getDate("flight_arrival_time"));
		
		price.setId(rs.getLong("price_id"));
		price.setFlightId(rs.getLong("price_flight_id"));
		price.setClassId(rs.getLong("price_class_id"));
		price.setPriceTypeId(rs.getLong("price_price_type_id"));
		price.setValue(rs.getLong("price_value"));
		
		paymentMethod.setId(rs.getLong("payment_method_id"));
		paymentMethod.setLabel(rs.getString("payment_method_label"));
		
		booking.setId(rs.getLong("id"));
		booking.setUserId(rs.getLong("user_id"));
		booking.setFlightId(rs.getLong("flight_id"));
		booking.setSeatNumber(rs.getString("seat_number"));
		booking.setPriceId(rs.getLong("price_id"));
		booking.setPaymentMethodId(rs.getLong("payment_method_id"));
		booking.setUser(user);
		booking.setFlight(flight);
		booking.setPrice(price);
		booking.setPaymentMethod(paymentMethod);
		

		return booking;
	}
	
	
	public List<Booking> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<Booking> bookings = new ArrayList<Booking>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		User user = new User();
    		Flight flight = new Flight();
    		Price price = new Price();
    		PaymentMethod paymentMethod = new PaymentMethod();
    		Booking booking = new Booking();
    		
    		user.setId((Long) row.get("user_id"));
    		user.setUserTypeId((Long) row.get("user_user_type_id"));
    		user.setLastName((String) row.get("user_last_name"));
    		user.setMiddleName((String) row.get("user_middle_name"));
    		user.setFirstName((String) row.get("user_first_name"));
    		user.setDateOfBirth((Date) row.get("user_date_of_birth"));
    		user.setAddress((String) row.get("user_address"));
    		user.setEmail((String) row.get("user_email"));
    		user.setPhone((int) row.get("user_phone"));
    		user.setIdentificationTypeId((Long) row.get("user_identifiction_type_id"));
    		user.setIdentificationNumber((int) row.get("user_identification_number"));
    		
    		flight.setId((Long) row.get("flight_id"));
    		flight.setRouteId((Long) row.get("flight_route_id"));
    		flight.setDayId((Long) row.get("flight_day_id"));
    		flight.setPlaneModelId((Long) row.get("flight_plane_model_id"));
    		flight.setDepartureTime((Date) row.get("flight_departure_time"));
    		flight.setArrivalTime((Date) row.get("flight_arrival_time"));
    		
    		price.setId((Long) row.get("price_id"));
    		price.setFlightId((Long) row.get("price_flight_id"));
    		price.setClassId((Long) row.get("price_class_id"));
    		price.setPriceTypeId((Long) row.get("price_price_type_id"));
    		price.setValue((Long) row.get("price_value"));
    		
    		paymentMethod.setId((Long) row.get("payment_method_id"));
    		paymentMethod.setLabel((String) row.get("payment_method_label"));
    		
    		booking.setId((Long) row.get("id"));
    		booking.setUserId((Long) row.get("user_id"));
    		booking.setFlightId((Long) row.get("flight_id"));
    		booking.setSeatNumber((String) row.get("seat_number"));
    		booking.setPriceId((Long) row.get("price_id"));
    		booking.setPaymentMethodId((Long) row.get("payment_method_id"));
    		booking.setUser(user);
    		booking.setFlight(flight);
    		booking.setPrice(price);
    		booking.setPaymentMethod(paymentMethod);
			
			bookings.add(booking);
			
		}
		
    	
		return bookings;

	}

}
