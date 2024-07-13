package com.example.E_Bank_Solution.Repository;

import com.example.E_Bank_Solution.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
   User findUserByNom(String username);


}

