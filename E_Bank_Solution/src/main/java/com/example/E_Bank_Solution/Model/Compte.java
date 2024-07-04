package com.example.E_Bank_Solution.Model;

import com.example.E_Bank_Solution.Enums.accountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Setter
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "compte")
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;
    private String account_number;
    private accountType account_type;
    private Double solde ;
    private Date date_creation;


    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "compte")
    @JsonIgnore
    private List<Cartebank> carteBancaires;


    @OneToMany (mappedBy = "compte")
    @JsonIgnore
    private List<Beneficiaire> benificiaires;


    @OneToMany (mappedBy = "compte")
    @JsonIgnore
    private List<Transaction> transactions;


}