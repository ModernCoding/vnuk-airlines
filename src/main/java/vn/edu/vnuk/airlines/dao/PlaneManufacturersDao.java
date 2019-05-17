package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.PlaneManufacturers;

public class PlaneManufacturersDao {

	 private final JdbcTemplate jdbcTemplate;
	    
	    @Autowired
	    public PlaneManufacturersDao(JdbcTemplate jdbcTemplate) {
		  this.jdbcTemplate = jdbcTemplate;
	    }
		


	    //  CREATE
	    public void create(PlaneManufacturers planManufacturers) throws SQLException{

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
	    public List<PlaneManufacturers> read() throws SQLException {

	        try {
	            
	        	return this.jdbcTemplate.query(
	        			"SELECT * FROM plane_manufacturers",
	        			new BeanPropertyRowMapper<PlaneManufacturers>(PlaneManufacturers.class)
	    			);

	        	
	        } catch (Exception e) {
	        	
	            e.printStackTrace();
	        
	        }
	        
	        
			return null;


	    }


	    //  READ (Single BodyPart)
	    public PlaneManufacturers read(Long id) throws SQLException{
	    	
	    	return this.jdbcTemplate.queryForObject(
	    			"SELECT * FROM plane_manufacturers where id = ?",
	        		new Object[] {id},
	        		new BeanPropertyRowMapper<PlaneManufacturers>(PlaneManufacturers.class)
	        	);
	        
	    }  

	    
	    //  UPDATE
	    public void update(PlaneManufacturers bodyPart) throws SQLException {
	    	
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