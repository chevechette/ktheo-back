package fr.ktheo.back.repository;

import fr.ktheo.back.model.Category;
import fr.ktheo.back.model.ECategory;
import fr.ktheo.back.model.ETransactionStatus;
import fr.ktheo.back.model.TransactionStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByCategory(ECategory cat);

}
