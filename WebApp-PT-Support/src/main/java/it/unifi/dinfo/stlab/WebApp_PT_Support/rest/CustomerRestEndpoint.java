package it.unifi.dinfo.stlab.WebApp_PT_Support.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unifi.dinfo.stlab.WebApp_PT_Support.dao.CustomerDao;
import it.unifi.dinfo.stlab.WebApp_PT_Support.domain.Customer;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
import javax.inject.Inject;
import java.util.List;

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
