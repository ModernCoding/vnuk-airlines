package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoAirports {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoAirports(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO airports (country_id, city_id, label, code)"				
								+ 	"values"
								+ 	"('1,1, Da Nang International Airport, DAD),"
								+ 	"('1,2, Tan Son Nhat International Aiport, SGN),"
								+ 	"('1,3, Noi Bai International Aiport, HAN),"
								+ 	"('2,1, Liszt Ferenc International Aiport, BUD),"
								+ 	"('3,1,	Los Angeles International Aiport, LAX),"
								+ 	"('3,2, Honolul International Aiport, HNL),"
								+ 	"('3,3, San Francisco International Aiport, SAX),"
								+ 	"('4,1, Paris International Aiport, CDG),"
								+ 	"('4,2, Bordeaux International Aiport, BOD),"
								+ 	"('4,3, Nizza International Aiport, NCE),"
								+ 	"('4,4, Lille International Aiport, LIL),"
								+ 	"('5,1, Berlin International Aiport, BER),"
								+ 	"('5,2, Dortmund International Aiport, DTM),"
								+ 	"('5,3, Frankfurt International Aiport, FRA),"
								+ 	"('6,1, Lisbon International Aiport, LIS),"
								+ 	"('6,2, Faro International Aiport, FAO),"
								+ 	"('7,1, Madrid International Aiport, MAD),"
								+ 	"('7,2, Barcelona International Aiport, BCN),"
								+ 	"('8,1, Marrakesh International Aiport, RAK)"				
								+	";"
								;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5010InsertIntoAirports started");

		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   DATA successfully loaded in \'airports\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5010InsertIntoAirports ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
