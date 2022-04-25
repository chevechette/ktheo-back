package fr.ktheo.back.rest;

import fr.ktheo.back.model.User;
import fr.ktheo.back.repository.AddressRepository;
import fr.ktheo.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;

    @GetMapping("/{id}/addresses")
    public ResponseEntity<?> getAddressesFromUser(@PathVariable long id){
        User user = userRepository.findById(id).orElseThrow();
        return ResponseEntity.ok().body(user.getAddresses());
    }
    @GetMapping("/address/{id}")
    public ResponseEntity<?>getOneAddress(@PathVariable long id){
        return ResponseEntity.ok().body(addressRepository.findById(id));
    }
    @DeleteMapping("/address/{id}")
    public ResponseEntity<?>deleteAddress(@PathVariable long id){
        addressRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
