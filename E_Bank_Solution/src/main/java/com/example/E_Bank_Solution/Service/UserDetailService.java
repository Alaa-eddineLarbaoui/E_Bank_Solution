package com.example.E_Bank_Solution.Service;

import com.example.E_Bank_Solution.Model.User;
import com.example.E_Bank_Solution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByNom(username);
        System.out.println(user.getUsername()+"///:::22IMPL/"+user.getPassword());
        return user.builder().nom(user.getUsername()).password(user.getPassword()).build();
    }
}
