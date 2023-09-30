package it.unifi.dinfo.stlab.WebApp_PT_Support.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Path;
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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import it.unifi.dinfo.stlab.WebApp_PT_Support.controllers.AuthenticationController;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.CustomerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.LoginFormDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.PersonalTrainerDTO;
import it.unifi.dinfo.stlab.WebApp_PT_Support.dto.TokenDTO;

@Path("")
public class AuthenticationRestEndpoint {
	
	@Inject
	private AuthenticationController authController;
	
	@POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginFormDTO lfDTO) {
        
        String email = lfDTO.getEmail();
        String password = lfDTO.getPassword();
        
        // Effettua l'autenticazione e genera un token JWT se le credenziali sono valide
        String token = authController.authenticate(email, password);
        TokenDTO tokenDto = new TokenDTO();
        tokenDto.setToken(token);
        if (token != null) {
        	System.out.println("token != null in AuthenticationRestEndpoint");
        	return Response.status(Response.Status.OK).entity(tokenDto).build();
        }
        else {
        	System.out.println("token == null in AuthenticationRestEndpoint");
        	return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
	
	@POST
    @Path("/customer/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginCustomer(LoginFormDTO lfDTO) {
        String email = lfDTO.getEmail();
        String password = lfDTO.getPassword();
        
        // Effettua l'autenticazione e genera un token JWT se le credenziali sono valide
        String token = authController.authenticateCustomer(email, password);
        TokenDTO tokenDto = new TokenDTO();
        tokenDto.setToken(token);
        if (token != null) {
        	System.out.println("token != null in AuthenticationRestEndpoint for Customer");
        	return Response.status(Response.Status.OK).entity(tokenDto).build();
        }
        else {
        	System.out.println("token == null in AuthenticationRestEndpoint for Customer");
        	return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
	
	@POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(PersonalTrainerDTO ptDTO) {
		PersonalTrainerDTO responseDTO = authController.register(ptDTO);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
    }
	
	@POST
    @Path("/customer/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerCustomer(CustomerDTO cDTO) {
		CustomerDTO responseDTO = authController.registerCustomer(cDTO);
		return Response.status(Response.Status.OK).entity(responseDTO).build();
    }
	

}
