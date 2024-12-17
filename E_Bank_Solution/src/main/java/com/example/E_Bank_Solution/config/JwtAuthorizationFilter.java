package com.example.E_Bank_Solution.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtAuthorizationFilter is responsible for authorizing requests by validating JWT tokens.
 * Extends {@link OncePerRequestFilter} to ensure the filter is applied once per request.
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    /**
     * Processes incoming HTTP requests to validate JWT tokens.
     * If a valid token is found in the "Authorization" header, the user's authentication
     * context is updated with the extracted claims.
     *
     * @param request     the incoming HTTP request
     * @param response    the outgoing HTTP response
     * @param filterChain the chain of filters to process the request
     * @throws ServletException if an error occurs during filtering
     * @throws IOException      if an I/O error occurs during request processing
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Retrieve the "Authorization" header from the request.
        String authorizationToken = request.getHeader("Authorization");

        // Check if the token exists and starts with "Bearer ".
        if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
            try {
                // Extract the JWT token by removing the "Bearer " prefix.
                String jwt = authorizationToken.substring(7);

                // Parse the JWT token and extract its claims using the secret key.
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtAuth.SECRET_KEY)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                // Extract the username (subject) from the claims.
                String username = claims.getSubject();

                // Create an authentication token and update the security context.
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        username, null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Continue processing the request through the filter chain.
                filterChain.doFilter(request, response);

            } catch (Exception e) {
                // Handle exceptions such as token expiration or invalid tokens.
                response.setHeader("error message", e.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
        } else {
            // If no valid token is found, continue processing the request.
            filterChain.doFilter(request, response);
        }
    }
}
