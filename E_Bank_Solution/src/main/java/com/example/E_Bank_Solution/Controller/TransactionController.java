package com.example.E_Bank_Solution.Controller;

import com.example.E_Bank_Solution.Model.Transaction;
import com.example.E_Bank_Solution.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contrôleur pour la gestion des transactions dans la solution E-Bank.
 */
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Effectue une transaction sur un compte spécifié.
     *
     * @param idCompte   l'identifiant du compte sur lequel la transaction est effectuée
     * @param transaction l'objet Transaction contenant les détails de la transaction
     * @return un message indiquant le résultat de l'opération
     */
    @PostMapping("/transaction/{idCompte}")
    public String addTransaction(@PathVariable Long idCompte, @RequestBody Transaction transaction) {
        return transactionService.addTransaction(idCompte, transaction);
    }
}
