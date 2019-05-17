package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoCountries {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoCountries(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO countries (label) "
				+	"values"
				+	"('Vietnam'),"
				+	"('Hungary'),"
				+	"('USA'),"
				+	"('France'),"
				+	"('Germany'),"
				+	"('Portugal'),"
				+	"('Spain'),"
				+	"('Morocco')"
				+ 	";"
				;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoCountries started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'countries\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoCountries ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
