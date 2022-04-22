package fr.ktheo.back.repository;

import fr.ktheo.back.model.UserData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends CrudRepository<UserData,Long> {
}
