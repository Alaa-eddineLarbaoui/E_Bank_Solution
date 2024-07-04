package com.example.E_Bank_Solution.Model;
import com.example.E_Bank_Solution.Enums.TypeBank;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private TypeBank TypeOfbank ;


    @ManyToOne
    @JoinColumn(name = "accountId")
    private Compte compte;

}