package fr.ktheo.back.service;

import fr.ktheo.back.model.*;
import fr.ktheo.back.repository.ProfilRepository;
import fr.ktheo.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
public class ProfilService {

@Autowired
    ProfilRepository profilRepository;
@Autowired
    UserRepository userRepository;


    @Transactional
    public Profil newProfile(String avatar, String description, String language, int views,User user) {
        Profil profil = new Profil(avatar,description,language,views,user);
        profilRepository.save(profil);
       return profil;
    }
}
