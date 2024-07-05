package com.example.E_Bank_Solution.Service;


import com.example.E_Bank_Solution.Model.Cartebank;
import com.example.E_Bank_Solution.Model.Compte;
import com.example.E_Bank_Solution.Model.User;
import com.example.E_Bank_Solution.Repository.CompteRepository;

import com.example.E_Bank_Solution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import java.util.Random;

@Service
public class CompteService {

    @Autowired
    private CompteRepository accountRepository;

    @Autowired
    private CartebankService bankCardService;

   @Autowired

    public List<Compte> getAllAccounts(Long userId) {
        return accountRepository.findAllAccountsByUserId(userId);
    }

    public Compte createAccount(Long userId, Compte account) {
      //  User user =getUserById(userId);

        //account.setUser(user);
        account.setAccount_number(generateAccountNumber());
        account.setDate_creation(new Date(System.currentTimeMillis()));

        accountRepository.save(account);

        Cartebank bankCard = bankCardService.addBankCard( account);

//        account.addBankCard(bankCard);

        accountRepository.save(account);

        return account;
    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }
//
//    public double getAccountBalance(Long accountId) {
//        Account account = accountRepository.findById(accountId)
//                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
//        return account.getBalance();
//    }
//
//    public void closeAccount(Long accountId, String raisonClosing) {
//        Account account = accountRepository.findById(accountId)
//                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
//
//        if (account.getBalance() != 0) {
//            throw new IllegalStateException("Account balance must be zero before closing.");
//        }
//
//        account.setAccountClosed(true);
//        account.setRaisonClosing(raisonClosing);
//
//        accountRepository.save(account);
//    }
//
//    public Account updateAccount(Long accountId, Account account) {
//        account.setId(accountId);
//        return accountRepository.save(account);
//    }

    public  void deleteAccount(long accountId) {
        accountRepository.deleteById(accountId);
    }
}
