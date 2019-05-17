package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoIdentificationTypes {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoIdentificationTypes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO identification_types (label) "
				+ 	"values"
				+ 	"('Passport'),"
				+ 	"('National ID'),"
				+	";"
				;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoIdentificationTypes started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'Identification_types\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoIdentificationTypes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
