package com.restfulbooker.api_automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restfulbooker.api_automation.base.BaseTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static com.restfulbooker.api_automation.endpoints.Endpoints.BOOKING;

public class DataDrivenBookingTest extends BaseTest {

	@Test(dataProvider = "bookingData", dataProviderClass = BookingDataProvider.class, priority = 8)
	public void testCreateBookingWithMultipleData(String firstName, String lastName, int price, boolean deposit) {

		String body = String.format(
				"{ " + "\"firstname\": \"%s\", " + "\"lastname\": \"%s\", " + "\"totalprice\": %d, "
						+ "\"depositpaid\": %b, " + "\"bookingdates\": { " + "\"checkin\": \"2024-03-01\", "
						+ "\"checkout\": \"2024-03-05\" " + "}, " + "\"additionalneeds\": \"Breakfast\" " + "}",
				firstName, lastName, price, deposit);

		Response response = given().spec(requestSpec).body(body).when().post(BOOKING).then().statusCode(200)
				.body("booking.firstname", equalTo(firstName)).body("booking.lastname", equalTo(lastName))
				.body("booking.totalprice", equalTo(price)).extract().response();

		int newBookingId = response.jsonPath().getInt("bookingid");

		Assert.assertTrue(newBookingId > 0, "Booking ID should be positive");
		Assert.assertEquals(response.jsonPath().getString("booking.firstname"), firstName);
		Assert.assertEquals(response.jsonPath().getString("booking.lastname"), lastName);

		System.out.println("Created booking for: " + firstName + " " + lastName + " | ID: " + newBookingId);
	}

}
