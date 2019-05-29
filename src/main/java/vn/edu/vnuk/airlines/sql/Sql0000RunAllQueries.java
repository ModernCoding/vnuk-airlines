package vn.edu.vnuk.airlines.sql;

import java.sql.Connection;
import java.sql.SQLException;

import vn.edu.vnuk.airlines.jdbc.ConnectionFactory;

public class Sql0000RunAllQueries {

	public static void main(String[] args) throws SQLException {
		
		Connection connectionDb = new ConnectionFactory()
				.getConnection("jdbc:mysql://localhost/");
		
		
		//	Create database
		
		new Sql1000DropDatabase(connectionDb).run();
		new Sql1010CreateDatabase(connectionDb).run();

		connectionDb.close();

		
		//	Create tables 
		
		Connection connectionTable = new ConnectionFactory()
				.getConnection();
		
		new Sql2010CreatePlaneManufacturers(connectionTable).run();
		new Sql2010CreatePlaneModels(connectionTable).run();
		new Sql2010CreateCountries(connectionTable).run();
		new Sql2010CreateCities(connectionTable).run();
		new Sql2010CreateAirports(connectionTable).run();
		new Sql2010CreateRoutes(connectionTable).run();
		new Sql2010CreateDays(connectionTable).run();
		new Sql2010CreateFlights(connectionTable).run();
		new Sql2010CreateUserTypes(connectionTable).run();
		new Sql2010CreateIdentificationTypes(connectionTable).run();
		new Sql2010CreateUsers(connectionTable).run();		
		new Sql2010CreatePaymentMethods(connectionTable).run();
		new Sql2010CreateClasses(connectionTable).run();
		new Sql2010CreatePriceTypes(connectionTable).run();
		new Sql2010CreatePrices(connectionTable).run();
		new Sql2010CreateBookings(connectionTable).run();
		new Sql2010CreateServices(connectionTable).run();
		new Sql2010CreateBookingServices(connectionTable).run();

		
		//	Insert data into tables
		
		new Sql5010InsertIntoPlaneManufacturers(connectionTable).run();
		new Sql5010InsertIntoPlaneModels(connectionTable).run();
		new Sql5010InsertIntoCountries(connectionTable).run();
		new Sql5010InsertIntoCities(connectionTable).run();
		new Sql5010InsertIntoAirports(connectionTable).run();
		new Sql5010InsertIntoRoutes(connectionTable).run();
		new Sql5010InsertIntoDays(connectionTable).run();
		new Sql5010InsertIntoFlights(connectionTable).run();
		new Sql5010InsertIntoUserTypes(connectionTable).run();
		new Sql5010InsertIntoIdentificationTypes(connectionTable).run();
		new Sql5010InsertIntoUsers(connectionTable).run();
		new Sql5010InsertIntoPaymentMethods(connectionTable).run();
		new Sql5010InsertIntoClasses(connectionTable).run();
		new Sql5010InsertIntoPriceTypes(connectionTable).run();
		new Sql5010InsertIntoPrices(connectionTable).run();
		new Sql5010InsertIntoBookings(connectionTable).run();
		new Sql5010InsertIntoServices(connectionTable).run();
		new Sql5010InsertIntoBookingServices(connectionTable).run();
		
		
		connectionTable.close();
		
	}

}
