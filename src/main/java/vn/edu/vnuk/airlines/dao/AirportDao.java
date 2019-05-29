package vn.edu.vnuk.airlines.dao;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.Airport;
import vn.edu.vnuk.airlines.rowmapper.AirportRowMapper;

public class AirportDao {

	private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public AirportDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(Airport airport) throws SQLException{

        String sqlQuery = "insert into airports (city_id, label, code) "
                        +	"values (?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new planeModel in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								airport.getCityId(),
            								airport.getLabel(),
            								airport.getCode()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Customers)
    public List<Airport> read(String cityId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.label"
		    			+ "		, t01.code"
		    			+ "     , t02.id as city_id"
		    			+ "		, t02.country_id as city_country_id"
		    			+ "     , t02.label as city_label"
						+ "  from airports t01, cities t02"
						+ " where t02.id = t01.city_id"
				;
    	
    	if (cityId != null) {
    		sqlQuery += String.format("   and t02.id = %s", cityId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new AirportRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single Customer)
    public Airport read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as city_id"
    			+ "     , t01.label"
				+ "     , t01.code "
    			+ "		, t02.country_id"
				+ "		, t02.label"
				+ "  from airports t01, cities t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.city_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new AirportRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(Airport airport) throws SQLException {
        
    	String sqlQuery = "update airports set city_id=? label=? code=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							airport.getCityId(),
							airport.getLabel(),
							airport.getCode(),
							airport.getId()
						}
				);
            
            
            System.out.println("Airport successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from airports where id=?";

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
        
    	Airport airport = this.read(id);
//        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(airport);
        
    }
    
}