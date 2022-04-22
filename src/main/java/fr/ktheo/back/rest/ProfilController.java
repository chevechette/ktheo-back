package fr.ktheo.back.rest;

import fr.ktheo.back.model.Profil;
import fr.ktheo.back.repository.ProfilRepository;
import fr.ktheo.back.rest.payload.MessageResponse;
import fr.ktheo.back.rest.payload.NewProfilRequest;
import fr.ktheo.back.service.ProfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController("restProfilController")
@RequestMapping("/api/profil")
@CrossOrigin(value = "*")
public class ProfilController {

    @Autowired
    ProfilService profilService;
    @Autowired
    ProfilRepository profilRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfilByUserId(@PathVariable("id")Long id){

        return ResponseEntity
                .ok()
                .body(profilRepository.findAllByUserId(id));
    }

    @PostMapping("/new")
    public ResponseEntity<?>setNewProfil(@Valid @RequestBody NewProfilRequest dto){
        Profil createdProfil= profilService.newProfile(dto.getAvatar(),dto.getDescription(),dto.getLanguage(),1 );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Profil registered succesfully"));
    }

}
