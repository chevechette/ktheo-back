package fr.ktheo.back.rest;

import fr.ktheo.back.model.Profil;
import fr.ktheo.back.model.User;
import fr.ktheo.back.repository.ProfilRepository;
import fr.ktheo.back.model.payload.MessageResponse;
import fr.ktheo.back.model.payload.NewProfilRequest;
import fr.ktheo.back.repository.UserRepository;
import fr.ktheo.back.service.ProfilService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Collection;

@RestController("restProfilController")
@RequestMapping("/api/profil")
@CrossOrigin(value = "*")
public class ProfilController {

    @Autowired
    ProfilService profilService;
    @Autowired
    ProfilRepository profilRepository;
    @Autowired
    UserRepository userRepository;


    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <?> getAllProfils(){
        return ResponseEntity.ok().body(profilRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfilByUserId(@PathVariable("id")Long id){

        return ResponseEntity
                .ok()
                .body(profilRepository.findAllByUser_Id(id));
    }

    @PostMapping("/new")
    public ResponseEntity<?>setNewProfil(@Valid @RequestBody NewProfilRequest dto){
        User user = userRepository.findById(dto.getUserId()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getUserId()));
        Profil createdProfil= profilService.newProfile(dto.getAvatar(),dto.getDescription(),dto.getLanguage(),1,user );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Profil registered succesfully"));
    }
}
