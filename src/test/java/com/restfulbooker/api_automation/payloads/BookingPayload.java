package com.restfulbooker.api_automation.payloads;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class BookingPayload {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static ObjectNode createBooking() {
		ObjectNode bookingDates = mapper.createObjectNode();
		bookingDates.put("checkin", "2024-01-01");
		bookingDates.put("checkout", "2024-01-07");

		ObjectNode booking = mapper.createObjectNode();
		booking.put("firstname", "John");
		booking.put("lastname", "Doe");
		booking.put("totalprice", 150);
		booking.put("depositpaid", true);
		booking.set("bookingdates", bookingDates);
		booking.put("additionalneeds", "Breakfast");

		return booking;
	}

	public static ObjectNode updateBooking() {
		ObjectNode bookingDates = mapper.createObjectNode();
		bookingDates.put("checkin", "2024-02-01");
		bookingDates.put("checkout", "2024-02-10");

		ObjectNode booking = mapper.createObjectNode();
		booking.put("firstname", "Jane");
		booking.put("lastname", "Smith");
		booking.put("totalprice", 250);
		booking.put("depositpaid", false);
		booking.set("bookingdates", bookingDates);
		booking.put("additionalneeds", "Lunch");

		return booking;
	}

}
