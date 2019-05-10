package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2010CreateRoutes {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2010CreateRoutes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE routes ("
				+ "id BIGINT NOT NULL AUTO_INCREMENT, "
				+ "take_off_airport_id BIGINT NOT NULL, "
				+ "landing_airport_id BIGINT NOT NULL, "
				+ "FOREIGN KEY (take_off_airport_id),"
				+ "FOREIGN KEY (landing_airport_id),"
				+ "PRIMARY KEY (id),"
				+ "REFERENCES airports (take_off_airport_id),"
				+ "REFERENCES airports (landing_airport_id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2010CreateRoutes started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'contacts\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2010CreateRoutes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}

}
