package vn.edu.vnuk.airlines.rowmapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import vn.edu.vnuk.airlines.model.IdentificationType;
import vn.edu.vnuk.airlines.model.User;
import vn.edu.vnuk.airlines.model.UserType;

public class UserRowMapper  implements RowMapper<User>{
	
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		UserType userType = new UserType();
		IdentificationType identificationType = new IdentificationType();
		User user = new User();
		
		userType.setId(rs.getLong("user_type_id"));
		userType.setLabel(rs.getString("user_type_label"));
		
		identificationType.setId(rs.getLong("identification_type_id"));
		identificationType.setLabel(rs.getString("identification_type_label"));
		
		user.setId(rs.getLong("id"));
		user.setUserTypeId(rs.getLong("user_type_id"));
		user.setLastName(rs.getString("last_name"));
		user.setMiddleName(rs.getString("middle_name"));
		user.setFirstName(rs.getString("first_name"));
		user.setDateOfBirth(rs.getDate("date_of_birth"));
		user.setAddress(rs.getString("address"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getInt("phone"));
		user.setIdentificationTypeId(rs.getLong("identification_type_id"));
		user.setIdentificationNumber(rs.getInt("identification_number"));
		user.setUserType(userType);
		user.setIdentificationType(identificationType);
		
		return user;
				
	}
	
	
	public List<User> mapRows(List<Map<String, Object>> rows) throws SQLException {
		
		List<User> users = new ArrayList<User>();
		
		
    	for (Map<String, Object> row : rows) {
			
    		UserType userType = new UserType();
    		IdentificationType identificationType = new IdentificationType();
    		User user = new User();
    		
    		userType.setId((Long) row.get("user_id"));
    		userType.setLabel((String) row.get("user_label"));
    		
    		identificationType.setId((Long) row.get("identification_type_id"));
    		identificationType.setLabel((String) row.get("identification_type_label"));
    		
    		user.setId((Long) row.get("id"));
    		user.setUserTypeId((Long) row.get("user_type_id"));
    		user.setLastName((String) row.get("last_name"));
    		user.setMiddleName((String) row.get("middle_name"));
    		user.setFirstName((String) row.get("first_name"));
    		user.setDateOfBirth((Date) row.get("date_of_birth"));
    		user.setAddress((String)row.get("address"));
    		user.setPhone((int) row.get("phone"));
    		user.setIdentificationTypeId((Long) row.get("identification_type_id"));
    		user.setIdentificationNumber((int) row.get("identification_number"));
    		user.setUserType(userType);
    		user.setIdentificationType(identificationType);
			
			
			users.add(user);
			
		}
		
    	
		return users;

	}

}
