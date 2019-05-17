package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoDays {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoDays(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO days (label) "
				+ 	"values"
				+ 	"('2019.06.27.'),"
				+ 	"('2019.06.28.'),"
				+ 	"('2019.06.29.'),"
				+ 	"('2019.07.30.'),"
				+ 	"('2019.07.15.'),"
				+ 	"('2019.07.21.'),"
				+ 	"('2019.08.11.'),"
				+ 	"('2019.08.05.'),"
				+ 	"('2019.08.06.'),"
				+ 	"('2019.09.08.'),"
				+ 	"('2019.09.22.'),"
				+ 	"('2019.10.01.'),"
				+ 	"('2019.11.18.'),"
				+ 	"('2019.12.14.')"
				+	";"
				;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoDays started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'days\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoDays ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
