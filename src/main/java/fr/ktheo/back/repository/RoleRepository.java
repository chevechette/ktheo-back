package fr.ktheo.back.repository;

import fr.ktheo.back.model.Role;
import fr.ktheo.back.model.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long> {
    Role findByName(RoleEnum role);
}
