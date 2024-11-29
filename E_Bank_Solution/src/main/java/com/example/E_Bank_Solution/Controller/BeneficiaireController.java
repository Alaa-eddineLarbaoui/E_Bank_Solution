package com.example.E_Bank_Solution.Controller;

import com.example.E_Bank_Solution.Model.Beneficiaire;
import com.example.E_Bank_Solution.Service.BeneficiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

/**
 * Contrôleur pour gérer les bénéficiaires dans la solution E-Bank.
 */
@RestController
public class BeneficiaireController {

    @Autowired
    private BeneficiaireService beneficiaireService;

    /**
     * Ajoute un nouveau bénéficiaire au système.
     *
     * @param beneficiaire l'objet Beneficiaire à ajouter
     * @return l'objet Beneficiaire ajouté
     */
    @PostMapping("/addBeneficiaire")
    public Beneficiaire addBeneficiaire(@RequestBody Beneficiaire beneficiaire) {
        return beneficiaireService.addBeneficiaire(beneficiaire);
    }

    /**
     * Récupère la liste de tous les bénéficiaires.
     *
     * @return une liste contenant tous les objets Beneficiaire
     */
    @GetMapping("/showbeneficaires")
    public ArrayList<Beneficiaire> showBeneficiaire() {
        return beneficiaireService.showAllBeneficiaire();
    }

    /**
     * Supprime un bénéficiaire à partir de son ID.
     *
     * @param id l'identifiant du Beneficiaire à supprimer
     */
    @DeleteMapping("/deleteBeneficiare/{id}")
    public void deleteBeneficiaire(@PathVariable Integer id) {
        beneficiaireService.deleteBeneficiaire(id);
    }

    /**
     * Met à jour les informations d'un bénéficiaire existant.
     *
     * @param id           l'identifiant du Beneficiaire à mettre à jour
     * @param beneficiaire l'objet Beneficiaire contenant les nouvelles données
     * @return l'objet Beneficiaire mis à jour
     */
    @PutMapping("/updateBeneficaire/{id}")
    public Beneficiaire updateBeneficiaire(@PathVariable Integer id, @RequestBody Beneficiaire beneficiaire) {
        return beneficiaireService.updateBeneficiaire(id, beneficiaire);
    }
}
