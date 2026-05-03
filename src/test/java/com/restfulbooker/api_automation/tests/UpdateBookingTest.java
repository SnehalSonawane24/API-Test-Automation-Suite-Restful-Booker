package com.restfulbooker.api_automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import com.restfulbooker.api_automation.base.BaseTest;
import static com.restfulbooker.api_automation.endpoints.Endpoints.*;
import com.restfulbooker.api_automation.payloads.BookingPayload;

import static io.restassured.RestAssured.given;

public class UpdateBookingTest extends BaseTest {

	@Test(priority = 4)
	public void testUpdateBooking() {
		Response response = given().spec(requestSpec).header("Cookie", "token=" + AuthTest.token)
				.pathParam("id", CreateBookingTest.bookingId).body(BookingPayload.updateBooking().toString()).when()
				.put(BOOKING_ID).then().statusCode(200).body("firstname", equalTo("Jane"))
				.body("lastname", equalTo("Smith")).body("totalprice", equalTo(250)).body("depositpaid", equalTo(false))
				.extract().response();

		Assert.assertEquals(response.jsonPath().getString("firstname"), "Jane", "First name should be updated to Jane");

		System.out.println("Updated Booking: " + response.prettyPrint());
	}

	@Test(priority = 5)
	public void testPartialUpdateBooking() {
		// PATCH — only send the fields you want to change
		String partialBody = "{ \"firstname\": \"UpdatedName\", \"totalprice\": 999 }";

		Response response = given().spec(requestSpec).header("Cookie", "token=" + AuthTest.token)
				.pathParam("id", CreateBookingTest.bookingId).body(partialBody).when().patch(BOOKING_ID)

				.then().statusCode(200).body("firstname", equalTo("UpdatedName")).body("totalprice", equalTo(999))
				.extract().response();

		Assert.assertEquals(response.jsonPath().getString("firstname"), "UpdatedName",
				"First name should be partially updated");

		System.out.println("Partially Updated Booking: " + response.prettyPrint());
	}

}
