package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.Price;
import vn.edu.vnuk.airlines.rowmapper.PriceRowMapper;

public class PriceDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
    public PriceDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(Price price) throws SQLException{

        String sqlQuery = "insert into prices (flight_id, class_id, price_type_id, value) "
                        +	"values (?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new city in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								
            								price.getFlightId(),
            								price.getClassId(),
            								price.getPriceTypeId(),
            								price.getValue()
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
	public List read(String flightId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
    			+ "     , t01.value"
    			+ "     , t02.id as flight_id"
    			+ "     , t02.route_id as flight_route_id"
    			+ "		, t02.day_id as flight_day_id"
    			+ "		, t02.plane_model_id as flight_plane_model_id"
    			+ "		, t02.departure_time as flight_departure_time"
    			+ "		, t02.arrival_time as flight_arrival_time"
				+ "  from prices t01, flights t02"
				+ " where t02.id = t01.flight_id"
		;
    	
    	if (flightId != null) {
    		sqlQuery += String.format("   and t02.id = %s", flightId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new PriceRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }
    
    //  READ (List of Customers)
    @SuppressWarnings("rawtypes")
	public List read1(String classId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
    			+ "     , t01.value"
    			+ "     , t02.id as class_id"
    			+ "     , t02.label as class_label"
				+ "  from prices t01, classes t02"
				+ " where t02.id = t01.class_id"
		;
    	
    	if (classId != null) {
    		sqlQuery += String.format("   and t02.id = %s", classId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new PriceRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }
    
//  READ (List of Customers)
    @SuppressWarnings("rawtypes")
	public List read2(String priceTypeId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
    			+ "     , t01.value"
    			+ "     , t02.id as price_type_id"
    			+ "     , t02.label as price_label"
    			+ "		, t02.description as price_description"
				+ "  from prices t01, price_types t02"
				+ " where t02.id = t01.price_type_id"
		;
    	
    	if (priceTypeId != null) {
    		sqlQuery += String.format("   and t02.id = %s", priceTypeId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new PriceRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }

    public Price read(Long id) throws SQLException{
    	
    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as flight_id"
    			+ "     , t01.value"
    			+ "     , t02.route_id "
    			+ "     , t02.day_id"
    			+ "		, t02.plane_model_id"
    			+ "		, t02.depature_time"
    			+ "		, t02.arrival_time"
    			+ "  from prices t01, flights t02"
    			+ " where t01.id = ?"
    			+ "   and t02.id = t01.flight_id"
    			+ " order by t02.id asc, t01.id asc"
    			+ ";"
    			;
    	
    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
    			new Object[] {id},
    			new PriceRowMapper()
    			);
    	
    }

    //  READ (Single Customer)
    public Price read1(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as class_id"
    			+ "     , t01.value"
				+ "     , t02.label "
				+ "  from prices t01, classes t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.class_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new PriceRowMapper()
        	);
        
    } 
    
//  READ (Single Customer)
    public Price read3(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as price_type_id"
    			+ "     , t01.value"
				+ "     , t02.label "
    			+ "		, t02.description"
				+ "  from prices t01, price_types t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.price_type_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new PriceRowMapper()
        	);
        
    } 
    

    
    //  UPDATE
    public void update(Price price) throws SQLException {
        
    	String sqlQuery = "update prices set flight_id=? class_id=? price_type_id=? value=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							price.getFlightId(),
							price.getClassId(),
							price.getPriceTypeId(),
							price.getValue(),
							price.getId()
						}
				);
            
            
            System.out.println("Price successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from prices where id=?";

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
        
    	Price price = this.read(id);
//        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(price);
        
    }

}
