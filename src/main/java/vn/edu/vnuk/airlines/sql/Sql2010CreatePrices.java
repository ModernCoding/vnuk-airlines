package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2010CreatePrices {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2010CreatePrices(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE prices ("
				+ "id BIGINT NOT NULL AUTO_INCREMENT, "
				+ "flight_id BIGINT NOT NULL,"
				+ "class_id BIGINT NOT NULL, "
				+ "price_type_id BIGINT NOT NULL, "
				+ "value VARCHAR(255) NOT NULL, "
				+ "FOREIGN KEY (flight_id), "
				+ "FOREIGN KEY (class_id), "
				+ "FOREIGN KEY (price_type_id), "
				+ "PRIMARY KEY (id),"
				+ "REFERENCES flights (flight_id), "
				+ "REFERENCES classes (class_id), "
				+ "REFERENCES price_types (price_type_id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2010CreatePrices started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'contacts\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2010CreatePrices ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}

}
