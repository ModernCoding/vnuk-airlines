package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2010CreatePlaneModels {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2010CreatePlaneModels(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE plane_models ("
				+ "id BIGINT NOT NULL AUTO_INCREMENT, "
				+ "plane_manufacturer_id BIGINT NOT NULL,"
				+ "label VARCHAR(255) NOT NULL, "
				+ "FOREIGN KEY (plane_manufacturer_id),"
				+ "PRIMARY KEY (id),"
				+ "REFERENCES plane_manufacturers (plane_manufacturer_id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2010CreatePlaneModels started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'contacts\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2010CreatePlaneModels ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}

}
