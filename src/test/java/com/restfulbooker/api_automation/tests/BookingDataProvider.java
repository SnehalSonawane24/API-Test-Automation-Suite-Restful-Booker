package com.restfulbooker.api_automation.tests;

import org.testng.annotations.DataProvider;

public class BookingDataProvider {
	@DataProvider(name = "bookingData")
	public static Object[][] getBookingData() {
		return new Object[][] {
				// firstName, lastName, price, depositPaid
				{ "Alice", "Johnson", 100, true }, { "Bob", "Smith", 200, false }, { "Charlie", "Brown", 350, true },
				{ "Diana", "Prince", 500, true }, };
	}
}
