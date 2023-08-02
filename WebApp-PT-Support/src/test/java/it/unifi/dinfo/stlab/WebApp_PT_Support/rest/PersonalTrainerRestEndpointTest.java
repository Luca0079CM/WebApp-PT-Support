package it.unifi.dinfo.stlab.WebApp_PT_Support.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unifi.dinfo.stlab.WebApp_PT_Support.rest.PersonalTrainerRestEndpoint;

public class PersonalTrainerRestEndpointTest {
	private static final String baseUrl = "http://localhost:8080/WebApp-PT-Support/rest/ptrainers/";
	private PersonalTrainerRestEndpoint ptRestEndpoint;
	
	@Test
	public void testFindCustomersByPTId() {
		ptRestEndpoint = new PersonalTrainerRestEndpoint();
		Response response = RestAssured.given().pathParam("ptId", "40").get(baseUrl + "list-customer/" + "{ptId}");
		System.out.println(response.getStatusCode());
		Assertions.assertEquals(200, response.getStatusCode());
	}
	
//	@Test
//	public void testSearchExercise() {
//		ptRestEndpoint = new PersonalTrainerRestEndpoint();
//		Response response = RestAssured.given().pathParam("ptId", "4818800298257727865").get(baseUrl + "{ptId}" + "/search-exercise");
//		System.out.println(response.getStatusCode());
//		Assertions.assertEquals(200, response.getStatusCode());
//	}
//	
//	@Test
//	public void testSearchWorkoutProgram() {
//		ptRestEndpoint = new PersonalTrainerRestEndpoint();
//		Response response = RestAssured.given().pathParam("ptId", "4818800298257727865").get(baseUrl + "{ptId}" + "/search-workoutprogram");
//		System.out.println(response.getStatusCode());
//		Assertions.assertEquals(200, response.getStatusCode());
//	}

}
