package vn.edu.vnuk.airlines.dao;


import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.City;
import vn.edu.vnuk.airlines.rowmapper.CityRowMapper;

public class CityDao {
	
	private final JdbcTemplate jdbcTemplate;

	@Autowired
    public CityDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(City city) throws SQLException{

        String sqlQuery = "insert into cities (country_id, label) "
                        +	"values (?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new city in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								city.getCountryId(),
            								city.getLabel()
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
	public List read(String countryId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.label"
		    			+ "     , t02.id as country_id"
		    			+ "     , t02.label as country_label"
						+ "  from cities t01, countries t02"
						+ " where t02.id = t01.country_id"
				;
    	
    	if (countryId != null) {
    		sqlQuery += String.format("   and t02.id = %s", countryId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new CityRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single Customer)
    public City read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as country_id"
    			+ "     , t01.label"
				+ "     , t02.label "
				+ "  from cities t01, countries t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.country_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new CityRowMapper()
        	);
        
    } 

    
    //  UPDATE
    public void update(City city) throws SQLException {
        
    	String sqlQuery = "update cities set country_id=? label=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							city.getCountryId(),
							city.getLabel(),
							city.getId()
						}
				);
            
            
            System.out.println("City successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from city where id=?";

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
        
    	City city = this.read(id);
//        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(city);
        
    }

}
