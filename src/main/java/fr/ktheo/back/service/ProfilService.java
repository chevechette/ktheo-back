package fr.ktheo.back.service;

import fr.ktheo.back.model.*;
import fr.ktheo.back.repository.ProfilRepository;
import fr.ktheo.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
public class ProfilService {

@Autowired
    ProfilRepository profilRepository;
@Autowired
    UserRepository userRepository;


    @Transactional
    public Profil newProfile(String avatar, String description, String language, int views) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken){
            System.out.println("AnonymousAuthentification");
        }
        User user = new User();

        Profil profil = new Profil(avatar,description,language,views,user);
        profilRepository.save(profil);
       return profil;
    }
}
