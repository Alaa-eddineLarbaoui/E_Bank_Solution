package com.example.E_Bank_Solution.Service;

import com.example.E_Bank_Solution.Model.User;
import com.example.E_Bank_Solution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository; // Dépendance vers le repository des utilisateurs

    @Autowired
    BCryptPasswordEncoder passwordEncoder; // Dépendance pour encoder le mot de passe

    /**
     * Cette méthode permet de récupérer tous les utilisateurs.
     *
     * @return la liste de tous les utilisateurs dans la base de données
     */
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Récupère tous les utilisateurs depuis le repository
    }

    /**
     * Cette méthode permet de récupérer un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur à récupérer
     * @return l'utilisateur trouvé ou null si non trouvé
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null); // Recherche de l'utilisateur par son ID
    }

    /**
     * Cette méthode permet de sauvegarder un utilisateur dans la base de données.
     * Elle chiffre le mot de passe avant de le sauvegarder.
     *
     * @param user l'utilisateur à sauvegarder
     * @return l'utilisateur sauvegardé
     */
    public User saveUser(User user) {
        // Chiffre le mot de passe avant de le sauvegarder
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword); // Définit le mot de passe chiffré
        return userRepository.save(user); // Sauvegarde l'utilisateur dans le repository
    }

    /**
     * Cette méthode permet de mettre à jour un utilisateur existant.
     *
     * @param id l'identifiant de l'utilisateur à mettre à jour
     * @param user l'utilisateur avec les nouvelles informations
     * @return l'utilisateur mis à jour
     */
    public User updateUser(Long id, User user) {
        user.setUserId(id); // Associe l'ID de l'utilisateur pour la mise à jour
        return userRepository.save(user); // Sauvegarde les modifications dans la base de données
    }

    /**
     * Cette méthode permet de supprimer un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur à supprimer
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id); // Supprime l'utilisateur avec l'ID spécifié
    }
}
