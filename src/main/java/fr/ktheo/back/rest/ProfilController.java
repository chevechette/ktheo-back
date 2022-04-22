package fr.ktheo.back.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("restProfilController")
@RequestMapping("/api/profil")
@CrossOrigin(value = "*")
public class ProfilController {
}
