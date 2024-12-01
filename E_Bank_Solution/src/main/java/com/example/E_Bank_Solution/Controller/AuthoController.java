package com.example.E_Bank_Solution.Controller;

import com.example.E_Bank_Solution.Model.User;
import com.example.E_Bank_Solution.Service.UserService;
import com.example.E_Bank_Solution.config.JwtAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling authentication operations such as signup and login.
 */
@RestController
public class AuthoController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticate;

    /**
     * Endpoint for user signup.
     * Accepts a User object in the request body and saves it in the database.
     *
     * @param user the User object containing user details
     * @return the saved User object
     */
    @PostMapping("/signup")
    public User createUser(@RequestBody User user) {
        System.out.println("username: " + user.getNom() + ", password: " + user.getPassword());
        return userService.saveUser(user);
    }

    /**
     * Endpoint for user login.
     * Authenticates the user and generates a JWT token upon successful login.
     *
     * @param user the User object containing login credentials (username and password)
     * @return a JWT token as a String
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        authenticate.authenticate(
                new UsernamePasswordAuthenticationToken(user.getNom(), user.getPassword())
        );
        String token = JwtAuth.generateToken(user.getUsername());
        return token;
    }
}
