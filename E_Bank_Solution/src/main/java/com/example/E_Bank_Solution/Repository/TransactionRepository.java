package com.example.E_Bank_Solution.Repository;


import com.example.E_Bank_Solution.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
