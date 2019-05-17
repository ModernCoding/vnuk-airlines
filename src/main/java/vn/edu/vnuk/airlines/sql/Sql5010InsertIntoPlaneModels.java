package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoPlaneModels {

	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoPlaneModels(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO plane_models (plane_manufacturer_id, label)"
				+	"values"
				+	"('1,707'),"
                +	"('1, 727'),"
                +	"('1, 737'),"
                +	"('1, 757'),"
                +	"('1, 787'),"
                +	"('2, A220'),"
                +	"('2, A300'),"
                +	"('2, A330'),"
                +	"('3, ARJ21'),"
                +	"('3, C919'),"
                +	"('4, ERJ145'),"
                +	"('4, 195E'),"
                +	"('4, 195E2'),"
                +	"('5, CRJ100'),"
                +	"('5, CRJ200')"
                + ";"
				;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoPlaneModels started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'plane_models\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoPlaneModels ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
