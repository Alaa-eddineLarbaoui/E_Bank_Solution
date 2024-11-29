package com.example.E_Bank_Solution.Controller;

import com.example.E_Bank_Solution.Model.User;
import com.example.E_Bank_Solution.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Contrôleur pour la gestion des utilisateurs dans la solution E-Bank.
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Récupère la liste de tous les utilisateurs.
     *
     * @return une liste d'utilisateurs
     */
    @GetMapping("/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Ajoute un nouvel utilisateur.
     *
     * @param user l'utilisateur à ajouter
     */
    @PostMapping("/addUser")
    public void addUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    /**
     * Supprime un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur à supprimer
     */
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param id   l'identifiant de l'utilisateur à mettre à jour
     * @param user l'objet User contenant les nouvelles informations
     */
    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
    }
}
