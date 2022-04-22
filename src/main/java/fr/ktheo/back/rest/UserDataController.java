package fr.ktheo.back.rest;

import fr.ktheo.back.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;



@RestController("RestUserDataController")
@RequestMapping("/api/userdata")
@CrossOrigin(value = "*")
public class UserDataController {

    @Autowired
    UserDataRepository userDataRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserDataById(@PathVariable("id") Long id){
        return ResponseEntity
                .ok()
                .body(userDataRepository.findById(id));
    }


}
