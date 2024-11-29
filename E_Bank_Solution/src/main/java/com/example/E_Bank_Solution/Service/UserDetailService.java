package com.example.E_Bank_Solution.Service;

import com.example.E_Bank_Solution.Model.User;
import com.example.E_Bank_Solution.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * Cette méthode charge les détails de l'utilisateur en fonction de son nom d'utilisateur.
     * Elle est utilisée par le mécanisme de sécurité de Spring pour l'authentification.
     *
     * @param username le nom d'utilisateur de l'utilisateur à charger
     * @return un objet UserDetails représentant l'utilisateur
     * @throws UsernameNotFoundException si l'utilisateur avec ce nom d'utilisateur n'est pas trouvé
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Recherche de l'utilisateur dans la base de données par son nom d'utilisateur
        User user = userRepository.findUserByNom(username);

        // Si l'utilisateur n'est pas trouvé, une exception est levée
        if (user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
        }

        // Affichage des informations de l'utilisateur (nom d'utilisateur et mot de passe)
        System.out.println(user.getUsername() + "///:::22IMPL/" + user.getPassword());

        // Retourne un objet UserDetails construit avec les informations de l'utilisateur
        return user.builder()
                .nom(user.getUsername()) // Nom d'utilisateur
                .password(user.getPassword()) // Mot de passe
                .build();
    }
}
