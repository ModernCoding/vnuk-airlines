package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2010CreateDays {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2010CreateDays(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE days ("
				+ "id BIGINT NOT NULL AUTO_INCREMENT, "
				+ "label VARCHAR(255) NOT NULL, "
				+ "PRIMARY KEY (id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2010CreateDays started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'contacts\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2010CreateDays ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}

}
