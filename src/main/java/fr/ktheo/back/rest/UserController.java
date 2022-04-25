package fr.ktheo.back.rest;

import fr.ktheo.back.model.User;
import fr.ktheo.back.repository.ProfilRepository;
import fr.ktheo.back.repository.UserDataRepository;
import fr.ktheo.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("restUserController")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfilRepository profilRepository;

    @Autowired
    UserDataRepository userDataRepository;

    @GetMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok().body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable long id){
        return ResponseEntity.ok().body(userRepository.findById(id));
    }

    @GetMapping("/{id}/profiles")
    public ResponseEntity<?> getAllProfilesFromUser(@PathVariable long id){
        return ResponseEntity.ok().body(profilRepository.findAllByUser_Id(id));
    }

    @GetMapping("/{id}/userdata")
    public ResponseEntity<?> getUserDataFromUser(@PathVariable long id){
        User user = userRepository.findById(id).orElseThrow();
        return ResponseEntity.ok().body(user.getUserData());
    }
    @PutMapping("/{id}")
    public ResponseEntity <?> upDateUser(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteUser(@PathVariable long id){
        userRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
