package fr.ktheo.back.repository;

import fr.ktheo.back.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address,Long>  {
    Optional<List<Address>> findAllByUser_Id(long id);

}
