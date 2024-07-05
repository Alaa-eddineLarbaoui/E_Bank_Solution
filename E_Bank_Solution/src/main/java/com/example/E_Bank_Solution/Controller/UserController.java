package com.example.E_Bank_Solution.Controller;

import com.example.E_Bank_Solution.Model.Beneficiaire;
import com.example.E_Bank_Solution.Model.User;
import com.example.E_Bank_Solution.Service.CompteService;
import com.example.E_Bank_Solution.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {


    @Autowired
    private UserService userservice;

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user1){
        return userservice.addUser(user1);
    }

}
