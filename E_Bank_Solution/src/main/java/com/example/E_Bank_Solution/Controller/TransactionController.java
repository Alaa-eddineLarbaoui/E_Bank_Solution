package com.example.E_Bank_Solution.Controller;

import com.example.E_Bank_Solution.Model.Transaction;
import com.example.E_Bank_Solution.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class TransactionController {
@Autowired
private TransactionService transactionService;
    @PostMapping("/transaction/{idCompte}")
    public String addTransaction(@PathVariable Long idCompte, @RequestBody Transaction transaction){
        return transactionService.addTransaction(idCompte,transaction);
    }

}
