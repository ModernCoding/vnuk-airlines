package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.PaymentMethod;

public class PaymentMethodsDao {

	private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public PaymentMethodsDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }
	


    //  CREATE
    public void create(PaymentMethod paymentMethods) throws SQLException{

        String sqlQuery = "insert into payment_methods (label) values (?)";

        try {
            System.out.println(
            		String.format(
            				"%s new collar in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {paymentMethods.getLabel()}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Body Parts)
    public List<PaymentMethod> read() throws SQLException {

        try {
            
        	return this.jdbcTemplate.query(
        			"SELECT * FROM payment_methods",
        			new BeanPropertyRowMapper<PaymentMethod>(PaymentMethod.class)
    			);

        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;


    }


    //  READ (Single BodyPart)
    public PaymentMethod read(Long id) throws SQLException{
    	
    	return this.jdbcTemplate.queryForObject(
    			"SELECT * FROM payment_methods where id = ?",
        		new Object[] {id},
        		new BeanPropertyRowMapper<PaymentMethod>(PaymentMethod.class)
        	);
        
    }  

    
    //  UPDATE
    public void update(PaymentMethod bodyPart) throws SQLException {
    	
        String sqlQuery = "update payment_methods set label=? where id=?";
        
        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							bodyPart.getLabel(),
							bodyPart.getId()
						}
				);
            
            
            System.out.println("PaymentMethods successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
    	
        String sqlQuery = "delete from payment_methods where id=?";

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
