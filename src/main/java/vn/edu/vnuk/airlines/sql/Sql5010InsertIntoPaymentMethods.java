package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoPaymentMethods {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoPaymentMethods(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO payment_methods (label) "
				+ 	"values"
				+ 	"('Mastercard'),"
				+ 	"('VISA'),"
				+	";"
				;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoPaymentMethods started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'payment_methods\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoPaymentMethods ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
