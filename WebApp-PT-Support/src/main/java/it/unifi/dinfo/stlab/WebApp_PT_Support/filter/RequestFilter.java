package it.unifi.dinfo.stlab.WebApp_PT_Support.filter;

//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Claims;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
//import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
//import jakarta.ws.rs.core.Context;
import java.security.Principal;
import java.util.Map;

import it.unifi.dinfo.stlab.WebApp_PT_Support.utils.JwtUtil;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class RequestFilter implements ContainerRequestFilter {

    @Inject
    private JwtUtil jwtUtil;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    	String token = null; 
        // Estrai il token JWT dall'intestazione Authorization
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if(authorizationHeader == null || authorizationHeader.isEmpty()) {
        	requestContext.abortWith(Response
        			.status(Response.Status.UNAUTHORIZED)
        			.entity("This request is UNAUTHORIZED")
        			.type("text/plain")
        			.build());
        }


        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            System.out.println("1 il token dentro filter() è: " + token);
        }
        System.out.println("2 il token dentro filter() è: " + token);

       
        if (token != null) {
        	System.out.println("3 il token dentro filter() è: " + token);
        	// Verifica il token JWT
        	Map<String, Object> claims = jwtUtil.getAllClaimsFromToken(token);
            // Estrai l'username dal token
            String username = (String)claims.get("sub");

            // Imposta l'oggetto principale per l'autenticazione
            // Ad esempio, puoi impostare l'oggetto PersonalTrainer o l'ID dell'utente

            requestContext.setSecurityContext(new AuthenticationSecurityContext(username, requestContext));
        }
//        
    }
}
