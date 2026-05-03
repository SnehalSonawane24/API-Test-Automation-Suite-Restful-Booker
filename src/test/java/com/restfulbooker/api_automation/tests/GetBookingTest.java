package com.restfulbooker.api_automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restfulbooker.api_automation.base.BaseTest;
import static io.restassured.RestAssured.given;
import static com.restfulbooker.api_automation.endpoints.Endpoints.*;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class GetBookingTest extends BaseTest {

	@Test(priority = 3)
	public void testGetBooking() {
		Response response = given().spec(requestSpec).pathParam("id", CreateBookingTest.bookingId).when()
				.get(BOOKING_ID).then().statusCode(200).body("firstname", equalTo("John"))
				.body("lastname", equalTo("Doe")).body("totalprice", equalTo(150)).body("depositpaid", equalTo(true))
				.extract().response();

		// Extra assertions using TestNG Assert
		Assert.assertNotNull(response.jsonPath().getString("bookingdates.checkin"));
		Assert.assertNotNull(response.jsonPath().getString("bookingdates.checkout"));

		System.out.println("Get Booking Response: " + response.prettyPrint());

	}

	@Test(priority = 3)
	public void testGetAllBookings() {
		Response response = given().spec(requestSpec).when().get(BOOKING).then().statusCode(200).extract().response();

		// Response is a list — check it's not empty
		Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Booking list should not be empty");

		System.out.println("Total Bookings: " + response.jsonPath().getList("$").size());
	}

}
