package com.example.E_Bank_Solution.Controller;


import com.example.E_Bank_Solution.Model.Compte;
import com.example.E_Bank_Solution.Service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/account")
public class CompteController {

    @Autowired
    private CompteService comptService;


    @GetMapping("/getAll/{userId}")
    public List<Compte> getAllAccounts(@PathVariable Long userId) {
        return comptService.getAllAccounts(userId);
    }

    @PostMapping("/addAccount/{userId}")
    public Compte addAccount(@PathVariable Long userId, @RequestBody Compte compte) {
        return comptService.createAccount(userId, compte);
    }

    @PutMapping("/update/{userId}")
    public Compte updateAccount(@PathVariable Long accountId, @RequestBody Compte account) {
        return comptService.updateAccount(accountId, account);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteAccount(@PathVariable Long accountId ) {

        comptService.deleteAccount(accountId);
    }

    @PutMapping("/close/{userId}")
    public void closeAccount(@PathVariable Long accountId, @PathVariable String raisonClosing) {
        comptService.closeAccount(accountId, raisonClosing);
    }

    @GetMapping("/balance/{userId}")
    public double  getBalance(@PathVariable Long accountId){
        return comptService.getAccountBalance(accountId);
    }

}

