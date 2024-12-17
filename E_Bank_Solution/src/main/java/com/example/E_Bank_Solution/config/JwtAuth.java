package com.example.E_Bank_Solution.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

/**
 * JwtAuth is a service for handling JWT-related operations such as token generation.
 */
@Service
public class JwtAuth {

    /**
     * Secret key used for signing JWT tokens.
     * It is generated using the {@link SignatureAlgorithm#HS256} algorithm.
     */
    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Generates a JWT token for a given username.
     * The token includes the subject (username), the issued date, and an expiration date set to 24 hours.
     *
     * @param username the username for which the token is generated
     * @return a signed JWT token as a string
     */
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Set the subject (typically the username).
                .setIssuedAt(new Date(System.currentTimeMillis())) // Set the issue date to the current time.
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // Set the expiration date to 24 hours from now.
                .signWith(SECRET_KEY) // Sign the token with the secret key.
                .compact(); // Compact and return the JWT as a string.
    }
}
