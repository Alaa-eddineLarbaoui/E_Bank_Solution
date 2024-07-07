package com.example.E_Bank_Solution.Repository;

import com.example.E_Bank_Solution.Model.Cartebank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartebankRepository extends JpaRepository <Cartebank, Long> {
}
