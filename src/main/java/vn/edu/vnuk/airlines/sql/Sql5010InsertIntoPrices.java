package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoPrices {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoPrices(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO prices (flight_id, class_id, price_type_id, value) "
				+ 	"values"
				+ 	"(3, 1, 1, 1200000),"
				+ 	"(6, 2, 3, 530),"
				+ 	"(5, 3, 3, 672),"
				+ 	"(1, 2, 1, 1009234),"
				+ 	"(2, 2, 1, 1650000),"
				+ 	"(4, 3, 2, 213)"
				+	";"
				;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoPrices started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'prices\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoPrices ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
