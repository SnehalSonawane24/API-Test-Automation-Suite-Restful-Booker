package com.restfulbooker.api_automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restfulbooker.api_automation.base.BaseTest;
import com.restfulbooker.api_automation.payloads.BookingPayload;
import static com.restfulbooker.api_automation.endpoints.Endpoints.*;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class CreateBookingTest extends BaseTest {

	public static int bookingId;

	@Test(priority = 2)
	public void testCreateBooking() {
		Response response = given().spec(requestSpec).body(BookingPayload.createBooking().toString()).when()
				.post(BOOKING).then().statusCode(200).extract().response();

		bookingId = response.jsonPath().getInt("bookingid");

		Assert.assertTrue(bookingId > 0, "Booking ID should be positive");
		Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "John");
		Assert.assertEquals(response.jsonPath().getString("booking.lastname"), "Doe");

		System.out.println("Created Booking ID: " + bookingId);
	}

}
