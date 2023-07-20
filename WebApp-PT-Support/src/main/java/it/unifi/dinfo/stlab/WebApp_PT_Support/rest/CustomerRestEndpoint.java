package it.unifi.dinfo.stlab.WebApp_PT_Support.rest;

import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;

@Path("/customers")
public class CustomerRestEndpoint {

//	@Inject
	CustomerDao customerDao;
	//TODO: iniettare anche mapper, usare mapper e dao nei metodi per costruire i dtos i quali veranno ritornati
	//oppure iniettare vari controllers ed usare i loro metodi (che useranno mapper e dao e costruiranno dtos) per restituire i dtos

	@GET
	@Path("/ping")
	public Response ping() {
		return Response.ok().entity("the service is online").build();
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerById(@PathParam("id") Long id) {
		Customer c = customerDao.findById(id);
		return Response.status(Response.Status.OK).entity(c).build();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCustomers() {
		List<Customer> cList = customerDao.findAll();
		return Response.status(Response.Status.OK).entity(cList).build();
	}

//	@POST
//	@Path("/save")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response saveCustomer(String requestBody) {
//
//	}

//	@PUT
//	@Path("/update")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response updateCustomer(String requestBody) {
//
//	}


}
