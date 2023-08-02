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

import it.unifi.dinfo.stlab.WebApp_PT_Support.controllers.PersonalTrainerController;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.PersonalTrainer;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutProgramDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.ExerciseDTO;

@Path("") 
public class PersonalTrainerRestEndpoint {
	
	@Inject
	private PersonalTrainerController ptController;
	
	@POST
	@Path("customers/create/{custId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCustomer(@PathParam("custId") Long custId, Long ptId, CustomerDTO cDTO) {
		CustomerDTO responseDTO = ptController.createCustomer(ptId, custId, cDTO);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@PUT
	@Path("customers/disable/{custId}")
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
	
	@POST
	@Path("exercises/create/{exId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createExercise(@PathParam("exId") Long exId, Long machineId, ExerciseDTO exDTO) {
		ExerciseDTO responseDTO = ptController.createExercise(machineId, exId, exDTO);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@GET
	@Path("exercises/search/{exId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchExercise(@PathParam("exId") Long exId) {
		ExerciseDTO exDTO = ptController.searchExercise(exId);
		return Response.status(Response.Status.OK).entity(exDTO).build();
	}
	
	@POST
	@Path("wprograms/create/{wpId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createWorkoutProgram(@PathParam("wpId") Long wpId, WorkoutProgramDTO wpDTO) {
		WorkoutProgramDTO responseDTO = ptController.createWorkoutProgram(wpId, wpDTO);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@GET
	@Path("wprograms/search/{wpId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchWorkoutProgram(@PathParam("wpId") Long wpId) {
		WorkoutProgramDTO wpDTO = ptController.searchWorkoutProgram(wpId);
		return Response.status(Response.Status.OK).entity(wpDTO).build();
	}
}
