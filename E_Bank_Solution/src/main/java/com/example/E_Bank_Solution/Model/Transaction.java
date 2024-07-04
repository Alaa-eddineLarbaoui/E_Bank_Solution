package com.example.E_Bank_Solution.Model;

import com.example.E_Bank_Solution.Enums.transaction_for;
import com.example.E_Bank_Solution.Enums.type_transaction;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transaction")

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    private Date dateTransaction;
    private LocalDateTime heureTransaction;
    private double montant;
    private type_transaction typeTransaction;
    private String description;
    private transaction_for transactionFor;




    @ManyToOne
    @JoinColumn(name = "accountId")
    private Compte compte;


    @ManyToOne
    @JoinColumn(name = "beneficiaireId")
    private Beneficiaire  beneficiaire;


}