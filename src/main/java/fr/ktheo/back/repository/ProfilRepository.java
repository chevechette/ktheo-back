package fr.ktheo.back.repository;

import fr.ktheo.back.model.Profil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilRepository extends CrudRepository<Profil,Long> {
    List<Profil> findAllByUser_Id(Long id);
}
