package com.example.E_Bank_Solution.Service;

import com.example.E_Bank_Solution.Enums.card_type;
import com.example.E_Bank_Solution.Enums.status_card;
import com.example.E_Bank_Solution.Exeption.BankCardNotFoundException;
import com.example.E_Bank_Solution.Model.Cartebank;
import com.example.E_Bank_Solution.Model.Compte;
import com.example.E_Bank_Solution.Repository.CartebankRepository;
import com.example.E_Bank_Solution.Repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.sql.Date;
import java.util.List;
import java.util.Random;

/**
 * Service pour la gestion des cartes bancaires dans le système E-Bank.
 * Cette classe contient des méthodes pour ajouter, activer, désactiver, bloquer, et supprimer des cartes bancaires.
 */
@Service
public class CartebankService {

    @Autowired
    private CartebankRepository bankCardRepository;

    @Autowired
    private CompteRepository accountRepository;

    /**
     * Ajoute une carte bancaire à un compte donné.
     * Cette méthode génère un numéro de carte, une date d'expiration, et attribue la carte à un compte.
     *
     * @param accountId l'identifiant du compte auquel la carte bancaire sera ajoutée
     * @param bankCard l'objet Cartebank à ajouter
     * @return la carte bancaire ajoutée
     * @throws AccountNotFoundException si le compte n'existe pas
     */
    public Cartebank addBankCard(Long accountId, Cartebank bankCard) throws AccountNotFoundException {
        // Rechercher le compte par ID
        Compte account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with id: " + accountId));

        // Générer les informations de la carte bancaire
        bankCard.setCardNumber(generateCardNumber());
        bankCard.setExpirationDate(generateExpirationDate());
        bankCard.setCardType(card_type.CLASSIC);  // Type de carte par défaut
        bankCard.setStatusCard(status_card.ACTIVATED);  // Carte activée par défaut
        bankCard.setCompte(account);

        // Sauvegarder la carte bancaire
        return bankCardRepository.save(bankCard);
    }

    /**
     * Génère un numéro de carte bancaire aléatoire de 16 chiffres.
     *
     * @return le numéro de carte bancaire généré
     */
    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));  // Générer un chiffre aléatoire
        }
        return cardNumber.toString();
    }

    /**
     * Génère une date d'expiration pour la carte bancaire, cinq ans à partir de la date actuelle.
     *
     * @return la date d'expiration générée
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + (5L * 365 * 24 * 60 * 60 * 1000));  // 5 ans en millisecondes
    }

    /**
     * Active ou désactive une carte bancaire selon la valeur de l'argument 'activate'.
     *
     * @param bankCardId l'identifiant de la carte bancaire à modifier
     * @param activate une valeur booléenne pour activer (true) ou désactiver (false) la carte
     * @return la carte bancaire mise à jour
     */
    public Cartebank activateOrDeactivateBankCard(Long bankCardId, boolean activate) {
        Cartebank bankCard = bankCardRepository.findById(bankCardId)
                .orElseThrow(BankCardNotFoundException::new);

        // Modifier le statut de la carte
        if (activate) {
            bankCard.setStatusCard(status_card.ACTIVATED);
        } else {
            bankCard.setStatusCard(status_card.INACTIVATED);
        }

        bankCardRepository.save(bankCard);  // Sauvegarder les changements
        return bankCard;
    }

    /**
     * Supprime une carte bancaire par son identifiant.
     *
     * @param carteId l'identifiant de la carte bancaire à supprimer
     */
    public void deletecarte(Long carteId) {
        bankCardRepository.deleteById(carteId);  // Supprimer la carte bancaire
    }

    /**
     * Récupère toutes les cartes bancaires associées à un compte donné.
     *
     * @param accountId l'identifiant du compte dont on veut récupérer les cartes
     * @return la liste des cartes bancaires associées à ce compte
     */
    public List<Cartebank> getAllBankCards(Long accountId) {
        return bankCardRepository.findAllByAccountId(accountId);  // Récupérer toutes les cartes du compte
    }

    /**
     * Bloque une carte bancaire avec une raison de blocage spécifiée.
     *
     * @param bankCardId l'identifiant de la carte à bloquer
     * @param blockRaison la raison du blocage de la carte
     * @return la carte bancaire mise à jour avec le statut "BLOQUÉ"
     * @throws IllegalStateException si la carte est déjà bloquée ou si la raison du blocage est vide
     */
    public Cartebank blockBankCard(Long bankCardId, String blockRaison) {
        Cartebank bankCard = bankCardRepository.findById(bankCardId)
                .orElseThrow(BankCardNotFoundException::new);

        // Vérifier que la raison du blocage n'est pas vide
        if (blockRaison == null || blockRaison.trim().isEmpty()) {
            throw new IllegalStateException("Block reason cannot be empty.");
        }

        // Bloquer la carte si elle n'est pas déjà bloquée
        if (bankCard.getStatusCard() != status_card.BLOCKED) {
            bankCard.setStatusCard(status_card.BLOCKED);
            bankCard.setReason_of_blockage(blockRaison);
            return bankCardRepository.save(bankCard);
        } else {
            throw new IllegalStateException("The bank card is already blocked.");
        }
    }
}
