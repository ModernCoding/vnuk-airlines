package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.PlaneModels;


public class PlaneModelsDao {

private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public PlaneModelsDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(PlaneModels planemodels) throws SQLException{

        String sqlQuery = "insert into planemodels (plane_manufacturer_id, label) "
                        +	"values (?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new subcategory in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								planemodels.getPlaneManufacturerId(),
            								planemodels.getLabel()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Customers)
    public List<PlaneModels> read(int plane_manufacturer_id) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.label"
		    			+ "     , t02.id as plane_manufacturer_id"
						+ "     , t02.label as plane_manufacturer_label"
						+ "  from planemodels t01, plane_manufacturers t02"
						+ " where t02.id = t01.plane_manufacturer_id"
				;
    	
    	if (planeManufacturerId != null) {
    		sqlQuery += String.format("   and t02.id = %s", planeManufacturerId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new CustomerRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single Customer)
    public Subcategory read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t01.label"
    			+ "     , t02.id as title_id"
				+ "     , t02.label as title_label"
				+ "     , t01.address"
				+ "     , t01.phone"
				+ "     , t01.email"
				+ "  from customers t01, titles t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.title_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new SubcategoryRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(Customer customer) throws SQLException {
        
    	String sqlQuery = "update customers set title_id=?, label=?, address=?, phone=?, email=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							customer.getTitleId(),
							customer.getLabel(),
							customer.getAddress(),
							customer.getPhone(),
							customer.getEmail()
						}
				);
            
            
            System.out.println("Customer successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from customers where id=?";

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
