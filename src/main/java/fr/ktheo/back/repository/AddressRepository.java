package fr.ktheo.back.repository;

import fr.ktheo.back.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long>  {

}
