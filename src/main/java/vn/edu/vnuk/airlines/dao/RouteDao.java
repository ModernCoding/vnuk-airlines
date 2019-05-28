package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.Route;
import vn.edu.vnuk.airlines.rowmapper.RouteRowMapper;

public class RouteDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
    public RouteDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(Route route) throws SQLException{

        String sqlQuery = "insert into routes (take_off_airport_id, landing_airport_id) "
                        +	"values (?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new route in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								route.getTakeOffAirportId(),
            								route.getLandingAirportId()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Customers)
    @SuppressWarnings("rawtypes")
	public List read(String airportId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.take_off_airport_id"
		    			+ "		, t01.landing_airport_id"
		    			+ "     , t02.id as airport_id"
		    			+ "     , t02.city_id as airport_city_id"
		    			+ "		, t02.label as airport_label"
		    			+ "		, t02.code as airport_label"
						+ "  from routes t01, airports t02"
						+ " where t02.id = t01.airport_id"
				;
    	
    	if (airportId != null) {
    		sqlQuery += String.format("   and t02.id = %s", airportId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new RouteRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single Customer)
    public Route read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as airport_id"
    			+ "     , t01.take_off_airport_id"
    			+ "     , t01.landing_airport_id"
    			+ "		, t02.city_id"
				+ "     , t02.label "
    			+ "		, t02.code"
				+ "	from routes t01, airports t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.airport_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new RouteRowMapper()
        	);
        
    } 

    
    //  UPDATE
    public void update(Route route) throws SQLException {
        
    	String sqlQuery = "update routes set take_off_airport_id=? landing_airport_id=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							route.getTakeOffAirportId(),
							route.getLandingAirportId(),
							route.getId()
						}
				);
            
            
            System.out.println("Route successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from route where id=?";

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
        
    	Route route = this.read(id);
//        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(route);
        
    }

}
