package com.example.E_Bank_Solution.Model;

import com.example.E_Bank_Solution.Enums.card_type;
import com.example.E_Bank_Solution.Enums.status_card;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity (name = "Cartebank")
public class Cartebank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carteId ;
    private String cardNumber;
    private Date expirationDate ;

    @Enumerated (EnumType.STRING)
    private card_type cardType;

    @Enumerated (EnumType.STRING)
    private status_card statusCard;

    private String reason_of_blockage ;

    @ManyToOne
    @JoinColumn(name = "accountId")
    private Compte compte;






//    @OneToMany(mappedBy = "carteBancaire")
//    private List<Blockage> blockageList;

}