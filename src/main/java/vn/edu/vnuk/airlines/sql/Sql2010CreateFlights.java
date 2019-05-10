package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2010CreateFlights {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2010CreateFlights(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE flights ("
				+ "id BIGINT NOT NULL AUTO_INCREMENT, "
				+ "route_id BIGINT NOT NULL, "
				+ "day_id BIGINT NOT NULL, "
				+ "plane_model_id BIGINT NOT NULL, "
				+ "departure_time TIME NOT NULL, "
				+ "arrival_time TIME NOT NULL, "
				+ "FOREIGN KEY (route_id), "
				+ "FOREIGN KEY (day_id), "
				+ "FOREIGN KEY (plane_model_id), "
				+ "PRIMARY KEY (id),"
				+ "REFERENCES routes (route_id), "
				+ "REFERENCES days (day_id), "
				+ "REFERENCES plane_models (plane_model_id) "
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2010CreateFlights started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'contacts\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2010CreateFlights ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}

}
