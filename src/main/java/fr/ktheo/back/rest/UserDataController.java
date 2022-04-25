package fr.ktheo.back.rest;

import fr.ktheo.back.model.User;
import fr.ktheo.back.model.UserData;
import fr.ktheo.back.repository.UserDataRepository;
import fr.ktheo.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserDataById(@PathVariable("id") Long id){
        return ResponseEntity
                .ok()
                .body(userDataRepository.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity <?> updateUserData(@RequestBody UserData userData){
        userDataRepository.save(userData);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<?>deleteUserData(@PathVariable long id){
        userDataRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }




}
