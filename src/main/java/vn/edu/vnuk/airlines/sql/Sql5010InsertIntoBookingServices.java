package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoBookingServices {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoBookingServices(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO booking_services (booking_id, services_id,)"
				+ 	"values"
				+ 	"('1,6'),"
				+ 	"('5,2')"
				+ 	"('4,4')"
				+ 	"('2,1')"
				+ 	"('3,4')"
				+ 	"('2,3')"
				+	";";
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoBookingServices started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'booking_services\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoBookingServices ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
