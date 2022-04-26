package fr.ktheo.back.rest;

import fr.ktheo.back.model.Address;
import fr.ktheo.back.model.User;
import fr.ktheo.back.model.payload.AddressModify;
import fr.ktheo.back.model.payload.MessageResponse;
import fr.ktheo.back.model.payload.newAddressRequest;
import fr.ktheo.back.repository.AddressRepository;
import fr.ktheo.back.repository.UserRepository;
import fr.ktheo.back.service.AddressService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class AddressController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AddressService addressService;


    @PostMapping("/address")
    public ResponseEntity<?>addNewAddress(@Valid @RequestBody newAddressRequest dto){
        User user = userRepository.findById(dto.getUserId()).orElseThrow(()->new EntityNotFoundException("id not found :"+dto.getUserId()));
        Address address = new Address(dto.getTown(),dto.getStreetNumber(),dto.getStreetName(),dto.getPostalCode(),user);
        addressRepository.save(address);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Address registered succesfully"));
    }
    @PutMapping("/address")
    public ResponseEntity <?> updateAddress(@RequestBody AddressModify address){
        addressService.updateAddress(address);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @GetMapping("/{id}/addresses")
    public ResponseEntity<?> getAddressesFromUser(@PathVariable long id){
        List<Address> addresses = addressRepository.findAllByUser_Id(id).orElseThrow(()->new EntityNotFoundException("addresses not found :"));
        return ResponseEntity.ok().body(addresses);
    }
    @GetMapping("/address/{id}")
    public ResponseEntity<?>getOneAddress(@PathVariable long id){
        return ResponseEntity.ok().body(addressRepository.findById(id));
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<?>deleteAddress(@PathVariable long id){
        System.out.println("id in delete mapping"+id);
        addressRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
