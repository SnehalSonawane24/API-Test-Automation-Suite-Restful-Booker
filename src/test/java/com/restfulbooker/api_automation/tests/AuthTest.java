package com.restfulbooker.api_automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.restfulbooker.api_automation.base.BaseTest;
import static com.restfulbooker.api_automation.endpoints.Endpoints.*;
import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class AuthTest extends BaseTest {

	public static String token;

	@Test(priority = 1)
	public void testGenerateToken() {
		String body = "{ \"username\": \"admin\", \"password\": \"password123\" }";

		Response response = given().spec(requestSpec).body(body).when().post(AUTH).then().statusCode(200).extract()
				.response();

		token = response.jsonPath().getString("token");

		Assert.assertNotNull(token, "Token should not be null");
		Assert.assertFalse(token.isEmpty(), "Token should not be empty");
		System.out.println("Token: " + token);
	}

}
