package com.example.E_Bank_Solution.Service;


import com.example.E_Bank_Solution.Model.Beneficiaire;
import com.example.E_Bank_Solution.Repository.BeneficiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BeneficiaireService {
    @Autowired
    BeneficiaireRepository beneficiaireRepository;

    public Beneficiaire addBeneficiaire(Beneficiaire beneficiaire){
        return beneficiaireRepository.save(beneficiaire);
    }
    public ArrayList<Beneficiaire> showAllBeneficiaire(){
        return (ArrayList<Beneficiaire>) beneficiaireRepository.findAll();
    }
    public void deleteBeneficiaire(Integer id){
        beneficiaireRepository.deleteById(id);
    }
    public Beneficiaire recupaireById(Integer id){
        return beneficiaireRepository.findById(id).orElseThrow();
    }

    public Beneficiaire updateBeneficiaire(Integer id,Beneficiaire beneficiaire){
        Beneficiaire beneficiaire1= recupaireById(id);
        beneficiaire1.setNameOfbeneficaire(beneficiaire.getNameOfbeneficaire());
        beneficiaire1.setTypeOfbank(beneficiaire.getTypeOfbank());
        beneficiaire1.setAccount_number(beneficiaire.getAccount_number());
        return beneficiaireRepository.save(beneficiaire1);
    }
}
