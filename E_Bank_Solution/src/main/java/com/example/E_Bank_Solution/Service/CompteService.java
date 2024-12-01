package com.example.E_Bank_Solution.Service;

import com.example.E_Bank_Solution.Model.Compte;
import com.example.E_Bank_Solution.Model.User;
import com.example.E_Bank_Solution.Repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import java.util.Random;

@Service
public class CompteService {

    private final CompteRepository accountRepository;

    private final CartebankService bankCardService;

    private final UserService userservice;

    public CompteService(CompteRepository accountRepository, CartebankService bankCardService, UserService userservice) {
        this.accountRepository = accountRepository;
        this.bankCardService = bankCardService;
        this.userservice = userservice;
    }

    /**
     * Retrieves all accounts associated with a specific user.
     *
     * @param userId the ID of the user whose accounts are to be retrieved
     * @return a list of accounts associated with the user
     */
    public List<Compte> getAllAccounts(Long userId) {
        return accountRepository.findAllAccountsByUserId(userId);
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param accountId the ID of the account to be retrieved
     * @return the account with the specified ID
     * @throws IllegalArgumentException if no account is found with the given ID
     */
    public Compte getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }

    /**
     * Creates a new account for a user with a generated account number.
     *
     * @param userId the ID of the user who owns the account
     * @param account the account details to be created
     * @return the newly created account
     */
    public Compte createAccount(Long userId, Compte account) {
        User user = userservice.getUserById(userId);
        account.setUser(user);
        account.setAccount_number(generateAccountNumber());
        account.setDate_creation(new Date(System.currentTimeMillis()));
        accountRepository.save(account);

        // Optionally, a bank card could be created here
        // Cartebank bankCard = bankCardService.addBankCard(account);
        // account.addBankCard(bankCard);

        accountRepository.save(account);
        return account;
    }

    /**
     * Generates a random account number consisting of 12 digits.
     *
     * @return a randomly generated 12-digit account number
     */
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }

    /**
     * Retrieves the balance of an account by its ID.
     *
     * @param accountId the ID of the account whose balance is to be retrieved
     * @return the balance of the specified account
     * @throws IllegalArgumentException if no account is found with the given ID
     */
    public double getAccountBalance(Long accountId) {
        Compte account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return account.getSolde();
    }

    /**
     * Closes an account if the balance is zero and sets the reason for closure.
     *
     * @param accountId the ID of the account to be closed
     * @param raisonClosing the reason for closing the account
     * @throws IllegalStateException if the account balance is not zero
     * @throws IllegalArgumentException if no account is found with the given ID
     */
    public void closeAccount(Long accountId, String raisonClosing) {
        Compte account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        if (account.getSolde() != 0) {
            throw new IllegalStateException("Account balance must be zero before closing.");
        }

        account.setAccountClosed(true);
        account.setRaisonClosing(raisonClosing);
        accountRepository.save(account);
    }

    /**
     * Updates the details of an existing account.
     *
     * @param accountId the ID of the account to be updated
     * @param account the account details to be updated
     * @return the updated account
     */
    public Compte updateAccount(Long accountId, Compte account) {
        account.setAccountId(accountId);
        return accountRepository.save(account);
    }

    /**
     * Deletes an account and any associated bank cards.
     *
     * @param accountId the ID of the account to be deleted
     * @throws IllegalArgumentException if no account is found with the given ID
     */
    public void deleteAccount(Long accountId) {
        // Ensure that any associated bank cards are deleted first
        bankCardService.deletecarte(accountId);
        accountRepository.deleteById(accountId);
    }

    /**
     * Updates the balance of an existing account.
     *
     * @param id the ID of the account whose balance is to be updated
     * @param solde the new balance of the account
     * @throws RuntimeException if the account is not found
     */
    public void updateSolde(Long id, Double solde) {
        Compte compte = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        compte.setSolde(solde);
        accountRepository.save(compte);
    }
}
