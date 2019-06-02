package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.BookingsService;
import vn.edu.vnuk.airlines.rowmapper.BookingsServiceRowMapper;

public class BookingsServiceDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
    public BookingsServiceDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(BookingsService bookingsService) throws SQLException{

        String sqlQuery = "insert into bookings_services (booking_id, sevice_id) "
                        +	"values (?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new bookings_service in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								bookingsService.getBookingId(),
            								bookingsService.getServiceId()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Customers)
    public List<BookingsService> read(String bookingId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t02.id as booking_id"
		    			+ "     , t02.user_id as booking_user_id"
		    			+ "     , t02.flight_id as booking_flight_id"
		    			+ "		, t02.seat_number as booking_seat_number"
		    			+ "     , t02.price_id_id as booking_price_id"
		    			+ "     , t02.payment_method_id as booking_payment_method_id"
						+ "  from bookings_services t01, bookings t02"
						+ " where t02.id = t01.booking_id"
				;
    	
    	if (bookingId != null) {
    		sqlQuery += String.format("   and t02.id = %s", bookingId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new BookingsServiceRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }
    
//  READ (List of Customers)
    @SuppressWarnings("rawtypes")
	public List read1(String serviceId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t02.id as service_id"
		    			+ "     , t02.label as service_label"
						+ "  from bookings_services t01, services t02"
						+ " where t02.id = t01.service_id"
				;
    	
    	if (serviceId != null) {
    		sqlQuery += String.format("   and t02.id = %s", serviceId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new BookingsServiceRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single Customer)
    public BookingsService read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as booking_id"
				+ "     , t02.user_id "
    			+ "		, t02.flight_id"
				+ "		, t02.seat_number"
    			+ "		, t02.price_id"
				+ "		, t02.payment_method_id"
				+ "  from bookings_services t01, bookings t02"
				+ "  where t01.id = ?"
				+ "  and t02.id = t01.booking_id"
				+ "  order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new BookingsServiceRowMapper()
        	);
        
    } 
    
//  READ (Single Customer)
    public BookingsService read1(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as service_id"
				+ "     , t02.label "
				+ "  from bookings_services t01, services t02"
				+ "  where t01.id = ?"
				+ "  and t02.id = t01.service_id"
				+ "  order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new BookingsServiceRowMapper()
        	);
        
    } 

    
    //  UPDATE
    public void update(BookingsService bookingsService) throws SQLException {
        
    	String sqlQuery = "update bookings_services set booking_id=? service_id=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							bookingsService.getBookingId(),
							bookingsService.getServiceId(),
							bookingsService.getId()
						}
				);
            
            
            System.out.println("BookingsServices successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from bookings_services where id=?";

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
        
    	BookingsService bookingsService = this.read(id);
//        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(bookingsService);
        
    }

}
