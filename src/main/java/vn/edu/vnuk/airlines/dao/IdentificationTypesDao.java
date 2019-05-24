package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import vn.edu.vnuk.airlines.model.IdentificationType;

public class IdentificationTypesDao {

private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public IdentificationTypesDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(IdentificationType identificationTypes) throws SQLException{

        String sqlQuery = "insert into identification_types (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new collar in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {identificationTypes.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Body Parts)
    public List<IdentificationType> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM identification_types",
        			new BeanPropertyRowMapper<IdentificationType>(IdentificationType.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single BodyPart)
    public IdentificationType read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM identification_types where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<IdentificationType>(IdentificationType.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(IdentificationType bodyPart) throws SQLException {
    	
        String sqlQuery = "update identification_types set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							bodyPart.getLabel(),
							bodyPart.getId()
						}
				);
            
            
            System.out.println("IdentificationTypes successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from identification_types where id=?";

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
