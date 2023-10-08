package it.unifi.dinfo.stlab.WebApp_PT_Support.rest;

import java.util.List;

import it.unifi.dinfo.stlab.WebApp_PT_Support.controllers.CustomerController;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.simple.JSONObject;

import it.unifi.dinfo.stlab.WebApp_PT_Support.controllers.PersonalTrainerController;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutProgramDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutSessionDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.ExerciseDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.GymMachineDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.PersonalTrainerDTO;

@Path("") 
public class PersonalTrainerRestEndpoint {
	
	@Inject
	private PersonalTrainerController ptController;
	
	@POST
	@Path("/customers/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCustomer(CustomerDTO cDTO) {
		CustomerDTO responseDTO = ptController.createCustomer(cDTO);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@PUT
	@Path("/customers/disable/{custId}")
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	public Response disableCustomer(@PathParam("custId") Long custId) {
		CustomerDTO responseDTO = ptController.disableCustomer(custId);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@GET
	@Path("/ptrainers/list-customer/{ptId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCustomersByPTId(@PathParam("ptId") Long ptId) {
		List<CustomerDTO> cListDTO = ptController.findCustomersByPTId(ptId);
		return Response.status(Response.Status.OK).entity(cListDTO).build();
	}
	
	@GET
	@Path("/customers/search/{cName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchCustomer(@PathParam("cName") String cName) {
		List<CustomerDTO> cListDTO = ptController.searchCustomer(cName);
		return Response.status(Response.Status.OK).entity(cListDTO).build();
	}
	
	@GET
	@Path("/ptrainers/search/{ptEmail}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchPersonalTrainerByEmail(@PathParam("ptEmail") String ptEmail) {
		PersonalTrainerDTO responseDTO = ptController.searchPersonalTrainerByEmail(ptEmail);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@POST
	@Path("/gym-machine/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createGymMachine(GymMachineDTO gmDTO) {
		GymMachineDTO responseDTO = ptController.createGymMachine(gmDTO);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@POST
	@Path("/exercises/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createExercise(ExerciseDTO exDTO) {
		ExerciseDTO responseDTO = ptController.createExercise(exDTO);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@GET
	@Path("/exercise/search/{exName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchExercise(@PathParam("exName") String exName) {
		List<ExerciseDTO> responseDTO = ptController.searchExercise(exName);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@PUT //devo fare post sennò non funziona (mette di default nell'Allow degli header cors solo POST come metodo http)
	@Path("/wprograms/{wpId}/add-ex")
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	public Response addExerciseToWorkoutProgram(@PathParam("wpId") Long wpId, ExerciseDTO exDTO) {
		WorkoutProgramDTO responseDTO = ptController.addExerciseToWorkoutProgram(wpId, exDTO);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@POST
	@Path("/wprograms/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createWorkoutProgram(WorkoutProgramDTO wpDTO) {
		WorkoutProgramDTO responseDTO = ptController.createWorkoutProgram(wpDTO);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@GET
	@Path("/wprograms/search/{wpName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchWorkoutProgram(@PathParam("wpName") String wpName) {
		List<WorkoutProgramDTO> wpDTOList = ptController.searchWorkoutProgram(wpName);
		return Response.status(Response.Status.OK).entity(wpDTOList).build();
	}
	
	@PUT
	@Path("/customers/{custId}/assign-wp")
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignWorkoutProgramToCustomer(@PathParam("custId") Long custId, WorkoutProgramDTO wpDTO) {
		CustomerDTO responseDTO = ptController.assignWorkoutProgramToCustomer(custId, wpDTO);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@GET
	@Path("/gym-machines/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listGymMachines() {
		List<GymMachineDTO> gmDTOList = ptController.listGymMachines();
		return Response.status(Response.Status.OK).entity(gmDTOList).build();
	}
	
	@GET
	@Path("/exercises/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listExercises() {
		List<ExerciseDTO> exDTOList = ptController.listExercises();
		return Response.status(Response.Status.OK).entity(exDTOList).build();
	}
	
	@GET
	@Path("/wprograms/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listWorkoutProgram() {
		List<WorkoutProgramDTO> wpDTOList = ptController.listWorkoutProgram();
		return Response.status(Response.Status.OK).entity(wpDTOList).build();
	}
	
	//probabilmente da levare perchè lista le sessions di TUTTI i customers
	@GET
	@Path("/sessions/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listWorkoutSessions() {
		List<WorkoutSessionDTO> wsDTOList = ptController.listWorkoutSessions();
		return Response.status(Response.Status.OK).entity(wsDTOList).build();
	}
	
	@GET
	@Path("/ptrainers/list-sessions/{ptId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listWorkoutSessionsOfPTCustomers(@PathParam("ptId") Long ptId) {
		List<WorkoutSessionDTO> wsDTOList = ptController.listWorkoutSessionsOfPTCustomers(ptId);
		return Response.status(Response.Status.OK).entity(wsDTOList).build();
	}
	
	@GET
	@Path("/ping")
	@Produces(MediaType.APPLICATION_JSON)
	public Response ping() {
		JSONObject jsonResponse = new JSONObject();
//	    String message = "Server is up and running!";
	    return Response.status(Response.Status.OK).entity(jsonResponse).build();
	}
	
}
