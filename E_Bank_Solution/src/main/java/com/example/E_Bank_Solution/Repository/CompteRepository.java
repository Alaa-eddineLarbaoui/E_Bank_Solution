package com.example.E_Bank_Solution.Repository;

import com.example.E_Bank_Solution.Model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    @Query("SELECT a FROM compte a WHERE a.user.userId = :userId")
    List<Compte> findAllAccountsByUserId(@Param("userId") Long userId);
}
