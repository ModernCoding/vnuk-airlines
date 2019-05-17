package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoCities {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoCities(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO cities (country_id, label)"
				+	"values"
				+	"('1, Da Nang'),"
				+	"('1, Ho Chi Minh'),"
				+	"('1, Hanoi'),"
				+	"('1, Da Lat'),"
				+	"('2, Budapest'),"
				+	"('3, Los Angeles'),"
				+	"('3, Honolulu'),"
				+	"('3, San Francisco'),"
				+	"('4, Paris'),"
				+	"('4, Bordeaux'),"
				+	"('4, Nizza'),"
				+	"('4, Lille'),"
				+	"('5, Berlin'),"
				+	"('5, Dortmund'),"
				+	"('5, Frankfurt'),"
				+	"('6, Lisbon'),"
				+	"('6, Faro'),"
				+	"('7, Madrid'),"
				+	"('7, Barcelona'),"
				+	"('8, Marrakesh')"
				+ 	";"
				;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoCities started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'cities\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoCities ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
