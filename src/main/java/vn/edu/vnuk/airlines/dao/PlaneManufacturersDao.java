package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.PlaneManufacturer;

public class PlaneManufacturersDao {

	 private final JdbcTemplate jdbcTemplate;
	    
	    @Autowired
	    public PlaneManufacturersDao(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
	    }
		


	    //  CREATE
	    public void create(PlaneManufacturer planManufacturers) throws SQLException{

	        String sqlQuery = "insert into plane_manufacturers (label) values (?)";

	        try {
	            System.out.println(
	            		String.format(
	            				"%s new collar in DB!",
	            				
	            				this.jdbcTemplate.update(
	            						sqlQuery,
	            						new Object[] {planManufacturers.getLabel()}
	        						)
	        				)
	        		);

	            
	        } catch (Exception e) {
	        	
	            e.printStackTrace();
	        
	        }

	    }
	    
	    
	    //  READ (List of Body Parts)
	    public List<PlaneManufacturer> read() throws SQLException {

	        try {
	            
	        	return this.jdbcTemplate.query(
	        			"SELECT * FROM plane_manufacturers",
	        			new BeanPropertyRowMapper<PlaneManufacturer>(PlaneManufacturer.class)
	    			);

	        	
	        } catch (Exception e) {
	        	
	            e.printStackTrace();
	        
	        }
	        
	        
			return null;


	    }


	    //  READ (Single BodyPart)
	    public PlaneManufacturer read(Long id) throws SQLException{
	    	
	    	return this.jdbcTemplate.queryForObject(
	    			"SELECT * FROM plane_manufacturers where id = ?",
	        		new Object[] {id},
	        		new BeanPropertyRowMapper<PlaneManufacturer>(PlaneManufacturer.class)
	        	);
	        
	    }  

	    
	    //  UPDATE
	    public void update(PlaneManufacturer bodyPart) throws SQLException {
	    	
	        String sqlQuery = "update plane_manufacturers set label=? where id=?";
	        
	        try {
	        	this.jdbcTemplate.update(
						sqlQuery,
						
						new Object[] {
								bodyPart.getLabel(),
								bodyPart.getId()
							}
					);
	            
	            
	            System.out.println("PlaneManufacturers successfully modified.");
	        } 

	        catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	    }
	    
	    
	    //  DELETE
	    public void delete(Long id) throws SQLException {
	    	
	        String sqlQuery = "delete from plane_manufacturers where id=?";

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
	    
	}