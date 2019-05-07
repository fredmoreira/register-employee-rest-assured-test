package br.com.demo.tests.api;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;



public class DemoApiTestRegisterEmployee {

	@BeforeClass
	public static void setUp()
	{
		RestAssured.baseURI = "http://localhost:5000";
	}


	@Test
	public void endpointNotFound()
	{
		given().
		contentType("application/json").
		when().
		get("/tester").
		then().
		statusCode(404);
	}

	@Test
	public void getRequestTester()
	{
		given().
		when().
			get("employees?name=TesterPortoMeetup").
		then().
			statusCode(200).
			body("[0]._id", is("5cca2e2e35e9fb1c4a1ab844")).
			body("[0].name", is("TesterPortoMeetup")).
			body("[0].nif", is(999888777)).
			body("[0].address", is("MTP"));
	}

	@Test
	public void postRequestContactFull() {
		given().
			contentType("application/json").
			body("{\"name\":\"TesterPTM\",\"nif\":999999111,\"address\":\"Porto Meetup\"}").
		when().
			post("/employees/").
		then().
			statusCode(201).
			body("name", is("TesterPTM")).
			body("nif", is(999999111)).
			body("address", is("Porto Meetup"));
	}
}
