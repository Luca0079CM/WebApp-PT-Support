package it.unifi.dinfo.stlab.WebApp_PT_Support.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.RequestScoped;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class JwtUtil {
	
	Path fileName = Path.of("C:\\Users\\carlo\\Desktop\\Github_repos\\WebApp-PT-Support\\WebApp-PT-Support\\secret.txt");
    private String secret = Files.readString(fileName);
    private int expiration = 3600; //1 ora
    
    public JwtUtil() throws IOException {}

    public String generateToken(String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration * 1000);

        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", subject);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    
    public Map<String, Object> getAllClaimsFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes());

        Jws<Claims> jws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

        Claims claims = jws.getBody();

        return claims;
    }

//    public boolean isTokenExpired(String token) {
//        Date expirationDate = getAllClaimsFromToken(token).getExpiration();
//        Date now = new Date();
//        return expirationDate.before(now);
//    }
    
    public boolean isTokenExpired(String token) {
    	Key key = Keys.hmacShaKeyFor(secret.getBytes());
        Date expirationDate = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        Date now = new Date();
        return expirationDate.before(now);
    }
}
