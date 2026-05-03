package com.restfulbooker.api_automation.base;

import java.io.FileOutputStream;
import java.io.PrintStream;

import org.testng.annotations.BeforeSuite;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest {

	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;

	@BeforeSuite
	public void setup() throws Exception {
		// Redirect logs to a file for later review
		PrintStream log = new PrintStream(new FileOutputStream("logs/api-log.txt"));

		requestSpec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com")
				.setContentType(ContentType.JSON).addFilter(new RequestLoggingFilter(log))
				.addFilter(new ResponseLoggingFilter(log)).build();

		responseSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
	}

}
