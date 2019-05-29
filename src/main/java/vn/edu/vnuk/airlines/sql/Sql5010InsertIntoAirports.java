package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql5010InsertIntoAirports {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5010InsertIntoAirports(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO airports (city_id, label, code)"				
								+ 	"values"
								+ 	"(1, 'Da Nang International Airport', 'DAD'),"
								+ 	"(2, 'Tan Son Nhat International Aiport', 'SGN'),"
								+ 	"(3, 'Noi Bai International Aiport', 'HAN'),"
								+ 	"(1, 'Liszt Ferenc International Aiport', 'BUD'),"
								+ 	"(1, 'Los Angeles International Aiport', 'LAX'),"
								+ 	"(2, 'Honolul International Aiport', 'HNL'),"
								+ 	"(3, 'San Francisco International Aiport', 'SAX'),"
								+ 	"(1, 'Paris International Aiport', 'CDG'),"
								+ 	"(2, 'Bordeaux International Aiport', 'BOD'),"
								+ 	"(3, 'Nizza International Aiport', 'NCE'),"
								+ 	"(4, 'Lille International Aiport', 'LIL'),"
								+ 	"(1, 'Berlin International Aiport', 'BER'),"
								+ 	"(2, 'Dortmund International Aiport', 'DTM'),"
								+ 	"(3, 'Frankfurt International Aiport', 'FRA'),"
								+ 	"(1, 'Lisbon International Aiport', 'LIS'),"
								+ 	"(2, 'Faro International Aiport', 'FAO'),"
								+ 	"(1, 'Madrid International Aiport', 'MAD'),"
								+ 	"(2, 'Barcelona International Aiport', 'BCN'),"
								+ 	"(1, 'Marrakesh International Aiport', 'RAK')"				
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
