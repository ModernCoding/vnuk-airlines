package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoRoutes {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoRoutes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO routes (take_off_airport_id,landing_airport_id) "
				+ 	"values"
				+ 	"('1,2),"
				+ 	"('1,3),"
				+ 	"('2,1),"
				+ 	"('2,3),"
				+ 	"('3,1),"
				+ 	"('3,2)"
				+	";"
				;
		
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoRoutes started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'routes\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoRoutes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
