package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.Flight;
import vn.edu.vnuk.airlines.rowmapper.FlightRowMapper;

public class FlightDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
    public FlightDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(Flight flight) throws SQLException{

        String sqlQuery = "insert into flights (route_id, day_id, plane_model_id, departure_time, arrival_time) "
                        +	"values (?, ?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new city in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								flight.getRouteId(),
            								flight.getDayId(),
            								flight.getPlaneModelId(),
            								flight.getDepartureTime(),
            								flight.getArrivalTime()
            						}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Customers)
    public List<Flight> read(String routeId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.departure_time"
		    			+ "		, t01.arrival_time"
		    			+ "     , t02.id as route_id"
		    			+ "     , t02.take_off_airport_id as route_take_off_airport_id"
		    			+ "		, t02.landing_airport_id as route_landing_airport_id"
						+ "  from flights t01, routes t02"
						+ " where t02.id = t01.route_id"
				;
    	
    	if (routeId != null) {
    		sqlQuery += String.format("   and t02.id = %s", routeId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new FlightRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }
    
//  READ (List of Customers)
    @SuppressWarnings("rawtypes")
	public List read1(String dayId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.departure_time"
		    			+ "		, t01.arrival_time"
		    			+ "     , t02.id as day_id"
		    			+ "     , t02.label as day_label"
						+ "  from flights t01, days t02"
						+ " where t02.id = t01.day_id"
				;
    	
    	if (dayId != null) {
    		sqlQuery += String.format("   and t02.id = %s", dayId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new FlightRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }
    
//  READ (List of Customers)
    @SuppressWarnings("rawtypes")
	public List read2(String planeModelId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.departure_time"
		    			+ "		, t01.arrival_time"
		    			+ "     , t02.id as plane_model_id"
		    			+ "     , t02.plane_manufacturer_id as plane_model_plane_manufacturer_id"
		    			+ "		, t02.label as plane_model_label"
						+ "  from flights t01, plane_models t02"
						+ " where t02.id = t01.plane_model_id"
				;
    	
    	if (planeModelId != null) {
    		sqlQuery += String.format("   and t02.id = %s", planeModelId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new FlightRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single Customer)
    public Flight read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as route_id"
    			+ "     , t01.departure_time"
    			+ "     , t01.arrival_time"
				+ "     , t02.take_off_airport_id "
    			+ "		, t02.landing_airport_id"
				+ "  from flights t01, routes t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.route_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new FlightRowMapper()
        	);
        
    } 
    
//  READ (Single Customer)
    public Flight read1(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as day_id"
    			+ "     , t01.departure_time"
    			+ "     , t01.arrival_time"
				+ "     , t02.label "
				+ "  from flights t01, days t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.day_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new FlightRowMapper()
        	);
        
    } 
    
    //  READ (Single Customer)
    public Flight read2(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as plane_model_id"
    			+ "     , t01.departure_time"
    			+ "     , t01.arrival_time"
				+ "     , t02.plane_manufacturer_id "
    			+ "		, t02.label"
				+ "  from flights t01, plane_models t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.plane_model_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new FlightRowMapper()
        	);
        
    } 

    
    //  UPDATE
    public void update(Flight flight) throws SQLException {
        
    	String sqlQuery = "update flights set route_id=? day_id=? plane_model_id=? departure_time=? arrival_time=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							flight.getRouteId(),
							flight.getDayId(),
							flight.getPlaneModelId(),
							flight.getDepartureTime(),
							flight.getArrivalTime(),
							flight.getId()
						}
				);
            
            
            System.out.println("Flight successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from flights where id=?";

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
        
    	Flight flight = this.read(id);
//        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(flight);
        
    }

}
