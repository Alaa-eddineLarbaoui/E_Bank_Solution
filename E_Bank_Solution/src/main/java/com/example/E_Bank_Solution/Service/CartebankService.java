package com.example.E_Bank_Solution.Service;

import com.example.E_Bank_Solution.Enums.card_type;
import com.example.E_Bank_Solution.Enums.status_card;
import com.example.E_Bank_Solution.Model.Cartebank;
import com.example.E_Bank_Solution.Model.Compte;
import com.example.E_Bank_Solution.Model.User;
import com.example.E_Bank_Solution.Repository.CartebankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Random;

@Service
public class CartebankService {

    @Autowired
    private CartebankRepository bankCardRepository;

    public Cartebank addBankCard( Compte account) {
        Cartebank bankCard = new Cartebank();
        bankCard.setCardNumber(generateCardNumber());
        bankCard.setExpirationDate(generateExpirationDate());
        bankCard.setCardType(card_type.CLASSIC);
        bankCard.setStatusCard(status_card.ACTIVATED);
//        bankCard.setUser(user);
        bankCard.setCompte(account);

        bankCardRepository.save(bankCard);

        return bankCard;
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
}
