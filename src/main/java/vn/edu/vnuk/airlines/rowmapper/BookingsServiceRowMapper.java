package vn.edu.vnuk.airlines.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.airlines.model.Booking;
import vn.edu.vnuk.airlines.model.BookingsService;
import vn.edu.vnuk.airlines.model.Service;

public class BookingsServiceRowMapper implements RowMapper<BookingsService>{
	
	public BookingsService mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Booking booking = new Booking();
		Service service = new Service();
		BookingsService bookingsService = new BookingsService();
		
		booking.setId(rs.getLong("booking_id"));
		booking.setUserId(rs.getLong("booking_user_id"));
		booking.setFlightId(rs.getLong("booking_flight_id"));
		booking.setSeatNumber(rs.getString("booking_seat_number"));
		booking.setPriceId(rs.getLong("booking_price_id"));
		booking.setPaymentMethodId(rs.getLong("booking_payment_method_id"));
		
		service.setId(rs.getLong("service_id"));
		service.setLabel(rs.getString("service_label"));
		
		bookingsService.setId(rs.getLong("id"));
		bookingsService.setBookingId(rs.getLong("booking_id"));
		bookingsService.setServiceId(rs.getLong("service_id"));
		bookingsService.setBooking(booking);
		bookingsService.setService(service);
		
		return bookingsService;
	}
	
	
	public List<BookingsService> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<BookingsService> bookingsServices = new ArrayList<BookingsService>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		Booking booking = new Booking();
    		Service service = new Service();
    		BookingsService bookingsService = new BookingsService();
			
    		booking.setId((Long) row.get("booking_id"));
    		booking.setUserId((Long) row.get("booking_user_id"));
    		booking.setFlightId((Long) row.get("booking_flight_id"));
    		booking.setSeatNumber((String) row.get("booking_seat_number"));
    		booking.setPriceId((Long) row.get("booking_price_id"));
    		booking.setPaymentMethodId((Long) row.get("booking_payment_method_id"));
    		
    		service.setId((Long) row.get("service_id"));
    		service.setLabel((String) row.get("service_label"));
    		
    		bookingsService.setId((Long) row.get("id"));
    		bookingsService.setBookingId((Long) row.get("booking_id"));
    		bookingsService.setServiceId((Long) row.get("service_id"));
    		bookingsService.setBooking(booking);
    		bookingsService.setService(service);
			
			bookingsServices.add(bookingsService);
			
		}
		
    	
		return bookingsServices;

	}

}
