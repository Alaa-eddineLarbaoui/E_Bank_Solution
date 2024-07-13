package com.example.E_Bank_Solution.Controller;

import com.example.E_Bank_Solution.Model.User;
import com.example.E_Bank_Solution.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthoController {
    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public User createUser(@RequestBody User user){
        System.out.println("username :  " +  user.getNom() + "password : " + user.getPassword() );
        return userService.saveUser(user);
    }
}
