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

@RestController
public class AuthoController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticate;

    @PostMapping("/signup")
    public User createUser(@RequestBody User user){
        System.out.println("username :  " +  user.getNom() + "password : " + user.getPassword() );
        return userService.saveUser(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody User user){
        Authentication authentication = authenticate.authenticate(
                new UsernamePasswordAuthenticationToken(user.getNom() , user.getPassword())
        );
        String token = JwtAuth.generateToken(user.getUsername());
        return token;

    }
}
