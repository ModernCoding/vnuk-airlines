package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2010CreateAirports {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2010CreateAirports(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE airports ("
				+ "id BIGINT NOT NULL AUTO_INCREMENT, "
				+ "city_id BIGINT NOT NULL,"
				+ "label VARCHAR(255) NOT NULL, "
				+ "code VARCHAR(255) NOT NULL,"
				+ "FOREIGN KEY (city_id)"
				+ "PRIMARY KEY (id),"
				+ "REFERENCES cities (city_id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2010CreateAirports started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'contacts\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2010CreateAirports ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}

}
