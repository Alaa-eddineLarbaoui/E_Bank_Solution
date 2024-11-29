package com.example.E_Bank_Solution.Controller;

import com.example.E_Bank_Solution.Model.Compte;
import com.example.E_Bank_Solution.Service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour la gestion des comptes dans la solution E-Bank.
 */
@RestController
@RequestMapping("/account")
public class CompteController {

    @Autowired
    private CompteService comptService;

    /**
     * Récupère tous les comptes associés à un utilisateur donné.
     *
     * @param userId l'identifiant de l'utilisateur
     * @return une liste de comptes liés à l'utilisateur
     */
    @GetMapping("/getAll/{userId}")
    public List<Compte> getAllAccounts(@PathVariable Long userId) {
        return comptService.getAllAccounts(userId);
    }

    /**
     * Ajoute un nouveau compte pour un utilisateur spécifique.
     *
     * @param userId l'identifiant de l'utilisateur auquel le compte sera associé
     * @param compte l'objet Compte contenant les détails du compte
     * @return l'objet Compte créé
     */
    @PostMapping("/addAccount/{userId}")
    public Compte addAccount(@PathVariable Long userId, @RequestBody Compte compte) {
        return comptService.createAccount(userId, compte);
    }

    /**
     * Met à jour les informations d'un compte existant.
     *
     * @param accountId l'identifiant du compte à mettre à jour
     * @param account   l'objet Compte contenant les nouvelles informations
     * @return l'objet Compte mis à jour
     */
    @PutMapping("/update/{userId}")
    public Compte updateAccount(@PathVariable Long accountId, @RequestBody Compte account) {
        return comptService.updateAccount(accountId, account);
    }

    /**
     * Supprime un compte spécifié par son identifiant.
     *
     * @param accountId l'identifiant du compte à supprimer
     */
    @DeleteMapping("/delete/{userId}")
    public void deleteAccount(@PathVariable Long accountId) {
        comptService.deleteAccount(accountId);
    }

    /**
     * Ferme un compte donné avec une raison spécifiée.
     *
     * @param accountId    l'identifiant du compte à fermer
     * @param raisonClosing la raison de la fermeture du compte
     */
    @PutMapping("/close/{userId}")
    public void closeAccount(@PathVariable Long accountId, @PathVariable String raisonClosing) {
        comptService.closeAccount(accountId, raisonClosing);
    }

    /**
     * Récupère le solde d'un compte donné.
     *
     * @param accountId l'identifiant du compte
     * @return le solde du compte
     */
    @GetMapping("/balance/{userId}")
    public double getBalance(@PathVariable Long accountId) {
        return comptService.getAccountBalance(accountId);
    }
}
