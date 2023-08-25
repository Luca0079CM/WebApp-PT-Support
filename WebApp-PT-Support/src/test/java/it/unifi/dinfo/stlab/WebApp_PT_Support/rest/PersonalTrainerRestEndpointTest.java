package it.unifi.dinfo.stlab.WebApp_PT_Support.rest;

import io.restassured.RestAssured;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.WorkoutProgramType;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.*;
import jakarta.ws.rs.core.MediaType;

import it.unifi.dinfo.stlab.WebApp_PT_Support.rest.PersonalTrainerRestEndpoint;

public class PersonalTrainerRestEndpointTest {
	private static final String baseUrl = "http://localhost:8080/WebApp-PT-Support/rest/";
	private PersonalTrainerRestEndpoint ptRestEndpoint;
	
	//TODO: implementare test per disableCustomer(), createExercise(), createWorkoutProgram()
	
	@Test
	public void testCreateCustomer() {
		ptRestEndpoint = new PersonalTrainerRestEndpoint();
		CustomerDTO cDTO = new CustomerDTO();
		cDTO.setId(Long.valueOf(55));
		cDTO.setPersonalTrainerId(40L);
		cDTO.setName("carlo");
		cDTO.setSurname("ceccherelli");
		cDTO.setEmail("boia@gmail.com");
		cDTO.setDateOfBirth("1997-10-10");
		cDTO.setPassword("password");
		cDTO.setPersonalTrainer("danny lazzarin");
		cDTO.setWorkoutProgramList(null);
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON).body(cDTO).post(baseUrl + "customers/create");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());
		Assertions.assertEquals(200, response.getStatusCode());
	}
	
	@Test
	public void testDisableCustomer() {
		ptRestEndpoint = new PersonalTrainerRestEndpoint();
		Response response = RestAssured.given().pathParam("custId", "51").put(baseUrl + "customers/disable/" + "{custId}");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());
		Assertions.assertEquals(200, response.getStatusCode());
	}
	
	@Test
	public void testFindCustomersByPTId() {
		ptRestEndpoint = new PersonalTrainerRestEndpoint();
		Response response = RestAssured.given().pathParam("ptId", "40").get(baseUrl + "ptrainers/list-customer/" + "{ptId}");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());
		Assertions.assertEquals(200, response.getStatusCode());
		response.then().body("size()", is(2));
	}
	
	@Test
	public void testCreateExercise() {
		ptRestEndpoint = new PersonalTrainerRestEndpoint();
		ExerciseDTO exDTO = new ExerciseDTO();
		exDTO.setId(25L);
		exDTO.setMachineId(10L);
		exDTO.setName("push up");
		exDTO.setDifficultyLevel(5);
		exDTO.setMachine(null);
		exDTO.setDescription("Allena il petto");;
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON).body(exDTO).post(baseUrl + "exercises/create");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());
		Assertions.assertEquals(200, response.getStatusCode());
	}
	
	@Test
	public void testSearchExercise() {
		ptRestEndpoint = new PersonalTrainerRestEndpoint();
		Response response = RestAssured.given().pathParam("exId", "20").get(baseUrl + "exercises/search/" + "{exId}");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());
		Assertions.assertEquals(200, response.getStatusCode());
//		response.then().body("size()", is(1)); //qui la size non è quanti oggetti sono (1) ma quanti campi contiene l'oggetto
	}
	
	@Test
	public void testCreateWorkoutProgram() {
		ptRestEndpoint = new PersonalTrainerRestEndpoint();
		WorkoutProgramDTO wpDTO = new WorkoutProgramDTO();
		wpDTO.setId(35L);
		wpDTO.setDifficultyLevel(10);
		wpDTO.setEstimatedDuration(60);
		wpDTO.setWorkoutProgramType(WorkoutProgramType.WEIGHTS);
		wpDTO.setExerciseList(null);
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON).body(wpDTO).post(baseUrl + "wprograms/create");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());
		Assertions.assertEquals(200, response.getStatusCode());
	}
	
	@Test
	public void testSearchWorkoutProgram() {
		ptRestEndpoint = new PersonalTrainerRestEndpoint();
		Response response = RestAssured.given().pathParam("wpId", "30").get(baseUrl + "wprograms/search/" + "{wpId}");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());
		Assertions.assertEquals(200, response.getStatusCode());
//		response.then().body("size()", is(1));
	}

}
