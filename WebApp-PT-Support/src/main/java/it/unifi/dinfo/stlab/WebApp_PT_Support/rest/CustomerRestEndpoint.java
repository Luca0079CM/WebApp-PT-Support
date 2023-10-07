package it.unifi.dinfo.stlab.WebApp_PT_Support.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

import it.unifi.dinfo.stlab.WebApp_PT_Support.controllers.CustomerController;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.ExerciseDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.PersonalTrainerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutProgramDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutSessionDTO;

@Path("")
public class CustomerRestEndpoint {

	@Inject
	private CustomerController customerController;
	
	@GET
	@Path("/customers/search-by-email/{cEmail}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchCustomer(@PathParam("cEmail") String cEmail) {
		CustomerDTO responseDTO = customerController.searchCustomerByEmail(cEmail);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@GET
	@Path("/customers/list-wprograms/{cId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listWorkoutProgram(@PathParam("cId") Long cId) {
		List<WorkoutProgramDTO> wpDTOList = customerController.listWorkoutProgram(cId);
		return Response.status(Response.Status.OK).entity(wpDTOList).build();
	}
	
	@GET
	@Path("/ptrainers/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPersonalTrainer() {
		List<PersonalTrainerDTO> ptDTOList = customerController.listPersonalTrainer();
		return Response.status(Response.Status.OK).entity(ptDTOList).build();
	}
	
	@PUT //mettere PUT
	@Path("/customers/change-pt/{cId}/{ptId}")
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	public Response changePersonalTrainer(@PathParam("cId") Long cId, @PathParam("ptId") Long ptId) {
		CustomerDTO responseDTO = customerController.changePersonalTrainer(cId, ptId);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
	@POST
	@Path("/customers/save-session")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveWorkoutSession(WorkoutSessionDTO wsDTO) {
		WorkoutSessionDTO responseDTO = customerController.saveWorkoutSession(wsDTO);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
	}
	
}
