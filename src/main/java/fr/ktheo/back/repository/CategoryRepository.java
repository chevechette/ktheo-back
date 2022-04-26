package fr.ktheo.back.repository;

import fr.ktheo.back.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
