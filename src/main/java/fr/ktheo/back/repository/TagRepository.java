package fr.ktheo.back.repository;

import fr.ktheo.back.model.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
