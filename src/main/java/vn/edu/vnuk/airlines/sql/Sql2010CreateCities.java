package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2010CreateCities {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2010CreateCities(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE cities ("
				+ "id BIGINT NOT NULL AUTO_INCREMENT, "
				+ "country_id BIGINT NOT NULL,"
				+ "label VARCHAR(255) NOT NULL, "
				+ "FOREIGN KEY (country_id),"
				+ "PRIMARY KEY (id),"
				+ "REFERENCES countries (country_id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2010CreateCities started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'contacts\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2010CreateCities ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}

}
