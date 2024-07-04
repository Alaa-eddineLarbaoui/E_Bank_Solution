package com.example.E_Bank_Solution.Controller;
import com.example.E_Bank_Solution.Model.Beneficiaire;
import com.example.E_Bank_Solution.Service.BeneficiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;






@RestController
public class BeneficiaireController {
    @Autowired
    private BeneficiaireService beneficiaireService;

    @PostMapping("/addBeneficiaire")
    public Beneficiaire addBeneficiaire(@RequestBody Beneficiaire beneficiaire){
        return beneficiaireService.addBeneficiaire(beneficiaire);
    }
    @GetMapping("/showbeneficaires")
    public ArrayList<Beneficiaire> showBeneficiaire(){
        return beneficiaireService.showAllBeneficiaire();
    }
    @DeleteMapping("/deleteBeneficiare/{id}")
    public void deleteBeneficiaire(@PathVariable Integer id){
        beneficiaireService.deleteBeneficiaire(id);
    }
    @PutMapping("/updateBeneficaire/{id}")
    public Beneficiaire updateBeneficiaire(@PathVariable Integer id,@RequestBody Beneficiaire beneficiaire){
        return beneficiaireService.updateBeneficiaire(id,beneficiaire);
    }
}
