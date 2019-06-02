package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.Booking;
import vn.edu.vnuk.airlines.rowmapper.BookingRowMapper;

public class BookingDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
    public BookingDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(Booking booking) throws SQLException{

        String sqlQuery = "insert into bookings (user_id, flight_id, seat_number, price_id, payment_method_id) "
                        +	"values (?, ?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new city in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								
            								booking.getUserId(),
            								booking.getFlightId(),
            								booking.getSeatNumber(),
            								booking.getPriceId(),
            								booking.getPaymentMethodId()
            						}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Customers)
    public List<Booking> read(String userId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.seat_number"
		    			+ "     , t02.id as user_id"
		    			+ "     , t02.user_type_id as user_user_type_id"
		    			+ "		, t02.last_name as user_last_name"
		    			+ "		, t02.middle_name as user_middle_name"
		    			+ "		, t02.first_name as user_first_name"
		    			+ "		, t02.date_of_birth as user_date_of_birth"
		    			+ "		, t02.address as user_address"
		    			+ "		, t02.email as user_email"
		    			+ "		, t02.phone as user_phone"
		    			+ "		, t02.identification_number as user_identification_number"
						+ "  from bookings t01, users t02"
						+ " where t02.id = t01.user_id"
				;
    	
    	if (userId != null) {
    		sqlQuery += String.format("   and t02.id = %s", userId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new BookingRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }
    
//  READ (List of Customers)
    @SuppressWarnings("rawtypes")
	public List read1(String flightId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.seat_number"
		    			+ "     , t02.id as flight_id"
		    			+ "     , t02.route_id as flight_route_id"
		    			+ "		, t02.day_id as flight_day_id"
		    			+ "		, t02.plane_model_id as flight_plane_model_id"
		    			+ "		, t02.departure_time as flight_departure_time"
		    			+ "		, t02.arrival_time as flight_arrival_time"
						+ "  from bookings t01, flights t02"
						+ " where t02.id = t01.flight_id"
				;
    	
    	if (flightId != null) {
    		sqlQuery += String.format("   and t02.id = %s", flightId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new BookingRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }
    
//  READ (List of Customers)
    @SuppressWarnings("rawtypes")
	public List read2(String priceId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.seat_number"
		    			+ "     , t02.id as price_id"
		    			+ "     , t02.flight_id as price_flight_id"
		    			+ "		, t02.class_id as price_class_id"
		    			+ "		, t02.price_type_id as price_price_type_id"
		    			+ "		, t02.value as price_value"
						+ "  from bookings t01, prices t02"
						+ " where t02.id = t01.price_id"
				;
    	
    	if (priceId != null) {
    		sqlQuery += String.format("   and t02.id = %s", priceId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new BookingRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }
    
//  READ (List of Customers)
    @SuppressWarnings("rawtypes")
	public List read3(String paymentMethodId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.seat_number"
		    			+ "     , t02.id as payment_method_id"
		    			+ "     , t02.label as payment_method_label"
						+ "  from bookings t01, payment_methods t02"
						+ " where t02.id = t01.payment_method_id"
				;
    	
    	if (paymentMethodId != null) {
    		sqlQuery += String.format("   and t02.id = %s", paymentMethodId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new BookingRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }
    
    //  READ (Single Customer)
    public Booking read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as user_id"
    			+ "     , t01.seat_number"
				+ "     , t02.user_type_id "
				+ "     , t02.last_name"
    			+ "		, t02.middle_name"
    			+ "		, t02.first_name"
    			+ "		, t02.date_of_birth"
    			+ "		, t02.address"
    			+ "		, t02.email"
    			+ "		, t02.phone"
    			+ "		, t02.identification_type_id"
    			+ "		, t02.identification_number"
				+ "  from bookings t01, users t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.user_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new BookingRowMapper()
        	);
        
    } 
    
    //  READ (Single Customer)
    public Booking read1(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as flight_id"
    			+ "     , t01.seat_number"
				+ "     , t02.route_id "
				+ "     , t02.day_id"
    			+ "		, t02.plane_model_id"
    			+ "		, t02.depature_time"
    			+ "		, t02.arrival_time"
				+ "  from bookings t01, flights t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.flight_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new BookingRowMapper()
        	);
        
    } 
    
    //  READ (Single Customer)
    public Booking read2(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as price_id"
    			+ "     , t01.seat_number"
				+ "     , t02.flight_id "
				+ "     , t02.class_id"
    			+ "		, t02.price_type_id"
    			+ "		, t02.value"
				+ "  from bookings t01, prices t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.price_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new BookingRowMapper()
        	);
        
    } 
    
    public Booking read3(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as payment_method_id"
    			+ "     , t01.seat_number"
				+ "     , t02.label"
				+ "  from bookings t01, payment_methods t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.payment_method_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new BookingRowMapper()
        	);
        
    } 

    
    //  UPDATE
    public void update(Booking booking) throws SQLException {
        
    	String sqlQuery = "update bookings set user_id=? flight_id=? seat_number=? price_id=? payment_method_id=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							booking.getUserId(),
							booking.getFlightId(),
							booking.getSeatNumber(),
							booking.getPriceId(),
							booking.getPaymentMethodId(),
							booking.getId()
						}
				);
            
            
            System.out.println("booking successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from bookings where id=?";

        try {

            System.out.println(
            		String.format(
            				"%s record successfully removed from DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {id}
        						)
        				)
        		);

        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public void complete(Long id) throws SQLException{
        
    	Booking booking = this.read(id);
//        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(booking);
        
    }

}
