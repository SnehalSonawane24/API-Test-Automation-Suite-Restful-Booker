package com.restfulbooker.api_automation.tests;

import org.testng.annotations.Test;

import com.restfulbooker.api_automation.base.BaseTest;
import static com.restfulbooker.api_automation.endpoints.Endpoints.*;
import static io.restassured.RestAssured.given;

public class DeleteBookingTest extends BaseTest {

	@Test(priority = 6)
	public void testDeleteBooking() {
		// Step 1 — Delete the booking
		given().spec(requestSpec).header("Cookie", "token=" + AuthTest.token)
				.pathParam("id", CreateBookingTest.bookingId).when().delete(BOOKING_ID).then().statusCode(201); // Restful
																												// Booker
																												// returns
																												// 201
																												// on
																												// successful
																												// delete

		System.out.println("Deleted Booking ID: " + CreateBookingTest.bookingId);
	}

	@Test(priority = 7)
	public void testVerifyBookingDeleted() {
		// Step 2 — Confirm it no longer exists
		given().spec(requestSpec).pathParam("id", CreateBookingTest.bookingId).when().get(BOOKING_ID).then()
				.statusCode(404);

		System.out.println("Confirmed: Booking ID " + CreateBookingTest.bookingId + " no longer exists");
	}
}
