package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.Country;

public class CountriesDao {

	private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public CountriesDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(Country countries) throws SQLException{

        String sqlQuery = "insert into countries (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new collar in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {countries.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Body Parts)
    public List<Country> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM countries",
        			new BeanPropertyRowMapper<Country>(Country.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single BodyPart)
    public Country read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM countries where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<Country>(Country.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(Country bodyPart) throws SQLException {
    	
        String sqlQuery = "update countries set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							bodyPart.getLabel(),
							bodyPart.getId()
						}
				);
            
            
            System.out.println("Countries successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from countries where id=?";

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
