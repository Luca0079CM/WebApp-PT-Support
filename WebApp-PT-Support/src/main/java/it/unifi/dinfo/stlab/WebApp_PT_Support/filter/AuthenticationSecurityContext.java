package it.unifi.dinfo.stlab.WebApp_PT_Support.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;

public class AuthenticationSecurityContext implements SecurityContext {

    private String principalUsername;
    private ContainerRequestContext requestContext;

    public AuthenticationSecurityContext(String principalUsername, ContainerRequestContext requestContext) {
        this.principalUsername = principalUsername;
        this.requestContext = requestContext;
    }

    @Override
    public Principal getUserPrincipal() {
        return new Principal() {
        	@Override
        	public String getName() {
        		return principalUsername;
        	}
        };
    }

    @Override
    public boolean isUserInRole(String role) {
        // Implementa la logica per verificare i ruoli dell'utente, se necessario
        return false;
    }

    @Override
    public boolean isSecure() {
        return requestContext.getSecurityContext().isSecure();
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
