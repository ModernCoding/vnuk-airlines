package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.Country;
import vn.edu.vnuk.airlines.model.Service;

public class ServicesDao {

private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public ServicesDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Service services) throws SQLException{

        String sqlQuery = "insert into services (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new collar in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {services.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Body Parts)
    public List<Service> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM services",
        			new BeanPropertyRowMapper<Service>(Service.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single BodyPart)
    public Service read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM services where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Service>(Service.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Service bodyPart) throws SQLException {
    	
        String sqlQuery = "update services set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							bodyPart.getLabel(),
							bodyPart.getId()
						}
				);
            
            
            System.out.println("services successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from services where id=?";

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
