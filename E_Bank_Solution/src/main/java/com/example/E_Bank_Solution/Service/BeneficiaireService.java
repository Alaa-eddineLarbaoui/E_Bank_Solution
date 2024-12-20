package com.example.E_Bank_Solution.Service;

import com.example.E_Bank_Solution.Model.Beneficiaire;
import com.example.E_Bank_Solution.Repository.BeneficiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Service pour la gestion des bénéficiaires dans le système E-Bank.
 * Cette classe contient les méthodes nécessaires pour interagir avec la base de données
 * concernant les bénéficiaires : ajout, suppression, mise à jour et récupération des bénéficiaires.
 */
@Service
public class BeneficiaireService {

    final
    BeneficiaireRepository beneficiaireRepository;

    public BeneficiaireService(BeneficiaireRepository beneficiaireRepository) {
        this.beneficiaireRepository = beneficiaireRepository;
    }

    /**
     * Ajoute un nouveau bénéficiaire dans la base de données.
     *
     * @param beneficiaire l'objet bénéficiaire à ajouter
     * @return le bénéficiaire ajouté
     */
    public Beneficiaire addBeneficiaire(Beneficiaire beneficiaire){
        return beneficiaireRepository.save(beneficiaire);
    }

    /**
     * Récupère tous les bénéficiaires enregistrés dans la base de données.
     *
     * @return une liste de tous les bénéficiaires
     */
    public ArrayList<Beneficiaire> showAllBeneficiaire(){
        return (ArrayList<Beneficiaire>) beneficiaireRepository.findAll();
    }

    /**
     * Supprime un bénéficiaire en fonction de son identifiant.
     *
     * @param id l'identifiant du bénéficiaire à supprimer
     */
    public void deleteBeneficiaire(Integer id){
        beneficiaireRepository.deleteById(id);
    }

    /**
     * Récupère un bénéficiaire par son identifiant.
     * Si le bénéficiaire n'existe pas, une exception sera lancée.
     *
     * @param id l'identifiant du bénéficiaire à récupérer
     * @return le bénéficiaire correspondant à l'identifiant donné
     * @throwsle bénéficiaire n'existe pas
     */
    public Beneficiaire recupaireById(Integer id){
        return beneficiaireRepository.findById(id).orElseThrow();
    }

    /**
     * Met à jour les informations d'un bénéficiaire existant.
     * L'objet bénéficiaire fourni met à jour les informations du bénéficiaire avec l'identifiant spécifié.
     *
     * @param id l'identifiant du bénéficiaire à mettre à jour
     * @param beneficiaire l'objet contenant les nouvelles informations du bénéficiaire
     * @return le bénéficiaire mis à jour
     */
    public Beneficiaire updateBeneficiaire(Integer id, Beneficiaire beneficiaire){
        Beneficiaire beneficiaire1 = recupaireById(id);
        beneficiaire1.setNameOfbeneficaire(beneficiaire.getNameOfbeneficaire());
        beneficiaire1.setTypeOfbank(beneficiaire.getTypeOfbank());
        beneficiaire1.setAccount_number(beneficiaire.getAccount_number());
        return beneficiaireRepository.save(beneficiaire1);
    }
}
