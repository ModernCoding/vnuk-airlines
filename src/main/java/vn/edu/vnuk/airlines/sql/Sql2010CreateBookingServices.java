package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2010CreateBookingServices {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2010CreateBookingServices(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE booking_services ("
				+ "id BIGINT NOT NULL AUTO_INCREMENT, "
				+ "booking_id BIGINT NOT NULL, "
				+ "service_id BIGINT NOT NULL, "
				+ "FOREIGN KEY (booking_id), "
				+ "FOREIGN KEY (service_id), "
				+ "PRIMARY KEY (id),"
				+ "REFERENCES bookings (booking_id), "
				+ "REFERENCES services (service_id) "
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2010CreateBookingServices started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'contacts\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2010CreateBookingServices ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}

}
