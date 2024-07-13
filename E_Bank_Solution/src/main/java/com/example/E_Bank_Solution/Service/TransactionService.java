package com.example.E_Bank_Solution.Service;

import com.example.E_Bank_Solution.Model.Compte;
import com.example.E_Bank_Solution.Model.Transaction;
import com.example.E_Bank_Solution.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {

@Autowired
private CompteService compteSrv;
@Autowired
private TransactionRepository transactionRepository;


    public String addTransaction(Long idCompte, Transaction transaction) {

        Compte compte = compteSrv.getAccountById(idCompte);


        if (transaction.getMontant() == 0) {
            return "Impossible de transférer ou poser ce montant";
        }

        if (("Transfert externe".equals(transaction.getTypeTransaction()) || "Transfert interne".equals(transaction.getTypeTransaction())) &&
                transaction.getMontant() > compte.getSolde()) {

            return "Impossible de transférer ce montant";
        }

        transaction.setDateTransaction(LocalDate.now());
        transaction.setHeureTransaction(LocalTime.now());
        transaction.setCompte(compte);

        if ("Transfert externe".equals(transaction.getTypeTransaction())) {
            transactionRepository.save(transaction);
            Double newSolde = compte.getSolde() - transaction.getMontant();
            compteSrv.updateSolde(idCompte, newSolde);
            return transaction.getMontant() + "Dh transféré avec succès à " ;

        }

        else if ("Transfert interne".equals(transaction.getTypeTransaction())) {
//            transaction.setbank("Ebank");
            transactionRepository.save(transaction);
            Double newSolde = compte.getSolde() - transaction.getMontant();
            compteSrv.updateSolde(idCompte, newSolde);
            return transaction.getMontant() + "Dh transféré avec succès à " ;
        }

        else {
//            transaction.setBanque("Ebank");
            transactionRepository.save(transaction);
            Double newSolde = compte.getSolde() + transaction.getMontant();
            compteSrv.updateSolde(idCompte, newSolde);
            return "Débit ajouté avec succès";
        }
    }

//    public List<Transaction> showTransactionByIdCompte(Integer idCompte){
//
//        return transactionRepository.findAllByCompte_IdCompte(idCompte);
//
//    }

}


