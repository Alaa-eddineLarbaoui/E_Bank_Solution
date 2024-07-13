package com.example.E_Bank_Solution.Service;

import com.example.E_Bank_Solution.Enums.card_type;
import com.example.E_Bank_Solution.Enums.status_card;
import com.example.E_Bank_Solution.Exeption.BankCardNotFoundException;
import com.example.E_Bank_Solution.Model.Cartebank;
import com.example.E_Bank_Solution.Model.Compte;
import com.example.E_Bank_Solution.Model.User;
import com.example.E_Bank_Solution.Repository.CartebankRepository;
import com.example.E_Bank_Solution.Repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.nio.channels.AcceptPendingException;
import java.sql.Date;
import java.util.List;
import java.util.Random;

@Service
public class CartebankService {

    @Autowired
    private CartebankRepository bankCardRepository;
    @Autowired
    private CompteRepository accountRepository;

    public Cartebank addBankCard(Long accountId, Cartebank bankCard) throws AccountNotFoundException {
        // Rechercher le compte par ID
        Compte account = accountRepository.findById(accountId).orElseThrow(()->new AccountNotFoundException("Account not found with id: " + accountId));


        // Générer les informations de la carte bancaire
        bankCard.setCardNumber(generateCardNumber());
        bankCard.setExpirationDate(generateExpirationDate());
        bankCard.setCardType(card_type.CLASSIC);
        bankCard.setStatusCard(status_card.ACTIVATED);
        bankCard.setCompte(account);

        // Sauvegarder la carte bancaire
        return bankCardRepository.save(bankCard);
    }


    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + (5L * 365 * 24 * 60 * 60 * 1000));
    }
    public Cartebank activateOrDeactivateBankCard(Long bankCardId, boolean activate) {
        Cartebank bankCard = bankCardRepository.findById(bankCardId)
                .orElseThrow(BankCardNotFoundException::new);

        if (activate) {
            bankCard.setStatusCard(status_card.ACTIVATED);
        } else {
            bankCard.setStatusCard(status_card.INACTIVATED);
        }

        bankCardRepository.save(bankCard);
        return bankCard;
    }

    public void deletecarte(Long carteId) {bankCardRepository.deleteById(carteId);
    }


    public List<Cartebank> getAllBankCards(Long accountId) {
        return bankCardRepository.findAllByAccountId(accountId);
    }


    public Cartebank blockBankCard(Long bankCardId, String blockRaison) {
        Cartebank bankCard = bankCardRepository.findById(bankCardId)
                .orElseThrow(BankCardNotFoundException::new);

        if (blockRaison == null || blockRaison.trim().isEmpty()) {
            throw new IllegalStateException("Block reason cannot be empty.");
        }

        if (bankCard.getStatusCard() != status_card.BLOCKED) {
            bankCard.setStatusCard(status_card.BLOCKED);
            bankCard.setReason_of_blockage(blockRaison);
            return bankCardRepository.save(bankCard);
        } else {
            throw new IllegalStateException("The bank card is already blocked.");
        }
    }


}
