package com.example.E_Bank_Solution.Repository;

import com.example.E_Bank_Solution.Model.Cartebank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartebankRepository extends JpaRepository <Cartebank, Long> {
    @Query("SELECT b FROM Cartebank b WHERE b.compte.accountId = :accountId")
    List<Cartebank> findAllByAccountId(@Param("accountId") Long accountId);
}
