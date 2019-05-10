package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2010CreateBookings {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2010CreateBookings(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE bookings ("
				+ "id BIGINT NOT NULL AUTO_INCREMENT, "
				+ "user_id BIGINT NOT NULL,"
				+ "flight_id BIGINT NOT NULL, "
				+ "seat_number VARCHAR(255) NOT NULL, "
				+ "price_id BIGINT NOT NULL, "
				+ "payment_method_id BIGINT NOT NULL, "
				+ "FOREIGN KEY (user_id),"
				+ "FOREIGN KEY (flight_id),"
				+ "FOREIGN KEY (price_id),"
				+ "FOREIGN KEY (payment_method_id),"
				+ "PRIMARY KEY (id),"
				+ "REFERENCES users (user_id),"
				+ "REFERENCES flights (flight_id),"
				+ "REFERENCES prices (price_id),"
				+ "REFERENCES payment_methods (payment_method_id),"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2010CreateBookings started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'contacts\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2010CreateBookings ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
