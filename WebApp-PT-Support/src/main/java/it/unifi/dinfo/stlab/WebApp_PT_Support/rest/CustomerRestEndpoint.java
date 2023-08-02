package it.unifi.dinfo.stlab.WebApp_PT_Support.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import it.unifi.dinfo.stlab.WebApp_PT_Support.controllers.CustomerController;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.WorkoutProgramDTO;

@Path("")
public class CustomerRestEndpoint {

	@Inject
	private CustomerController customerController;

	@GET
	@Path("/ping")
	public Response ping() {
		return Response.ok().entity("the service is online").build();
	}

	@GET
	@Path("wprograms/detach/{wpId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response detachWorkoutProgram(@PathParam("wpId") Long wpId, Long customerId) {
		WorkoutProgramDTO wpDTO = customerController.detachWorkoutProgram(customerId, wpId);
		return Response.status(Response.Status.OK).entity(wpDTO).build();
	}
}
