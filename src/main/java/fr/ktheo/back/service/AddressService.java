package fr.ktheo.back.service;

import fr.ktheo.back.model.Address;
import fr.ktheo.back.model.User;
import fr.ktheo.back.model.payload.AddressModify;
import fr.ktheo.back.repository.AddressRepository;
import fr.ktheo.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Address updateAddress(AddressModify addressModify){
        Address address = addressRepository.findById(addressModify.getId()).orElseThrow(() -> new UsernameNotFoundException("addressId :  " + addressModify.getUserId() + " not found"));
        address.setPostalCode(addressModify.getPostalCode());
        address.setStreetName(addressModify.getStreetName());
        address.setTown(addressModify.getTown());
        address.setStreetNumberComplement(addressModify.getStreetNumberComplement());
        address.setStreetNumber(addressModify.getStreetNumber());
        addressRepository.save(address);
        return address;
    }
}
