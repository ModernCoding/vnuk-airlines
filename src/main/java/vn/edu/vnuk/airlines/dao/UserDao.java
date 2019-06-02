package vn.edu.vnuk.airlines.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vn.edu.vnuk.airlines.model.User;
import vn.edu.vnuk.airlines.rowmapper.UserRowMapper;

public class UserDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;
    }


    //  CREATE
    public void create(User user) throws SQLException{

        String sqlQuery = "insert into flights (user_type_id, last_name, middle_name, first_name, date_of_birth,"
        				+ " address, email, phone, identification_type_id, identification_number) "
                        +	"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            System.out.println(
            		String.format(
            				"%s new user in DB!",
            				
            				this.jdbcTemplate.update(
            						sqlQuery,
            						new Object[] {
            								user.getUserTypeId(),
            								user.getLastName(),
            								user.getMiddleName(),
            								user.getFirstName(),
            								user.getDateOfBirth(),
            								user.getAddress(),
            								user.getEmail(),
            								user.getPhone(),
            								user.getIdentificationTypeId(),
            								user.getIdentificationNumber()

            						}
        						)
        				)
        		);

            
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }

    }
    
    
    //  READ (List of Customers)
    public List<User> read(String userTypeId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.last_name"
		    			+ "		, t01.middle_name"
		    			+ "		, t01.first_name"
		    			+ "		, t01.date_of_birth"
		    			+ "		, t01.address"
		    			+ "		, t01.email"
		    			+ "		, t01.phone"
		    			+ "		, t01.identification_number"
		    			+ "     , t02.id as user_type_id"
		    			+ "     , t02.label as user_type_label"
						+ "  from users t01, user_types t02"
						+ " where t02.id = t01.user_type_id"
				;
    	
    	if (userTypeId != null) {
    		sqlQuery += String.format("   and t02.id = %s", userTypeId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new UserRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }
    
//  READ (List of Customers)
    @SuppressWarnings("rawtypes")
	public List read1(String identificationTypeId) throws SQLException {
    	
    	String sqlQuery = "select t01.id"
		    			+ "     , t01.last_name"
		    			+ "		, t01.middle_name"
		    			+ "		, t01.first_name"
		    			+ "		, t01.date_of_birth"
		    			+ "		, t01.address"
		    			+ "		, t01.email"
		    			+ "		, t01.phone"
		    			+ "		, t01.identification_number"
		    			+ "     , t02.id as identification_type_id"
		    			+ "     , t02.label as identification_label"
						+ "  from users t01, identification_types t02"
						+ " where t02.id = t01.identification_type_id"
				;
    	
    	if (identificationTypeId != null) {
    		sqlQuery += String.format("   and t02.id = %s", identificationTypeId);
    		sqlQuery += " order by t01.id asc;";
    	}
    	
    	else {
        	sqlQuery += " order by t02.id asc, t01.id asc;";
    	}
    	
    	
        try {
        	
        	return new UserRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));
        	
        } catch (Exception e) {
        	
            e.printStackTrace();
        
        }
        
        
		return null;

    }
    
    //  READ (Single Customer)
    public User read(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as user_type_id"
    			+ "     , t01.last_name"
    			+ "		, t01.middle_name"
    			+ "		, t01.first_name"
    			+ "		, t01.date_of_birth"
    			+ "		, t01.address"
    			+ "		, t01.email"
    			+ "		, t01.phone"
    			+ "		, t01.identification_number"
				+ "     , t02.label"
				+ "  from users t01, user_types t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.user_type_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new UserRowMapper()
        	);
        
    } 
    
    public User read1(Long id) throws SQLException{

    	String sqlQuery = "select t01.id"
    			+ "     , t02.id as identification_type_id"
    			+ "     , t01.last_name"
    			+ "		, t01.middle_name"
    			+ "		, t01.first_name"
    			+ "		, t01.date_of_birth"
    			+ "		, t01.address"
    			+ "		, t01.email"
    			+ "		, t01.phone"
    			+ "		, t01.identification_number"
				+ "     , t02.label"
				+ "  from users t01, identification_types t02"
				+ " where t01.id = ?"
				+ "   and t02.id = t01.identification_type_id"
				+ " order by t02.id asc, t01.id asc"
				+ ";"
		;

    	return this.jdbcTemplate.queryForObject(
    			sqlQuery,
        		new Object[] {id},
        		new UserRowMapper()
        	);
        
    } 

    
    //  UPDATE
    public void update(User user) throws SQLException {
        
    	String sqlQuery = "update users set user_type_id=? last_name=? middle_name=? first_name=? date_of_birth=?"
    			+ " address=? email=? phone=? identification_type_id=? identification_number=? where id=?";
        

        try {
        	this.jdbcTemplate.update(
					sqlQuery,
					
					new Object[] {
							user.getUserTypeId(),
							user.getLastName(),
							user.getMiddleName(),
							user.getFirstName(),
							user.getDateOfBirth(),
							user.getAddress(),
							user.getEmail(),
							user.getPhone(),
							user.getIdentificationTypeId(),
							user.getIdentificationNumber(),
							user.getId()
						}
				);
            
            
            System.out.println("User successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        
    	String sqlQuery = "delete from users where id=?";

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
        
    	User user = this.read(id);
//        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));
        
        this.update(user);
        
    }

}
