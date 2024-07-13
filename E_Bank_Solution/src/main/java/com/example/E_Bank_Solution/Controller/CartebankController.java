package com.example.E_Bank_Solution.Controller;


import com.example.E_Bank_Solution.Model.Cartebank;
import com.example.E_Bank_Solution.Service.CartebankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bankCard")
public class CartebankController {
    @Autowired
    private CartebankService bankCardService;

    @GetMapping("/all/{accountId}")
    public List<Cartebank> getAllBankCard(@PathVariable Long accountId){
        return bankCardService.getAllBankCards(accountId);
    }

    @PostMapping("/add/{accountId}")
    public Cartebank createBankCard(@PathVariable Long accountId, @RequestBody Cartebank bankCard) throws AccountNotFoundException {
        return bankCardService.addBankCard(accountId, bankCard);
    }

    @PutMapping("/activate/{bankCardId}")
    public Cartebank activateOrDeactivateBankCard(@PathVariable Long bankCardId, @RequestParam boolean activate) {
        return bankCardService.activateOrDeactivateBankCard(bankCardId, activate);
    }

    @PutMapping("/block/{bankCardId}")
    public Cartebank blockBankCard(@PathVariable Long bankCardId, @RequestBody String blockRaison) {
        return bankCardService.blockBankCard(bankCardId, blockRaison);
    }
}
