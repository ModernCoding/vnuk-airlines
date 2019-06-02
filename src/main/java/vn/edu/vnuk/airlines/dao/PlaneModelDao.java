package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.PlaneModel;
import vn.edu.vnuk.airlines.rowmapper.PlaneModelRowMapper;


public class PlaneModelDao {

	private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public PlaneModelDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(PlaneModel planeModel) throws SQLException{

        String sqlQuery = "insert into plane_models (plane_manufacturer_id, label) "
                        +	"values (?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new planeModel in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								planeModel.getPlaneManufacturerId(),
            								planeModel.getLabel()
            							}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Customers)
    public List<PlaneModel> read(String planeManufacturerId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.label"
		    			+ "     , t02.id as plane_manufacturer_id"
		    			+ "     , t02.label as plane_manufacturer_label"
						+ "  from plane_models t01, plane_manufacturers t02"
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
        	
        	return new PlaneModelRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }


    //  READ (Single Customer)
    public PlaneModel read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as plane_manufacturer_id"
    			+ "     , t01.label"
				+ "     , t02.label "
				+ "  from plane_models t01, plane_manufacturers t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.plane_manufacturer_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new PlaneModelRowMapper()
        	);
        
    }  

    
    //  UPDATE
    public void update(PlaneModel planeModel) throws SQLException {
        
    	String sqlQuery = "update plane_models set plane_manufacturer_id=? label=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							planeModel.getPlaneManufacturerId(),
							planeModel.getLabel(),
							planeModel.getId()
						}
				);
            
            
            System.out.println("PlaneModel successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from plane_model where id=?";

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
        
    	PlaneModel planModel = this.read(id);
//        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(planModel);
        
    }

}
