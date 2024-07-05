package com.example.E_Bank_Solution.Service;

import com.example.E_Bank_Solution.Model.User;
import com.example.E_Bank_Solution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User addUser(User user){
        return userRepository.save(user);


    }
}
