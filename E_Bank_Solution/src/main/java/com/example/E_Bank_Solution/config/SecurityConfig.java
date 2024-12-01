package com.example.E_Bank_Solution.config;

import com.example.E_Bank_Solution.Service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class for Spring Security.
 * Defines the security settings for the application, including authentication, authorization, and password encoding.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailService userDetailService;

    /**
     * Constructor for SecurityConfig.
     * Injects the custom UserDetailService for user authentication.
     *
     * @param userDetailService the service used to load user details
     */
    public SecurityConfig(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    /**
     * Bean definition for password encoding using BCrypt.
     * Ensures secure password storage and validation.
     *
     * @return an instance of BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain for the application.
     * - Disables CSRF protection.
     * - Allows public access to specific endpoints (e.g., /signup, /login).
     * - Requires authentication for all other requests.
     * - Disables the default login form provided by Spring Security.
     * - Adds a custom JWT authorization filter.
     *
     * @param http the HttpSecurity object to configure
     * @return the configured SecurityFilterChain
     * @throws Exception if there is an error during configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection as it is not needed for APIs
                .csrf(csrf -> csrf.disable())

                // Configure authorization rules
                .authorizeHttpRequests(expressionInterceptUrlRegistry ->
                        expressionInterceptUrlRegistry
                                .requestMatchers("/signup", "/login").permitAll() // Public access
                                .anyRequest().authenticated() // Require authentication for other requests
                )

                // Disable the default login form
                .formLogin(formLogin -> formLogin.disable());

        // Add a custom JWT authorization filter
        http.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * Bean definition for the AuthenticationManager.
     * Configures authentication using the custom UserDetailService and BCrypt password encoder.
     *
     * @param http the HttpSecurity object to configure
     * @return an instance of AuthenticationManager
     * @throws Exception if there is an error during configuration
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        System.out.println("/////////// AuthenticationManager initialization");
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailService) // Use the custom user details service
                .passwordEncoder(passwordEncoder()); // Use BCrypt for password encoding

        return authenticationManagerBuilder.build();
    }
}
