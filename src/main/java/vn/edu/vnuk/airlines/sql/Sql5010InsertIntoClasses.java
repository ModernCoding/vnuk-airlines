package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoClasses {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoClasses(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO classes (label) "
				+ 	"values"
				+ 	"('Economy'),"
				+ 	"('Business'),"
				+ 	"('First class'),"
				+	";"
				;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoClasses started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'classes\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoClasses ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
