package com.example.E_Bank_Solution.Model;
import com.example.E_Bank_Solution.Enums.TypeBank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity (name = "Beneficiaire")
public class Beneficiaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer beneficiaireId ;
    private String nameOfbeneficaire ;
    private String account_number ;

    @Enumerated (EnumType.STRING)
    private TypeBank typeOfbank ;


    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "accountId")
    private Compte compte;
    @OneToMany (mappedBy = "beneficiaire")
    @JsonIgnore
    private List<Transaction> transactions;


}