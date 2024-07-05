package com.example.E_Bank_Solution.Repository;

import com.example.E_Bank_Solution.Model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository <Compte,Integer> {
}
