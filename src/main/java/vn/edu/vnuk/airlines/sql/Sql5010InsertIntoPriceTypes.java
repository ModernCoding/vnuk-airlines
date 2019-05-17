package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoPriceTypes {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoPriceTypes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO price_types (label) "
				+ 	"values"
				+ 	"('Vietnamese Dong (VND)'),"
				+ 	"('Dollar (USD)'),"
				+ 	"('Yuan (RMB)')"
				+	";"
				;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoPriceTypes started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'price_types\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoPriceTypes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
