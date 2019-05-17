package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoFlights {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoFlights(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO flights (route_id,day_id,plane_model_id,departure_time, arrival_time) "
				+ 	"values"
				+	"('1,2,2, 02:42 PM , 03:56 PM'),"
				+	"('2,6,6, 04:42 PM , 06:01 PM'),"
				+	"('3,3,4, 09:42 PM , 11:00 PM'),"
				+	"('4,11,1, 01:35 AM , 02:59 AM'),"
				+	"('5,7,8, 03:23 AM , 03:54 AM'),"
				+	"('6,10,7, 07:18 AM , 07:48 AM'),"
				+ 	";"
				;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoFlights started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'flights\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoFlights ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
