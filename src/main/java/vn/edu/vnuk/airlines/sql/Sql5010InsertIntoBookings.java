package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoBookings {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoBookings(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO bookings (user_id, flight_id, seat_number, price_id, payment_method_id) "
				+ 	"values"
				+ 	"('2,2,23A,6,1 '),"
				+ 	"('2,5,65F,1,2 '),"
				+ 	"('2,6,48E,5,2 '),"
				+ 	"('2,3,11C,2,1 '),"
				+ 	"('1,1,15B,4,2 '),"
				+ 	"('1,4,36D,3,1 '),"
				+	";"
				;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoBookings started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'bookings\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoBookings ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
