package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2010CreateUsers {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2010CreateUsers(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE users ("
				+ "id BIGINT NOT NULL AUTO_INCREMENT, "
				+ "users_type_id BIGINT NOT NULL, "
				+ "last_name VARCHAR(255) NOT NULL, "
				+ "middle_name VARCHAR(255), "
				+ "first_name VARCHAR(255) NOT NULL, "
				+ "date_of_birth DATE NOT NULL, "
				+ "address VARCHAR(255) NOT NULL, "
				+ "email VARCHAR(255) NOT NULL, "
				+ "phone INT NOT NUL, "
				+ "identification_type_id BIGINT NOT NULL, "
				+ "identification_number INT NOT NUL, "
				+ "password VARCHAR(255) NOT NULL, "
				+ "FOREIGN KEY (user_type_id), "
				+ "PRIMARY KEY (id),"
				+ "REFERENCES user_types (user_type_id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2010CreateUsers started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'contacts\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2010CreateUsers ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}

}
