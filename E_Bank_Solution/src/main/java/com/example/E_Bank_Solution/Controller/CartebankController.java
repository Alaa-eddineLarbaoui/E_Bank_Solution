package com.example.E_Bank_Solution.Controller;

import com.example.E_Bank_Solution.Model.Cartebank;
import com.example.E_Bank_Solution.Service.CartebankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

/**
 * Contrôleur pour gérer les cartes bancaires dans la solution E-Bank.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api/bankCard")
public class CartebankController {

    @Autowired
    private CartebankService bankCardService;

    /**
     * Récupère toutes les cartes bancaires associées à un compte donné.
     *
     * @param accountId l'identifiant du compte
     * @return une liste de toutes les cartes bancaires associées au compte
     */
    @GetMapping("/all/{accountId}")
    public List<Cartebank> getAllBankCard(@PathVariable Long accountId) {
        return bankCardService.getAllBankCards(accountId);
    }

    /**
     * Crée une nouvelle carte bancaire pour un compte spécifique.
     *
     * @param accountId l'identifiant du compte auquel associer la carte bancaire
     * @param bankCard  l'objet Cartebank contenant les détails de la nouvelle carte
     * @return l'objet Cartebank créé
     * @throws AccountNotFoundException si le compte avec l'identifiant donné n'existe pas
     */
    @PostMapping("/add/{accountId}")
    public Cartebank createBankCard(@PathVariable Long accountId, @RequestBody Cartebank bankCard)
            throws AccountNotFoundException {
        return bankCardService.addBankCard(accountId, bankCard);
    }

    /**
     * Active ou désactive une carte bancaire en fonction de l'état spécifié.
     *
     * @param bankCardId l'identifiant de la carte bancaire
     * @param activate   un booléen indiquant si la carte doit être activée (true) ou désactivée (false)
     * @return l'objet Cartebank mis à jour
     */
    @PutMapping("/activate/{bankCardId}")
    public Cartebank activateOrDeactivateBankCard(@PathVariable Long bankCardId, @RequestParam boolean activate) {
        return bankCardService.activateOrDeactivateBankCard(bankCardId, activate);
    }

    /**
     * Bloque une carte bancaire pour une raison spécifiée.
     *
     * @param bankCardId  l'identifiant de la carte bancaire à bloquer
     * @param blockRaison une chaîne indiquant la raison du blocage
     * @return l'objet Cartebank mis à jour
     */
    @PutMapping("/block/{bankCardId}")
    public Cartebank blockBankCard(@PathVariable Long bankCardId, @RequestBody String blockRaison) {
        return bankCardService.blockBankCard(bankCardId, blockRaison);
    }
}
