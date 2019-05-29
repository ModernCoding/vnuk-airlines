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
				+	"(1,2,2, '14:42:00' , '15:56:00'),"
				+	"(2,6,6, '16:42:00' , '18:01:00'),"
				+	"(3,3,4, '21:42:00' , '23:00:00'),"
				+	"(4,11,1, '01:35:00' , '02:59:00'),"
				+	"(5,7,8, '03:23:00' , '03:54:00'),"
				+	"(6,10,7, '07:18:00' , '07:48:00')"
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
