package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoPlaneManufacturers {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoPlaneManufacturers(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO plane_manufacturers (label)"
                +	"values"
				+	"('Boeing'),"
                +	"('Airbus'),"
                +	"('Comac'),"
                +	"('Embraer'),"
                +	"('Bombardier')"
                + ";"
				;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoPlaneManufacturers started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'plane_manufacturers\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoPlaneManufacturers ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
